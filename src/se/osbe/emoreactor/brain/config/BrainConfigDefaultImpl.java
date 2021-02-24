package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.feelings.FeelingBuilder;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.helper.DiceHelper;

public class BrainConfigDefaultImpl implements BrainConfig {

    private final DiceHelper diceHelper;
    private final PersonalityBaseline personalityBaseline;
    private final FeelingBuilder feelingBuilder;
    private Integer perceptionAwareness;

    public BrainConfigDefaultImpl(PersonalityBaseline personalityBaseline) {
        diceHelper = new DiceHelper();
        perceptionAwareness = 0;
        this.personalityBaseline = personalityBaseline;
        feelingBuilder = new FeelingBuilder();
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
    public FeelingBuilder getFeelingBuilder() {
        return feelingBuilder;
    }

}
