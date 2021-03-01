package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HelplessEmotion extends AbstractEmotion {
	public HelplessEmotion(Float amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.HELPLESS, amplitude, initialTime, duration);
	}
}
