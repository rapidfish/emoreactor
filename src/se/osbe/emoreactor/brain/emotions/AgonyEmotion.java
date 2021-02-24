package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AgonyEmotion extends AbstractEmotion {
	public AgonyEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.AGONY, amplitude, initialTime, duration);
	}
}
