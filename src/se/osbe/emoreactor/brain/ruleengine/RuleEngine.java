package se.osbe.emoreactor.brain.ruleengine;

import se.osbe.emoreactor.brain.emotions.EmotionType;

import java.util.Map;

public class RuleEngine {
    public Reaction feed(Map<EmotionType, Float> feelingNow, String ruleScript) {
        return new Reaction();
    }
}
