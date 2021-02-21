package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.perception.PerceptionBuilder;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.LinkedList;
import java.util.Queue;

public class BrainConfigTimeBasedImpl implements BrainConfig {

    private final DiceHelper _diceHelper;
    private final Personality _personality;
    private final Queue<Emotion> _perceptionQueue;
    private final PerceptionBuilder _perceptionBuilder;
    private Integer _perceptionAwareness;
    private EmotionBuilder _emotionBuilder;

    public BrainConfigTimeBasedImpl(Personality personality) {
        _perceptionQueue = new LinkedList<Emotion>();
        _diceHelper = new DiceHelper();
        _perceptionAwareness = 0;
        _personality = personality;
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
    public EmotionBuilder getEmotionBuilder() {
        return _emotionBuilder;
    }

    @Override
    public PerceptionBuilder getPerceptionBuilder() {
        return _perceptionBuilder;
    }
}
