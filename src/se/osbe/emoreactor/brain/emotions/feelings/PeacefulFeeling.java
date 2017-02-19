package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PeacefulFeeling extends AbstractFeeling {
	public PeacefulFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.PEACEFUL, amplitude, initialTime, duration);
	}
}