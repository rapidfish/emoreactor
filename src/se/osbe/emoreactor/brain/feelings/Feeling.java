package se.osbe.emoreactor.brain.feelings;

import java.util.*;

public class Feeling implements Cloneable {

    private final long timestamp;
    private final List<Emotion> emotions;
    private float amplitudeLevel;

    // closed
    private Feeling() {
        this(null, null);
    }

    Feeling(List<Emotion> emotions, Long timestamp) {
        this.timestamp = Objects.nonNull(timestamp) ? timestamp : new Date().getTime();
        this.emotions = Objects.nonNull(emotions) ? emotions : new LinkedList<>();
        amplitudeLevel = 0;
    }

    public static FeelingBuilder builder() {
        return new FeelingBuilder();
    }

    public float getTimestamp() {
        return timestamp;
    }

    public List<Emotion> getEmotions() {
        return emotions;
    }

    public static class FeelingBuilder {
        private long timestamp;
        private List<Emotion> emotions;

        private float amplitude = 100;
        private int attack = 10; // % of time to reach 100% amp
        private int decay = 10; // % of time to reach sustain level
        private float sustain = 60;
        private int release = 20;

        FeelingBuilder() {
            emotions = new ArrayList<>();
        }

        public FeelingBuilder emotion(Emotion emotion) {
            this.emotions.add(emotion);
            return this;
        }

        public FeelingBuilder amplitude(float amplitude) {
            this.amplitude = amplitude;
            return this;
        }

        public FeelingBuilder adsr(int attack, int decay, float sustain, int release) {
            this.attack = attack;
            this.decay = decay;
            this.sustain = sustain;
            this.release = release;
            return this;
        }

        public FeelingBuilder decay(int decay) {
            this.decay = decay;
            return this;
        }

        public FeelingBuilder sustain(float sustain) {
            this.sustain = sustain;
            return this;
        }

        public FeelingBuilder release(int release) {
            this.release = release;
            return this;
        }

        public FeelingBuilder emotions(List<Emotion> emotions) {
            this.emotions.addAll(emotions);
            return this;
        }

        public Feeling build() {
            return new Feeling(emotions, timestamp);
        }
    }
}