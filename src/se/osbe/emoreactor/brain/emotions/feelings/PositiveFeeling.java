package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PositiveFeeling extends AbstractFeeling {
	
	public PositiveFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.POSITIVE, amplitude);
	}
	
	public PositiveFeeling(int amplitude) throws ReactorException {
		super(FeelingType.POSITIVE, new Double(amplitude));
	}
	
}
