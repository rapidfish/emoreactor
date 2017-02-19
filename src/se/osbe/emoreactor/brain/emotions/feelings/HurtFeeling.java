package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HurtFeeling extends AbstractFeeling {
	public HurtFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.HURT, amplitude, initialTime, duration);
	}
}
