package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AfraidFeeling extends AbstractFeeling {
	public AfraidFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.AFRAID, amplitude);
	}
}
