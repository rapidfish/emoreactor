package se.osbe.emoreactor.brain.reactor;

import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Reactor {

    private final Map<EmotionType, List<Emotion>> _registry;
    private final Map<EmotionType, Double> _intensityResultMap;
    private final Map<EmotionType, ProgressTrendType> _progressingTypeMap;
    private final BrainConfig _config;

    public Reactor(BrainConfig config) {
        _config = config;
        _intensityResultMap = new HashMap<>();
        _progressingTypeMap = new HashMap<>();
        _registry = new HashMap<>();
        for (EmotionType type : EmotionType.values()) {
            _registry.put(type, new LinkedList<>());
        }
    }

    public void addEmotion(Feeling feeling) {
        feeling.getEmotions().forEach(emotion -> _registry.get(emotion.getFeelingType()).add(emotion));
    }

    public boolean isRegistryEmpty() {
        return _registry.values().isEmpty();
//        for (int i = 0; i < EmotionType.values().length; i++) {
//            if (!_registry.get(EmotionType.values()[i]).isEmpty()) {
//                return false;
//            }
//        }
//        return true;
    }

    public ProgressTrendType getProgressForFeeling(EmotionType type) {
        return _progressingTypeMap.get(type);
    }

    public Map<EmotionType, Double> tic() {
        long now = System.currentTimeMillis(); // Read only once per tic
        List<Double> calculatedIntensityList = new ArrayList<>();

        IntStream.range(0, EmotionType.values().length).forEach(i -> {
            EmotionType emotionType = EmotionType.values()[i];
            List<Emotion> listOfSameEmotions = _registry.get(emotionType);

            // Clean up old feelings (EOL) and write back to registry
            listOfSameEmotions = garbageCollect(listOfSameEmotions, now);
            _registry.put(emotionType, listOfSameEmotions);

            // Calculate intensity for each concurrent feeling
            listOfSameEmotions.forEach(emotion -> calculatedIntensityList.add(
                    calculateIntensity(emotion, now))
            );
            Double sum = calculatedIntensityList.stream().reduce(0d, Double::sum);
            Double oldSum = _intensityResultMap.get(emotionType);
            oldSum = (oldSum != null) ? oldSum : (double) 0;
            Double delta = (sum - oldSum);
            ProgressTrendType trend = ProgressTrendType.NEUTRAL;
            if (delta.compareTo(0d) != 0) {
                trend = (delta.compareTo(0d) > 0) ? ProgressTrendType.POSITIVE : ProgressTrendType.NEGATIVE;
            }
            trend.setCoefficient(delta); // store delta
            _progressingTypeMap.put(emotionType, trend);
            _intensityResultMap.put(emotionType, sum);
            calculatedIntensityList.clear();
        });
        return _intensityResultMap;
    }

    private List<Emotion> garbageCollect(List<Emotion> emotions, long now) {
        return emotions.stream()
                .filter(f -> now <= (f.getInitialTime() + f.getDuration()))
                .collect(Collectors.toList()); // return remaining active emotions after cleanup!
    }

    private Double calculateIntensity(Emotion emotion, long now) {
        Double result = null;
        long initialTime = emotion.getInitialTime();
        long duration = emotion.getDuration();
        if (now >= (initialTime + duration) || now < initialTime) {
            return 0d;
        }
        Double amplitude = emotion.getAmplitude();
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