package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HappyEmotion extends AbstractEmotion {
	public HappyEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.HAPPY, amplitude, initialTime, duration);
	}
}
