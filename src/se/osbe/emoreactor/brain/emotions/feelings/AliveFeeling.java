package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AliveFeeling extends AbstractFeeling {
	public AliveFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.ALIVE, amplitude);
	}
}
