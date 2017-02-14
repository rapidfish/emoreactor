package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class InterestedFeeling extends AbstractFeeling {
	
	public InterestedFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.INTERESTED, amplitude);
	}
	
	public InterestedFeeling(int amplitude) throws ReactorException {
		super(FeelingType.INTERESTED, new Double(amplitude));
	}

}
