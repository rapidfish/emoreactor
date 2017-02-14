package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class RelaxedFeeling extends AbstractFeeling {
	
	public RelaxedFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.RELAXED, amplitude);
	}
	
	public RelaxedFeeling(int amplitude) throws ReactorException {
		super(FeelingType.RELAXED, new Double(amplitude));
	}
	
}
