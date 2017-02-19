package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class StrongFeeling extends AbstractFeeling {
	
	public StrongFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.STRONG, amplitude, initialTime, duration);
	}
}