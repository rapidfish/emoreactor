package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AliveEmotion extends AbstractEmotion {
	public AliveEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.ALIVE, amplitude, initialTime, duration);
	}
}
