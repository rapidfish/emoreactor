package se.osbe.emoreactor.brain.config;

import lombok.Builder;
import lombok.Data;
import se.osbe.emoreactor.brain.Personality;

@Data
@Builder
public class CustomBrainConfig implements BrainConfig {
    private float defaultAwareness = 0f;
    private String defaultBrainName = "John Doe";
    private Personality defaultPersonality = new Personality();
}
