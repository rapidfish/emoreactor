package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PositiveEmotion extends AbstractEmotion {
	
	public PositiveEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.POSITIVE, amplitude, initialTime, duration);
	}
}