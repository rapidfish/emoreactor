package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.BrainHelperImpl;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.DiceHelperImpl;

public class BrainConfigDefaultImpl implements BrainConfig {
	
	private BrainHelper _brainHelper;
	private DiceHelper _diceHelper;
	
	public BrainConfigDefaultImpl() {
		_brainHelper = new BrainHelperImpl();
		_diceHelper = new DiceHelperImpl();
	}
	
	@Override
	public BrainHelper getBrainHelper() {
		return _brainHelper;
	}
	
	@Override
	public DiceHelper getDiceHelper() {
		return _diceHelper;
	}
}
