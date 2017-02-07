package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class OpenFeeling extends AbstractFeeling {
	public OpenFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.OPEN, amplitude);
	}
}
