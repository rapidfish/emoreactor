package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AngerFeeling extends AbstractFeeling {
	public AngerFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.ANGER, amplitude, initialTime, duration);
	}
}
