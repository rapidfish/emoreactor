package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class OpenFeeling extends AbstractFeeling {
	public OpenFeeling(int intencity) throws ReactorException {
		super(FeelingType.OPEN, intencity);
	}
}
