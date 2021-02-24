package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class OpenEmotion extends AbstractEmotion {
	public OpenEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.OPEN, amplitude, initialTime, duration);
	}
}