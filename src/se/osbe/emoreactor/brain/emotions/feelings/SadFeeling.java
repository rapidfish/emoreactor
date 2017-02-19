package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SadFeeling extends AbstractFeeling {
	
	public SadFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.SAD, amplitude, initialTime, duration);
	}
}