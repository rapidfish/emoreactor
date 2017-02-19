package se.osbe.emoreactor.brain.reactor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

public class Reactor {

	private Map<FeelingType, LinkedList<Feeling>> _registry;
	private Map<FeelingType, Double> _intensityResultMap;

	public Reactor() {
		for (FeelingType type : FeelingType.values()) {
			_registry.put(type, new LinkedList<>());
		}
	}

	public void addEmotion(Emotion emo) {
		emo.getFeelings().forEach(feeling -> {
			_registry.get(feeling.getFeelingType()).add(feeling);
		});
	}

	public Map<FeelingType, Double> tic() throws ReactorException {

		long timeNow = getTimeNow(); // Read once per tic
		List<Double> calculatedIntensityList = new ArrayList<>();

		for (int i = 0; i < FeelingType.values().length; i++) {
			FeelingType feelingType = FeelingType.values()[i];
			List<Feeling> list = _registry.get(feelingType);
			
			// Clean up old feelings (EOL)
			list = garbageCollect(list, timeNow);
			
			// Calculate intensity for each concurrent feeling
			list.forEach(feeling -> {
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
			_intensityResultMap.put(feelingType, sum);
		}

		return _intensityResultMap;
	}

	private List<Feeling> garbageCollect(List<Feeling> list, long timeNow) {
		List<Feeling> result = new ArrayList<>();
		list.forEach(f -> {
			if (timeNow <= (f.getInitialTime() + f.getDuration())) {
				result.add(f);
			}
		});
		return result;
	}

	private Double calculateIntensity(Feeling feeling, long timeNow) throws ReactorException {
		Double result = null;
		Double amplitude = feeling.getAmplitude();
		long initialTime = feeling.getInitialTime();
		long duration = feeling.getDuration();
		long endTime = (initialTime + duration);
		long t = (timeNow - initialTime);
		if (timeNow < endTime) {
			if (duration == 0 || amplitude == 0) {
				return 0d;
			} else if (timeNow > endTime) {
				return result; // End of life
			}
			result = amplitude * Math.sin((Math.PI / duration) * t);
		}
		return result;
	}

	public long getTimeNow() {
		return System.currentTimeMillis();
	}
}
