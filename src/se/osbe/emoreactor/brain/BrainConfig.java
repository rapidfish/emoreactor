package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public interface BrainConfig {

	BrainHelper getBrainHelper();
	DiceHelper getDiceHelper();

}