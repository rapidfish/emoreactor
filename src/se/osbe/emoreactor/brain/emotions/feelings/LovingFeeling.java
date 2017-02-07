package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class LovingFeeling extends AbstractFeeling {
	public LovingFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.LOVING, amplitude);
	}
}
