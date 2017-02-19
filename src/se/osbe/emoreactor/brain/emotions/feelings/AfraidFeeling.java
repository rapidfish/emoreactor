package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AfraidFeeling extends AbstractFeeling {
	public AfraidFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.AFRAID, amplitude, initialTime, duration);
	}
}