package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class OpenFeeling extends AbstractFeeling {
	public OpenFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.OPEN, amplitude);
	}
	public OpenFeeling(int amplitude) throws ReactorException {
		super(FeelingType.OPEN, new Double(amplitude));
	}
}
