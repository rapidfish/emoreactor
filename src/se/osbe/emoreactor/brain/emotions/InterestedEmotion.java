package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class InterestedEmotion extends AbstractEmotion {
	
	public InterestedEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.INTERESTED, amplitude, initialTime, duration);
	}
}