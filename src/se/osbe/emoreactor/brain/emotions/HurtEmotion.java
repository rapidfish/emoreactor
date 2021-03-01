package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HurtEmotion extends AbstractEmotion {
	public HurtEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.HURT, amplitude, initialTime, duration);
	}
}
