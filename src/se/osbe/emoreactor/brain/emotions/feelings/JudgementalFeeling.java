package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class JudgementalFeeling extends AbstractFeeling {
	
	public JudgementalFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.JUDGEMENTAL, amplitude);
	}

	public JudgementalFeeling(int amplitude) throws ReactorException {
		super(FeelingType.JUDGEMENTAL, new Double(amplitude));
	}
	
}
