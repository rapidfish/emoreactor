package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AngerEmotion extends AbstractEmotion {
	public AngerEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.ANGER, amplitude, initialTime, duration);
	}
}