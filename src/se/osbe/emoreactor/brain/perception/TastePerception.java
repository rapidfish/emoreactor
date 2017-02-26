package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class TastePerception extends AbstractPerception {
	public TastePerception(Emotion emoCandidate) throws ReactorException {
		super(PerceptionType.TASTING, emoCandidate);
	}
}