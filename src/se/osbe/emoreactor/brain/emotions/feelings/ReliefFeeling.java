package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ReliefFeeling extends AbstractFeeling {
	public ReliefFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.RELIEF, amplitude, initialTime, duration);
	}
}
