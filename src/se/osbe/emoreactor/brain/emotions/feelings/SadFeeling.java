package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SadFeeling extends AbstractFeeling {
	public SadFeeling(int amplitude) throws ReactorException {
		super(FeelingType.SAD, amplitude);
	}
}
