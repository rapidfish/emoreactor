package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SadEmotion extends AbstractEmotion {
	
	public SadEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.SADNESS, amplitude, initialTime, duration);
	}
}