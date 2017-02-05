package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HappyFeeling extends AbstractFeeling {
	public HappyFeeling(int intencity) throws ReactorException {
		super(FeelingType.HAPPY, intencity);
	}
}
