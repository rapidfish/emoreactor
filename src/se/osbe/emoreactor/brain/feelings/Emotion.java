package se.osbe.emoreactor.brain.feelings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Difference between feelings and emotions
 * <p>
 * https://www.quora.com/Whats-the-differences-between-feelings-and-emotions
 *
 * @author Oskar Bergstrom <rapidfish@me.com>
 */
@AllArgsConstructor
@Getter
@Builder
public class Emotion {
    private final EmotionType emotionType;

    // ADSR - Attack time, Decay time, Sustain level, Release time
    // See: https://en.wikipedia.org/wiki/Envelope_(music)
    private int attack; // time
    private int decay; // time
    private int sustain; // level
    private int release; // time

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Emotion [")
                .append(emotionType.getMnmonic())
                .append("], attack=(")
                .append(attack)
                .append("), decay=(")
                .append(decay)
                .append(") , sustain=(")
                .append(sustain)
                .append("), release=")
                .append(release)
                .append(")")
                .toString();
    }
}
