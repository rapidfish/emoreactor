package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public interface BrainConfig {
	public BrainHelper getBrainHelper();
	public DiceHelper getDiceHelper();
	public Integer getPerceptionAwareness();
	public Personality getPersonality();
}