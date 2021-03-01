package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ConfusedEmotion extends AbstractEmotion {
	public ConfusedEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.CONFUSED, amplitude, initialTime, duration);
	}
}
