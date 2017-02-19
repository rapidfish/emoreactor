package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ConfusedFeeling extends AbstractFeeling {
	public ConfusedFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.CONFUSED, amplitude, initialTime, duration);
	}
}
