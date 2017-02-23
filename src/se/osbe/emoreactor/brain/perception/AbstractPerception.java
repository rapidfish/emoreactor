package se.osbe.emoreactor.brain.perception;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public abstract class AbstractPerception implements Perception {
	private final PerceptionType _perceptionType;
	private final Emotion _emoCandidate;
	private static final String PARAM_FEELING_NULL = "Parametrar feeling is null";

	@SuppressWarnings("unused")
	private AbstractPerception() throws ReactorException {
		this(null, null);
	}
	
	protected AbstractPerception(PerceptionType perception, Emotion emoCandidate) throws ReactorException {
		if (perception == null) {
			throw new ReactorException(PARAM_FEELING_NULL);
		}
		_perceptionType = perception;
		_emoCandidate = emoCandidate;
	}

	public PerceptionType getPerceptionType() {
		return _perceptionType;
	}
	
	public Emotion getEmotionCandidate() {
		return _emoCandidate;
	}
	
	@Override
	public String toString() {
		String result = _perceptionType.getDescription() + " --> Emo(" + _emoCandidate + ")";
		return result;
	}
}