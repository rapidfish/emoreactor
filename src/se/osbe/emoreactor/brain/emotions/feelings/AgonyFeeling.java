package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AgonyFeeling extends AbstractFeeling {
	public AgonyFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.AGONY, amplitude);
	}
}
