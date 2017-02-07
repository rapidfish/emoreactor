package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HurtFeeling extends AbstractFeeling {
	public HurtFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.HURT, amplitude);
	}
}
