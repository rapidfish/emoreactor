package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class OpenFeeling extends AbstractFeeling {
	public OpenFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.OPEN, amplitude, initialTime, duration);
	}
}