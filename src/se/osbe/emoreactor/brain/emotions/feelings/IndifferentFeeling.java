package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class IndifferentFeeling extends AbstractFeeling {
	public IndifferentFeeling(int intencity) throws ReactorException {
		super(FeelingType.INDIFFERENT, intencity);
	}
}
