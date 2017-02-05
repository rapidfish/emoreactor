package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class SmellPerception extends AbstractPerception {
	public SmellPerception(Emotion emoCandidate) throws ReactorException {
		super(PerceptionType.SMELL, emoCandidate);
	}
}