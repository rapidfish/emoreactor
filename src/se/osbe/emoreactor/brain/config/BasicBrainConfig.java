package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.Personality;

public class BasicBrainConfig implements BrainConfig {

    private final float defaultAwareness = 0f;
    private final String defaultBrainName = "John Doe";
    private final Personality defaultPersonality = new Personality();
    ;

    @Override
    public float getDefaultAwareness() {
        return defaultAwareness;
    }

    @Override
    public String getDefaultBrainName() {
        return defaultBrainName;
    }

    @Override
    public Personality getDefaultPersonality() {
        return defaultPersonality;
    }
}
