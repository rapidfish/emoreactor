package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AngerFeeling extends AbstractFeeling {
	public AngerFeeling(int intencity) throws ReactorException {
		super(FeelingType.ANGER, intencity);
	}
}
