package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SightPerception extends AbstractPerception {
	public SightPerception(Emotion emoCandidate) throws ReactorException {
		super(PerceptionType.SEE, emoCandidate);
	}
}