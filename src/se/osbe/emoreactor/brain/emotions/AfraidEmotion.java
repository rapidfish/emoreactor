package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AfraidEmotion extends AbstractEmotion {
	public AfraidEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.AFRAID, amplitude, initialTime, duration);
	}
}