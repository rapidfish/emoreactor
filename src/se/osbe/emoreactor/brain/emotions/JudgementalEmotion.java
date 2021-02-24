package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class JudgementalEmotion extends AbstractEmotion {
	
	public JudgementalEmotion(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(EmotionType.JUDGEMENTAL, amplitude, initialTime, duration);
	}
}