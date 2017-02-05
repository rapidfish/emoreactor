package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class DepressedFeeling extends AbstractFeeling {
	public DepressedFeeling(int intencity) throws ReactorException {
		super(FeelingType.DEPRESSED, intencity);
	}
}
