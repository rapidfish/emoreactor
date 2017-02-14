package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HelplessFeeling extends AbstractFeeling {
	public HelplessFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.HELPLESS, amplitude);
	}

	public HelplessFeeling(int amplitude) throws ReactorException {
		super(FeelingType.HELPLESS, new Double(amplitude));
	}

}
