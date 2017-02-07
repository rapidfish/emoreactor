package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ConfusedFeeling extends AbstractFeeling {
	public ConfusedFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.CONFUSED, amplitude);
	}
}
