package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AngerFeeling extends AbstractFeeling {
	public AngerFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.ANGER, amplitude);
	}
	public AngerFeeling(int amplitude) throws ReactorException {
		super(FeelingType.ANGER, new Double(amplitude));
	}
}
