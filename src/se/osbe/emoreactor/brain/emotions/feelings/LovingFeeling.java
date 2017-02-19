package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class LovingFeeling extends AbstractFeeling {
	public LovingFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.LOVING, amplitude, initialTime, duration);
	}
}