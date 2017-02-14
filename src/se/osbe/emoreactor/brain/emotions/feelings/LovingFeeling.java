package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class LovingFeeling extends AbstractFeeling {
	
	public LovingFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.LOVING, amplitude);
	}

	public LovingFeeling(int amplitude) throws ReactorException {
		super(FeelingType.LOVING, new Double(amplitude));
	}
	
}
