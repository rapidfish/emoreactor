package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class DepressedFeeling extends AbstractFeeling {
	public DepressedFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.DEPRESSED, amplitude);
	}
	public DepressedFeeling(int amplitude) throws ReactorException {
		super(FeelingType.DEPRESSED, new Double(amplitude));
	}
}
