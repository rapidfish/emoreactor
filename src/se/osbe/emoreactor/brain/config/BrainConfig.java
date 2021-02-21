package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.perception.PerceptionBuilder;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Queue;

public interface BrainConfig {

    DiceHelper getDiceHelper();

    Integer getPerceptionAwareness();

    void setPerceptionAwareness(int i);

    Personality getPersonality();

    Queue<Emotion> getPerceptionQueue();

    PerceptionBuilder getPerceptionBuilder();

    EmotionBuilder getEmotionBuilder();

}