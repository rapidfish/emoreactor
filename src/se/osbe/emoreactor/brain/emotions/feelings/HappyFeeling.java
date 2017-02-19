package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HappyFeeling extends AbstractFeeling {
	public HappyFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.HAPPY, amplitude, initialTime, duration);
	}
}
