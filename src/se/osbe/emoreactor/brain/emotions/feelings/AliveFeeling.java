package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AliveFeeling extends AbstractFeeling {
	public AliveFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.ALIVE, amplitude);
	}
	public AliveFeeling(int amplitude) throws ReactorException {
		super(FeelingType.ALIVE, new Double(amplitude));
	}
}
