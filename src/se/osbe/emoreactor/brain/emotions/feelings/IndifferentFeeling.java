package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class IndifferentFeeling extends AbstractFeeling {
	public IndifferentFeeling(Double amplitude, long initialTime, long duration) throws ReactorException {
		super(FeelingType.INDIFFERENT, amplitude, initialTime, duration);
	}
}