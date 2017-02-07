package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PeacefulFeeling extends AbstractFeeling {
	public PeacefulFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.PEACEFUL, amplitude);
	}
}
