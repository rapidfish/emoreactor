package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.BrainHelperImpl;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.DiceHelperImpl;

public class BrainConfigDefaultImpl implements BrainConfig {
	
	private BrainHelper _brainHelper;
	private DiceHelper _diceHelper;
	private Integer _perceptionAwareness;
	private Personality _personality;
	
	public BrainConfigDefaultImpl(Personality personality) {
		_brainHelper = new BrainHelperImpl();
		_diceHelper = new DiceHelperImpl();
		_perceptionAwareness = new Integer(50); // 50% awareness
		_personality = personality;
	}
	
	@Override
	public BrainHelper getBrainHelper() {
		return _brainHelper;
	}
	
	@Override
	public DiceHelper getDiceHelper() {
		return _diceHelper;
	}

	@Override
	public Integer getPerceptionAwareness() {
		return _perceptionAwareness;
	}

	@Override
	public Personality getPersonality() {
		return null;
	}
}
