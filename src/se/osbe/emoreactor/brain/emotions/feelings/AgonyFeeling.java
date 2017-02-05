package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AgonyFeeling extends AbstractFeeling {
	public AgonyFeeling(int intencity) throws ReactorException {
		super(FeelingType.AGONY, intencity);
	}
}
