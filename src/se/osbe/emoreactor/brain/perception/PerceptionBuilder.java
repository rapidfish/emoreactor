package se.osbe.emoreactor.brain.perception;

import java.util.LinkedList;
import java.util.List;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PerceptionBuilder {

	private static List<Perception> _perceptions;

	public PerceptionBuilder() {
		_perceptions = new LinkedList<Perception>();
	}

	public PerceptionBuilder addPerception(PerceptionType perceptionType, Emotion emoCandidate) throws ReactorException {
		AbstractPerception perception;
		switch (perceptionType) {
		case TASTE:
			perception = (AbstractPerception) new TastePerception(emoCandidate);
			break;
		case SIGHT:
			perception = (AbstractPerception) new SightPerception(emoCandidate);
			break;
		case TOUCH:
			perception = (AbstractPerception) new TouchPerception(emoCandidate);
			break;
		case SMELL:
			perception = (AbstractPerception) new SmellPerception(emoCandidate);
			break;
		case HEARING:
			perception = (AbstractPerception) new HearingPerception(emoCandidate);
			break;
		default:
			throw new ReactorException("Missing enum for constructor");
		}
		_perceptions.add(perception);
		return this;
	}

	public List<Perception> build() throws ReactorException {
		if (_perceptions == null) {
			throw new ReactorException("PerceptionBuilder has no feelings to build up on!");
		}
		return _perceptions;
	}

	public PerceptionBuilder reset() {
		_perceptions.clear();
		return this;
	}
}