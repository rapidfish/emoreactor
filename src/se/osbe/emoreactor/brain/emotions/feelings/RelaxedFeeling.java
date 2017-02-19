package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class RelaxedFeeling extends AbstractFeeling {
	
	public RelaxedFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.RELAXED, amplitude, initialTime, duration);
	}
}