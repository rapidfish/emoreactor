package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PeacefulFeeling extends AbstractFeeling {
	public PeacefulFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.PEACEFUL, amplitude);
	}
	public PeacefulFeeling(int amplitude) throws ReactorException {
		super(FeelingType.PEACEFUL, new Double(amplitude));
	}
}
