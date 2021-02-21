package se.osbe.emoreactor.brain.reactor;

import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Reactor {

    private final Map<FeelingType, List<Feeling>> _registry;
    private final Map<FeelingType, Double> _intensityResultMap;
    private final Map<FeelingType, ProgressTrendType> _progressingTypeMap;
    private final BrainConfig _config;

    public Reactor(BrainConfig config) {
        _config = config;
        _intensityResultMap = new HashMap<>();
        _progressingTypeMap = new HashMap<>();
        _registry = new HashMap<>();
        for (FeelingType type : FeelingType.values()) {
            _registry.put(type, new LinkedList<>());
        }
    }

    public void addEmotion(Emotion emo) {
        emo.getFeelings().forEach(feeling -> _registry.get(feeling.getFeelingType()).add(feeling));
    }

    public boolean isRegistryEmpty() {
        for (int i = 0; i < FeelingType.values().length; i++) {
            if (!_registry.get(FeelingType.values()[i]).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ProgressTrendType getProgressForFeeling(FeelingType type) {
        return _progressingTypeMap.get(type);
    }

    public Map<FeelingType, Double> ticTac() {

        long now = System.currentTimeMillis(); // Read once per tic
        List<Double> calculatedIntensityList = new ArrayList<>();

        IntStream.range(0, FeelingType.values().length).forEach(i -> {
            FeelingType feelingType = FeelingType.values()[i];
            List<Feeling> listOfSameFeelings = _registry.get(feelingType);

            // Clean up old feelings (EOL) and write back to registry
            listOfSameFeelings = garbageCollect(listOfSameFeelings, now);
            _registry.put(feelingType, listOfSameFeelings);

            // Calculate intensity for each concurrent feeling
            listOfSameFeelings.forEach(feeling -> calculatedIntensityList.add(
                    calculateIntensity(feeling, now))
            );
            Double sum = calculatedIntensityList.stream().reduce(0d, Double::sum);
            Double oldSum = _intensityResultMap.get(feelingType);
            oldSum = (oldSum != null) ? oldSum : (double) 0;
            Double delta = (sum - oldSum);
            ProgressTrendType trend = ProgressTrendType.NEUTRAL;
            if (delta.compareTo(0d) != 0) {
                trend = (delta.compareTo(0d) > 0) ? ProgressTrendType.POSITIVE : ProgressTrendType.NEGATIVE;
            }
            trend.setCoefficient(delta); // store delta
            _progressingTypeMap.put(feelingType, trend);
            _intensityResultMap.put(feelingType, sum);
            calculatedIntensityList.clear();
        });
        return _intensityResultMap;
    }

    private List<Feeling> garbageCollect(List<Feeling> feelings, long now) {
        return feelings.stream()
                .filter(f -> now <= (f.getInitialTime() + f.getDuration()))
                .collect(Collectors.toList()); // return remaining active feelings after cleanup!
    }

    private Double calculateIntensity(Feeling feeling, long now) {
        Double result = null;
        long initialTime = feeling.getInitialTime();
        long duration = feeling.getDuration();
        if (now >= (initialTime + duration) || now < initialTime) {
            return 0d;
        }
        Double amplitude = feeling.getAmplitude();
        if (duration == 0 || amplitude == 0) {
            return 0d;
        }
        return amplitude * Math.sin((Math.PI / duration) * (now - initialTime));
    }

    public BrainConfig getConfig() {
        return _config;
    }

    public enum ProgressTrendType {
        NEUTRAL, POSITIVE, NEGATIVE;
        private Double coefficient;

        ProgressTrendType() {
            coefficient = (double) 0;
        }

        public Double getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(Double k) {
            coefficient = k;
        }

        public ProgressTrendType getTypeForVal(Double d) {
            if (d.compareTo(0d) == 0) {
                return ProgressTrendType.NEUTRAL;
            } else if (d.compareTo(0d) > 0) {
                return ProgressTrendType.POSITIVE;
            } else {
                return ProgressTrendType.NEGATIVE;
            }
        }
    }
}