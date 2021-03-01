package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class DepressedEmotion extends AbstractEmotion {
	public DepressedEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.DEPRESSED, amplitude, initialTime, duration);
	}
}
