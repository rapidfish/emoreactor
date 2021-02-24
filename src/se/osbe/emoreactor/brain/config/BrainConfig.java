package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.feelings.FeelingBuilder;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.helper.DiceHelper;

public interface BrainConfig {

    DiceHelper getDiceHelper();

    Integer getPerceptionAwareness();

    void setPerceptionAwareness(int i);

    PersonalityBaseline getPersonality();

    FeelingBuilder getFeelingBuilder();

}