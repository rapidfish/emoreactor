package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AliveFeeling extends AbstractFeeling {
	public AliveFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.ALIVE, amplitude, initialTime, duration);
	}
}
