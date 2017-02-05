package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class LovingFeeling extends AbstractFeeling {
	public LovingFeeling(int intencity) throws ReactorException {
		super(FeelingType.LOVING, intencity);
	}
}
