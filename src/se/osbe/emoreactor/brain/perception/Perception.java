package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;

public interface Perception {
	public PerceptionType getPerceptionType();
	public Emotion getEmotionCandidate();
}