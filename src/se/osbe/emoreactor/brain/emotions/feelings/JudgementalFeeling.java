package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class JudgementalFeeling extends AbstractFeeling {
	public JudgementalFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.JUDGEMENTAL, amplitude);
	}
}
