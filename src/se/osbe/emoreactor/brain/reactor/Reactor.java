package se.osbe.emoreactor.brain.reactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.BrainConfig;
import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

public class Reactor {

	private final Map<FeelingType, List<Feeling>> _registry;
	private final Map<FeelingType, Double> _intensityResultMap;
	private final Map<FeelingType, ProgressTrendType> _progressingTypeMap;
	private final BrainConfig _config;
	
	public enum ProgressTrendType {
		NEUTRAL, POSITIVE, NEGATIVE;
		private Double _k;
		ProgressTrendType() {
			_k = new Double(0);
		}
		public Double getK(){
			return _k;
		}
		public void setK(Double k){
			_k = k;
		}
		public ProgressTrendType getTypeForVal(Double d){
			if(d.compareTo(0d) == 0){
				return ProgressTrendType.NEUTRAL;
			} else if(d.compareTo(0d) > 0 ) {
				return ProgressTrendType.POSITIVE;
			} else {
				return ProgressTrendType.NEGATIVE;
			}
		}
	};
	
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
		emo.getFeelings().forEach(feeling -> {
			_registry.get(feeling.getFeelingType()).add(feeling);
		});
	}

	public boolean isRegistryEmpty() {
		for (int i = 0; i < FeelingType.values().length; i++) {
			if(_registry.get(FeelingType.values()[i]).isEmpty() == false) {
				return false;
			}
		}
		return true;
	}
	
	public ProgressTrendType getProgressForFeeling(FeelingType type){
		return _progressingTypeMap.get(type);
	}
	
	public Map<FeelingType, Double> ticTac() throws ReactorException {
		
		long timeNow = getConfig().getTicker().getTicTimeNow(); // Read once per tic
		List<Double> calculatedIntensityList = new ArrayList<>();
		
		for (int i = 0; i < FeelingType.values().length; i++) {
			FeelingType feelingType = FeelingType.values()[i];
			List<Feeling> listOfSameFeelings = _registry.get(feelingType);
			
			// Clean up old feelings (EOL) and write back to registry
			listOfSameFeelings = garbageCollect(listOfSameFeelings, timeNow);
			_registry.put(feelingType, listOfSameFeelings);
			
			// Calculate intensity for each concurrent feeling
			listOfSameFeelings.forEach(feeling -> {
				try {
					Double intensity = calculateIntensity(feeling, timeNow);
					if (intensity != null) {
						calculatedIntensityList.add(intensity);
					}
				} catch (ReactorException e) {
					e.printStackTrace();
				}
			});
			Double sum = new Double(0);
			for (int j = 0; j < calculatedIntensityList.size(); j++) {
				sum += calculatedIntensityList.get(j);
			}
			Double oldSum = _intensityResultMap.get(feelingType);
			oldSum = (oldSum != null) ? oldSum : new Double(0); 
			Double delta = sum - oldSum; 
			ProgressTrendType k = ProgressTrendType.NEUTRAL;
			if(delta.compareTo(0d) != 0) {
				k = (delta.compareTo(0d) > 0) ? ProgressTrendType.POSITIVE : ProgressTrendType.NEGATIVE;
			}
			k.setK(delta); // store delta
			_progressingTypeMap.put(feelingType, k);
			_intensityResultMap.put(feelingType, sum);
			calculatedIntensityList.clear();
		}
		return _intensityResultMap;
	}

	private List<Feeling> garbageCollect(List<Feeling> feelings, long timeNow) {
		List<Feeling> result = new ArrayList<>();
		feelings.forEach(f -> {
			if (timeNow <= (f.getInitialTime() + f.getDuration())) {
				result.add(f); // Only feelings with an active life cycle gets passed here
			}
		});
		return result; // return remaining active feelings after cleanup!
	}

	private Double calculateIntensity(Feeling feeling, long timeNow) throws ReactorException {
		Double result = null;
		Double amplitude = feeling.getAmplitude();
		long initialTime = feeling.getInitialTime();
		long duration = feeling.getDuration();
		long endTime = (initialTime + duration);
		long t = (timeNow - initialTime);
		long shaveValue = t % 1000;
		t -= shaveValue; // Shave off surplus time that eventually will drift away!
		if (timeNow <= endTime) {
			if (duration == 0 || amplitude == 0) {
				return 0d;
			} else if (timeNow > endTime) {
				return result; // End of life
			}
			result = amplitude * Math.sin((Math.PI / duration) * t);
		}
		return result;
	}

	public BrainConfig getConfig() {
		return _config;
	}
}