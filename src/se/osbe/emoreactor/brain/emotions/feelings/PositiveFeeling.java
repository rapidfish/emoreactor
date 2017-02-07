package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PositiveFeeling extends AbstractFeeling {
	public PositiveFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.POSITIVE, amplitude);
	}
}
