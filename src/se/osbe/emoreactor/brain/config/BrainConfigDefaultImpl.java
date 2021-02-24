package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.LinkedList;
import java.util.Queue;

public class BrainConfigDefaultImpl implements BrainConfig {

    private final DiceHelper diceHelper;
    private final PersonalityBaseline personalityBaseline;
    private final Queue<Emotion> perceptionQueue;
    private Integer perceptionAwareness;
    private EmotionBuilder emotionBuilder;

    public BrainConfigDefaultImpl(PersonalityBaseline personalityBaseline) {
        perceptionQueue = new LinkedList();
        diceHelper = new DiceHelper();
        perceptionAwareness = 0;
        this.personalityBaseline = personalityBaseline;
        emotionBuilder = new EmotionBuilder();
    }

    @Override
    public DiceHelper getDiceHelper() {
        return diceHelper;
    }

    @Override
    public Integer getPerceptionAwareness() {
        return perceptionAwareness;
    }

    @Override
    public void setPerceptionAwareness(int percentage) {
        perceptionAwareness = (percentage >= 0 && percentage <= 100) ? percentage : 0;
    }

    @Override
    public PersonalityBaseline getPersonality() {
        return personalityBaseline;
    }

    @Override
    public EmotionBuilder getEmotionBuilder() {
        return emotionBuilder;
    }

}
