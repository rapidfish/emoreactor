package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PositiveFeeling extends AbstractFeeling {
	public PositiveFeeling(int intencity) throws ReactorException {
		super(FeelingType.POSITIVE, intencity);
	}
}
