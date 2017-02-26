package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class TouchPerception extends AbstractPerception {
	public TouchPerception(Emotion emoCandidate) throws ReactorException {
		super(PerceptionType.TOUCHING, emoCandidate);
	}
}