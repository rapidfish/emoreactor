package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HappyFeeling extends AbstractFeeling {
	public HappyFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.HAPPY, amplitude);
	}
	public HappyFeeling(int amplitude) throws ReactorException {
		super(FeelingType.HAPPY, new Double(amplitude));
	}
}
