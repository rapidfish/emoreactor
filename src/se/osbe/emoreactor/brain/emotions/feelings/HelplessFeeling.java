package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HelplessFeeling extends AbstractFeeling {
	public HelplessFeeling(Integer amplitude) throws ReactorException {
		super(FeelingType.HELPLESS, amplitude);
	}
}
