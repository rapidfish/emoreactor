package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class InterestedFeeling extends AbstractFeeling {
	public InterestedFeeling(int intencity) throws ReactorException {
		super(FeelingType.INTERESTED, intencity);
	}
}
