package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HurtFeeling extends AbstractFeeling {
	public HurtFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.HURT, amplitude);
	}
	public HurtFeeling(int amplitude) throws ReactorException {
		super(FeelingType.HURT, new Double(amplitude));
	}
}
