package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AngerFeeling extends AbstractFeeling {
	public AngerFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.ANGER, amplitude);
	}
}
