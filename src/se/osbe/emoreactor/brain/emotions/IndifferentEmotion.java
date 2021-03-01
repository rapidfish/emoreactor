package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class IndifferentEmotion extends AbstractEmotion {
	public IndifferentEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.INDIFFERENT, amplitude, initialTime, duration);
	}
}