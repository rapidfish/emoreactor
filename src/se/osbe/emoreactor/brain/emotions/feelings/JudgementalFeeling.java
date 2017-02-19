package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class JudgementalFeeling extends AbstractFeeling {
	
	public JudgementalFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.JUDGEMENTAL, amplitude, initialTime, duration);
	}
}