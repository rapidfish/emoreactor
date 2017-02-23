package se.osbe.emoreactor.brain;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.PerceptionType;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.brain.reactor.Reactor.ProgressType;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;

public class Brain {

	private final String _id;
	private long _ticCounter;
	private final Personality _personality;
	private Integer _perceptionAwarenessPercentage;
	private final Queue<Emotion> _perceptionQueue;
	private Reactor _reactor;

	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		_id = BrainHelper.createUUID();
		_personality = personality;
		_perceptionAwarenessPercentage = 100; // 100%
		_perceptionQueue = new LinkedList<Emotion>(); // incomming emo's from
														// perception
		_reactor = new Reactor();
		_ticCounter = 0;
	}

	// Add only if within brains attention span
	public boolean addInboundPerception(Perception perception) {
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();
		boolean isAccepted = false;
		// if (_dice.getRandomPercentage() <= _awarenessPercentage) {
		isAccepted = _perceptionQueue.offer(emoCandidate);
		if (!isAccepted) {
			System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
		}
		// }
		return isAccepted;
	}

	public Personality getPersonality() {
		return _personality;
	}

	public Map<FeelingType, Double> tic() throws ReactorException {

		// Poll perception from queue!
		Emotion inboundEmotion = _perceptionQueue.poll();
		if (inboundEmotion != null) {
			System.err.println("Inbound -> " + inboundEmotion);
			_reactor.addEmotion(inboundEmotion);
		}

		// Consume one ticTac in reactor
		Map<FeelingType, Double> emotionNow = _reactor.ticTac();
		_ticCounter++;
		return emotionNow;
	}

	public String getId() {
		return _id;
	}

	public Integer getPerceptionAwarenessPercentage() {
		return _perceptionAwarenessPercentage;
	}

	public void setPerceptionAwarenessPercentage(Integer percentage) {
		_perceptionAwarenessPercentage = (percentage >= 0 && percentage <= 100) ? percentage : 0;
	}

	public long getTickCounter() {
		return _ticCounter;
	}

	public boolean isReactorDry() {
		return _reactor.isRegistryEmpty();
	}

	public ProgressType getProgressTypeForFeeling(FeelingType type) {
		return _reactor.getProgressForFeeling(type);
	}

	public String getProgressSignForFeeling(FeelingType type) {
		ProgressType pt = _reactor.getProgressForFeeling(type);
		String result = null;
		String k = String.format( "%.2f", pt.getK());
		if (pt == ProgressType.NEUTRAL) {
			result = k;
		} else if (pt == ProgressType.POSITIVE) {
			result = "+" + k;
		} else

		if (pt == ProgressType.NEGATIVE) {
			result = k;
		}
		return result;
	}
}