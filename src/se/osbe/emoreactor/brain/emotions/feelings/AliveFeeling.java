package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AliveFeeling extends AbstractFeeling {
	public AliveFeeling(int intencity) throws ReactorException {
		super(FeelingType.ALIVE, intencity);
	}
}
