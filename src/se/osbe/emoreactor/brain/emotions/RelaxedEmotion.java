package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class RelaxedEmotion extends AbstractEmotion {
	
	public RelaxedEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.RELAXED, amplitude, initialTime, duration);
	}
}