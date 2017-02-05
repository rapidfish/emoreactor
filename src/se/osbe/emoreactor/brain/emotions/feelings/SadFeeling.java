package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SadFeeling extends AbstractFeeling {
	public SadFeeling(int intencity) throws ReactorException {
		super(FeelingType.SAD, intencity);
	}
}
