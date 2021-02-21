package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.perception.PerceptionBuilder;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.DiceHelper;
import se.osbe.emoreactor.helper.Ticker;
import se.osbe.emoreactor.helper.TurnBasedTickerImpl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Instead of using clock based time as x-axis it uses a manual counter.
 * Useful when want to run reactor as fast as possible (not wait for clock to tic)
 */
public class BrainConfigTurnBasedImpl implements BrainConfig {

    private final DiceHelper _diceHelper;
    private final Personality _personality;
    private final Queue<Emotion> _perceptionQueue;
    private final Ticker _ticker;
    private Integer _perceptionAwareness;
    private EmotionBuilder _emotionBuilder;
    private PerceptionBuilder _perceptionBuilder;

    public BrainConfigTurnBasedImpl(Personality personality) {
        _perceptionQueue = new LinkedList<Emotion>();
        _diceHelper = new DiceHelper();
        _perceptionAwareness = 0;
        _personality = personality;
        _ticker = new TurnBasedTickerImpl();
        _emotionBuilder = new EmotionBuilder();
        _perceptionBuilder = new PerceptionBuilder();
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
}
