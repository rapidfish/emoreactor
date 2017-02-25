package se.osbe.emoreactor.brain;

import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.Ticker;

public interface BrainConfig {
	public BrainHelper getBrainHelper();
	public DiceHelper getDiceHelper();
	public Integer getPerceptionAwareness();
	public void setPerceptionAwareness(int i);
	public Personality getPersonality();
	public Queue<Emotion> getPerceptionQueue();
	public Ticker getTicker();
}