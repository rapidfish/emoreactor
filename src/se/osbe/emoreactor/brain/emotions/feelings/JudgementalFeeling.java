package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class JudgementalFeeling extends AbstractFeeling {
	public JudgementalFeeling(int intencity) throws ReactorException {
		super(FeelingType.JUDGEMENTAL, intencity);
	}
}
