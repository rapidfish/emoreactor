package se.osbe.emoreactor.brain.reactor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

public class Reactor {

	private Map<FeelingType, LinkedList<Feeling>> _registry;

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

	public void tic(long mainCounter) throws ReactorException {
		// anv mainCounter som t
		// calculateIntencity / Emo x feelings
		for (int i = 0; i < FeelingType.values().length; i++) {
			Double intencity = new Double(0);
			List<Feeling> list = _registry.get(FeelingType.values()[i]);
			list.forEach(feeling -> {
				FeelingType type = feeling.getFeelingType();
				long initialTime = feeling.getInitialTime();
				long duration = feeling.getDuration();
				if (mainCounter <= (initialTime + duration)) {
					Double calculatedIntencity = null;
					try {
						calculatedIntencity = calculateIntencity(feeling, mainCounter);
					} catch (ReactorException e) {
						e.printStackTrace();
					}
					intencity.sum(intencity, calculatedIntencity);
				}
			});
		}
	}

	private Double calculateIntencity(Feeling feeling, long t) throws ReactorException {
		Double result = null;
		// Read
		// FeelingType type = feeling.getFeelingType();
		Double amplitude = feeling.getAmplitude();
		long duration = feeling.getDuration();

		// validate
		if (duration == 0 || amplitude == 0) {
			return 0d;
		} else if (t > duration) {
			throw new ReactorException(
					"Value for (t) is not allowed to be greater than the value of duration for a feeling");
		} else if (t < 0) {
			throw new ReactorException("Value for (t) is not allowed to be negative");
		}

		// TODO: Make formula configurable here later!
		// For now, using sinus-waves to spread amplitude smoothly over duration
		// time
		if (t <= duration) {
			result = amplitude * Math.sin((Math.PI / duration) * t);
		}
		return result;
	}
}
