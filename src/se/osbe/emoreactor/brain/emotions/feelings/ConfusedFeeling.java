package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ConfusedFeeling extends AbstractFeeling {
	public ConfusedFeeling(int intencity) throws ReactorException {
		super(FeelingType.CONFUSED, intencity);
	}
}
