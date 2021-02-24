package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PeacefulEmotion extends AbstractEmotion {
	public PeacefulEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.PEACEFUL, amplitude, initialTime, duration);
	}
}