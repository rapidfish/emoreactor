package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class DepressedFeeling extends AbstractFeeling {
	public DepressedFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.DEPRESSED, amplitude, initialTime, duration);
	}
}
