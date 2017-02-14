package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AgonyFeeling extends AbstractFeeling {
	public AgonyFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.AGONY, amplitude);
	}
	public AgonyFeeling(int amplitude) throws ReactorException {
		super(FeelingType.AGONY, new Double(amplitude));
	}
}
