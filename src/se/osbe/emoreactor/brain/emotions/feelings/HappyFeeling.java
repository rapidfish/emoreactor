package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HappyFeeling extends AbstractFeeling {
	public HappyFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.HAPPY, amplitude);
	}
}
