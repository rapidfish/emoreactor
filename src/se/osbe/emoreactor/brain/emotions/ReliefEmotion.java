package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ReliefEmotion extends AbstractEmotion {
	public ReliefEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.RELIEF, amplitude, initialTime, duration);
	}
}
