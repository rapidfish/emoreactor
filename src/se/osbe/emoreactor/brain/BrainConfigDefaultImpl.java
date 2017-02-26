package se.osbe.emoreactor.brain;

import java.util.LinkedList;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.perception.PerceptionBuilder;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.BrainHelperImpl;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.DiceHelperImpl;
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

	public BrainConfigDefaultImpl(Personality personality) {
		_perceptionQueue = new LinkedList<Emotion>();
		_brainHelper = new BrainHelperImpl();
		_diceHelper = new DiceHelperImpl();
		_perceptionAwareness = new Integer(100); // 100% awareness
		_personality = personality;
		_ticker = new TimeTickerImpl();
		_perceptionBuilder = new PerceptionBuilder();
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
	public PerceptionBuilder getPerceptionBuilder() {
		return _perceptionBuilder;
	}
}
