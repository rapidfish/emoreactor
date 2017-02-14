package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class ConfusedFeeling extends AbstractFeeling {
	public ConfusedFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.CONFUSED, amplitude);
	}
	public ConfusedFeeling(int amplitude) throws ReactorException {
		super(FeelingType.CONFUSED, new Double(amplitude));
	}
}
