package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AfraidFeeling extends AbstractFeeling {
	public AfraidFeeling(int intencity) throws ReactorException {
		super(FeelingType.AFRAID, intencity);
	}
}
