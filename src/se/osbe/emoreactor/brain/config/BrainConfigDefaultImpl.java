package se.osbe.emoreactor.brain.config;

import java.util.LinkedList;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.perception.PerceptionBuilder;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.BrainHelperImpl;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.Ticker;
import se.osbe.emoreactor.helper.TimeTickerImpl;

public class BrainConfigDefaultImpl implements BrainConfig {

	private final BrainHelper _brainHelper;
	private final DiceHelper _diceHelper;
	private Integer _perceptionAwareness;
	private final Personality _personality;
	private final Queue<Emotion> _perceptionQueue;
	private final Ticker _ticker;
	private final PerceptionBuilder _perceptionBuilder;
	private EmotionBuilder _emotionBuilder;
	private boolean _isUseSyncTimeInReactor;

	public BrainConfigDefaultImpl(Personality personality) {
		_perceptionQueue = new LinkedList<Emotion>();
		_brainHelper = new BrainHelperImpl();
		_diceHelper = new DiceHelper();
		_perceptionAwareness = 100; // 100% awareness
		_personality = personality;
		_ticker = new TimeTickerImpl();
		_emotionBuilder = new EmotionBuilder();
		_perceptionBuilder = new PerceptionBuilder();
		_isUseSyncTimeInReactor = false;
	}

	@Override
	public BrainHelper getBrainHelper() {
		return _brainHelper;
	}

	@Override
	public DiceHelper getDiceHelper() {
		return _diceHelper;
	}

	@Override
	public Integer getPerceptionAwareness() {
		return _perceptionAwareness;
	}

	@Override
	public void setPerceptionAwareness(int percentage) {
		_perceptionAwareness = (percentage >= 0 && percentage <= 100) ? percentage : 0;
	}

	@Override
	public Personality getPersonality() {
		return _personality;
	}

	@Override
	public Queue<Emotion> getPerceptionQueue() {
		return _perceptionQueue;
	}
	
	@Override
	public Ticker getTicker() {
		return _ticker;
	}

	@Override
	public EmotionBuilder getEmotionBuilder() {
		return _emotionBuilder;
	}

	@Override
	public PerceptionBuilder getPerceptionBuilder() {
		return _perceptionBuilder;
	}

	@Override
	public boolean isUseSyncTimeInReactor() {
		return _isUseSyncTimeInReactor;
	}

	@Override
	public void setUseSyncTimeInReactor(boolean sync) {
		_isUseSyncTimeInReactor = sync;
	}
}
