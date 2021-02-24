package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class StrongEmotion extends AbstractEmotion {
	
	public StrongEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.STRONG, amplitude, initialTime, duration);
	}
}