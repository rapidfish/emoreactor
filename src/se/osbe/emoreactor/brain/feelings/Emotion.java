package se.osbe.emoreactor.brain.feelings;

import lombok.Getter;

import java.util.Objects;

/**
 * Difference between feelings and emotions
 * <p>
 * https://www.quora.com/Whats-the-differences-between-feelings-and-emotions
 *
 * @author Oskar Bergstrom <rapidfish@me.com>
 */
@Getter
public class Emotion {

    private EmotionType emotionType = EmotionType.AGONY;

    // ADSR - Attack time, Decay time, Sustain level, Release time
    // See: https://en.wikipedia.org/wiki/Envelope_(music)
    private long durationTime = 60000; // highest amplitude

    private int amplitudePeak = 100; // highest amplitude
    private int amplitudeSustain = 50; // sustain amplitude

    private int attack = 20; // % default
    private int decay = 20; // % default
    private int sustain = 40; // % default
    private int release = 20; // % default

    private int sumADSR = (-1);

    private boolean isExpired = false;

    public Emotion(EmotionType emotionType, long durationTime, int amplitudePeak, int amplitudeSustain, int attack, int decay, int sustain, int release) {
        this(emotionType, durationTime, amplitudePeak, amplitudeSustain, attack, decay, sustain, release, -1);
    }

    public Emotion(EmotionType emotionType, long durationTime, int amplitudePeak, int amplitudeSustain, int attack, int decay, int sustain, int release, int sumADSR) {
        this.emotionType = emotionType;
        this.durationTime = durationTime;
        this.amplitudePeak = amplitudePeak;
        this.amplitudeSustain = amplitudeSustain;
        this.attack = attack;
        this.decay = decay;
        this.sustain = sustain;
        this.release = release;
        this.sumADSR = -1;
    }

    public static EmotionBuilder builder() {
        return new EmotionBuilder();
    }

    public float getAttackPercent() {
        return totalADSR() > 0 ? 100f * attack / totalADSR() : 0;
    }

    public float getDecayPercent() {
        return totalADSR() > 0 ? 100f * decay / totalADSR() : 0;
    }

    public float getSustainPercent() {
        return totalADSR() > 0 ? 100f * sustain / totalADSR() : 0;
    }

    public float getReleasePercent() {
        return totalADSR() > 0 ? 100f * release / totalADSR() : 0;
    }

    private int totalADSR() {
        if (sumADSR == -1) {
            sumADSR = Math.abs(attack) + Math.abs(decay) + Math.abs(sustain) + Math.abs(release);
        }
        return sumADSR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emotion emotion = (Emotion) o;
        return getDurationTime() == emotion.getDurationTime() && getAmplitudePeak() == emotion.getAmplitudePeak() && getAmplitudeSustain() == emotion.getAmplitudeSustain() && getAttackPercent() == emotion.getAttackPercent() && getDecayPercent() == emotion.getDecayPercent() && getSustainPercent() == emotion.getSustainPercent() && getReleasePercent() == emotion.getReleasePercent() && getEmotionType() == emotion.getEmotionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmotionType(), getDurationTime(), getAmplitudePeak(), getAmplitudeSustain(), getAttackPercent(), getDecayPercent(), getSustainPercent(), getReleasePercent());
    }

    public String toString() {
        return "Emotion(type=" + this.getEmotionType() + ", amplitudePeak=" + this.getAmplitudePeak() + ", amplitudeSustain=" + this.getAmplitudeSustain() + ", duration=" + this.durationTime + ", attack=" + this.getAttackPercent() + "%, decay=" + this.getDecayPercent() + "%, sustain=" + this.getSustainPercent() + "%, release=" + this.getReleasePercent() + "%)";
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired() {
        isExpired = true;
    }

    public static class EmotionBuilder {
        private EmotionType emotionType;
        private long durationTime;
        private int amplitudePeak;
        private int amplitudeSustain;
        private int attack;
        private int decay;
        private int sustain;
        private int release;
        private int sumADSR;

        EmotionBuilder() {
        }

        public EmotionBuilder emotionType(EmotionType emotionType) {
            this.emotionType = emotionType;
            return this;
        }

        public EmotionBuilder durationTime(long durationTime) {
            this.durationTime = durationTime;
            return this;
        }

        public EmotionBuilder amplitudePeak(int amplitudePeak) {
            this.amplitudePeak = amplitudePeak;
            return this;
        }

        public EmotionBuilder amplitudeSustain(int amplitudeSustain) {
            this.amplitudeSustain = amplitudeSustain;
            return this;
        }

        public EmotionBuilder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public EmotionBuilder decay(int decay) {
            this.decay = decay;
            return this;
        }

        public EmotionBuilder sustain(int sustain) {
            this.sustain = sustain;
            return this;
        }

        public EmotionBuilder release(int release) {
            this.release = release;
            return this;
        }

        public EmotionBuilder sumADSR(int sumADSR) {
            this.sumADSR = sumADSR;
            return this;
        }

        public Emotion build() {
            return new Emotion(emotionType, durationTime, amplitudePeak, amplitudeSustain, attack, decay, sustain, release, sumADSR);
        }
    }
}