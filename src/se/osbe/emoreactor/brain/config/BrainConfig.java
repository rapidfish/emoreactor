package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.Personality;

public interface BrainConfig {
    // Awareness
    float getDefaultAwareness();

    // Naming
    String getDefaultBrainName();

    // Characteristics
    Personality getDefaultPersonality();
}
