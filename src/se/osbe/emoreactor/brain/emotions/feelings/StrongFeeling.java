package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class StrongFeeling extends AbstractFeeling {
	
	public StrongFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.STRONG, amplitude);
	}
	
	public StrongFeeling(int amplitude) throws ReactorException {
		super(FeelingType.STRONG, new Double(amplitude));
	}
	
}
