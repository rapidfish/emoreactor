package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class LovingEmotion extends AbstractEmotion {
	public LovingEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.LOVING, amplitude, initialTime, duration);
	}
}