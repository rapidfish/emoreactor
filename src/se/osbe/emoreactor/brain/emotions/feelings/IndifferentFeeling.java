package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class IndifferentFeeling extends AbstractFeeling {
	public IndifferentFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.INDIFFERENT, amplitude);
	}
	public IndifferentFeeling(int amplitude) throws ReactorException {
		super(FeelingType.INDIFFERENT, new Double(amplitude));
	}
}
