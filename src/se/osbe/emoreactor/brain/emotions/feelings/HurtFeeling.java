package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HurtFeeling extends AbstractFeeling {
	public HurtFeeling(int intencity) throws ReactorException {
		super(FeelingType.HURT, intencity);
	}
}
