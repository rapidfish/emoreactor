package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PositiveFeeling extends AbstractFeeling {
	
	public PositiveFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.POSITIVE, amplitude, initialTime, duration);
	}
}