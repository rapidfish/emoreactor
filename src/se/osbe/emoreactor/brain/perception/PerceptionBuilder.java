package se.osbe.emoreactor.brain.perception;

import java.util.LinkedList;
import java.util.List;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class PerceptionBuilder {

	private List<Perception> _perceptions = new LinkedList<>();
	private EmotionBuilder _emotionBuilder;

	public PerceptionBuilder() {
		_perceptions = new LinkedList<Perception>();
		_emotionBuilder = new EmotionBuilder();
	}

	/**
	 * 
	 * @param type Perception type as a string.
	 * Valid perception types is: Taste, See, Touch, Smell, Hear.
	 * 
	 * @param feelingsScript String with feelings separated with semicolon on the form:
	 * 		[Feeling]=[intensity value], [duration][prefix: s/m/h/w, seconds, minutes, hours, weeks];
	 * Example: "Agony=24,34s;Sadness=31,45s;Hurt=22,1h"
	 * @return
	 * @throws ReactorException
	 */
	public PerceptionBuilder addPerception(String type, String feelingsScript) throws ReactorException {
		PerceptionType pt = PerceptionType.valueOf(type.toUpperCase());
		return addPerception(pt, _emotionBuilder.addFeelings(feelingsScript).build(null));
	}
	
	public PerceptionBuilder addPerception(PerceptionType type, String feelingsScript) throws ReactorException {
		return addPerception(type, _emotionBuilder.addFeelings(feelingsScript).build(null));
	}

	public PerceptionBuilder addPerception(PerceptionType perceptionType, Emotion emoCandidate)
			throws ReactorException {
		Perception perception;
		switch (perceptionType) {
		case TASTING:
			perception = new TastePerception(emoCandidate);
			break;
		case EYE_SIGHT:
			perception = new SightPerception(emoCandidate);
			break;
		case TOUCHING:
			perception = new TouchPerception(emoCandidate);
			break;
		case SMELLING:
			perception = new SmellPerception(emoCandidate);
			break;
		case HEARING:
			perception = new HearingPerception(emoCandidate);
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
		List<Perception> result = new LinkedList<>();
		result.addAll(_perceptions);
		_perceptions.clear();
		return result;
	}

	public static void main(String[] args) throws ReactorException {
		PerceptionBuilder builder = new PerceptionBuilder();
		builder.addPerception(PerceptionType.HEARING, "agony=24,30s;");
		builder.addPerception(PerceptionType.EYE_SIGHT, "afraid=30,1m;");
//		builder.addPerception(PerceptionType.SIGHT, "agony=24,30s;");
		System.out.println(builder.build());
	}
}