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
import se.osbe.emoreactor.brain.reactor.Reactor.ProgressTrendType;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

public class Brain {

	private final BrainConfig _brainConfig;
	private long _ticCounter;
	private final Queue<Emotion> _perceptionQueue;
	private Reactor _reactor;
	private DiceHelper _dice;

	@SuppressWarnings("unused")
	private Brain() throws ReactorException {
		this(null);
	}

	public Brain(BrainConfig config) throws ReactorException {
		_brainConfig = config == null ? new BrainConfigDefaultImpl(new Personality()) : config;
		_perceptionQueue = new LinkedList<Emotion>();
		_reactor = new Reactor(_brainConfig);
		_dice = _brainConfig.getDiceHelper();
		_ticCounter = 0;
	}


	// Add only if within brains attention span
	public boolean addInboundPerception(Perception perception) {
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion perceptionEmoCandidate = perception.getEmotionCandidate();
		boolean isAccepted = false;
		Double rndPercentage = _dice.getRandomPercentage();
		Integer awareness = _brainConfig.getPerceptionAwareness();
		if (rndPercentage <= awareness) {
			isAccepted = _perceptionQueue.offer(perceptionEmoCandidate);
			if (!isAccepted) {
				System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
			}
		} else {
			System.err.println("Perception passed undetected for: " + perception.getPerceptionType().getDescription());
		}
		return isAccepted;
	}

	public Personality getPersonality() {
		return _brainConfig.getPersonality();
	}

	/**
	 * Process all inbound emotions supplied by the perception queue. Then
	 * it does internal calculations to summarize changes inside its emotion
	 * registry. And at last it returns its current emotion (list of feelings
	 * and their magnitude right now)
	 * 
	 * @return What emotion the brain is feeling right now! The result is
	 *         reflecting latest emotional changes inside the brain since last
	 *         unit of time has past.
	 *         
	 * @throws ReactorException
	 */
	public Map<FeelingType, Double> tic() throws ReactorException {
		// Poll perception from queue!
		Emotion inboundEmotion = _perceptionQueue.poll();
		if (inboundEmotion != null) {
			System.err.println("Tic: Inbound -> " + inboundEmotion);
			_reactor.addEmotion(inboundEmotion);
		}

		// Delegate one unit of processing (time) emotions to the reactor
		Map<FeelingType, Double> emotionNow = _reactor.ticTac();
		_ticCounter++;
		return emotionNow;
	}

	public Integer getPerceptionAwarenessPercentage() {
		return _brainConfig.getPerceptionAwareness();
	}

	public void setPerceptionAwarenessPercentage(Integer percentage) {
		_brainConfig.setPerceptionAwareness(percentage);
	}

	public long getTickCounter() {
		return _ticCounter;
	}

	public boolean isReactorDry() {
		return _reactor.isRegistryEmpty();
	}

	public ProgressTrendType getProgressTypeForFeeling(FeelingType type) {
		return _reactor.getProgressForFeeling(type);
	}

	public String getProgressSignForFeeling(FeelingType type) {
		ProgressTrendType pt = _reactor.getProgressForFeeling(type);
		String result = null;
		String k = String.format("%.2f", pt.getK());
		if (pt == ProgressTrendType.NEUTRAL) {
			result = k;
		} else if (pt == ProgressTrendType.POSITIVE) {
			result = "+" + k;
		} else
		if (pt == ProgressTrendType.NEGATIVE) {
			result = k;
		}
		return result;
	}
}