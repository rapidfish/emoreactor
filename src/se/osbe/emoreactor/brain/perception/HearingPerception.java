package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class HearingPerception extends AbstractPerception {
	public HearingPerception(Emotion emoCandidate) throws ReactorException {
		super(PerceptionType.HEARING, emoCandidate);
	}
}