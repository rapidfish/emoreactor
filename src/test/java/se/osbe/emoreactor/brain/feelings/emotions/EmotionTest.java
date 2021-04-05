package se.osbe.emoreactor.brain.feelings.emotions;

import org.junit.Assert;
import org.junit.Test;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;

import java.util.Arrays;

public class EmotionTest {

    @Test
    public void emoConstructorTest() {
        Arrays.asList(
                new Emotion(EmotionType.AGONY, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.ALIVE, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.ANGER, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.CONFUSED, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.DEPRESSED, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.HAPPY, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.HELPLESS, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.HURT, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.INDIFFERENT, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.INTERESTED, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.JUDGEMENTAL, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.LOVING, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.OPEN, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.PEACEFUL, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.POSITIVE, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.RELAXED, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.SADNESS, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.STRONG, 60000, 10, 5, 1, 2, 3, 4),
                new Emotion(EmotionType.RELIEF, 60000, 10, 5, 1, 2, 3, 4)
        ).forEach(emo -> {
            Assert.assertEquals(Float.valueOf(10), Float.valueOf(emo.getAmplitudePeak()));
            Assert.assertEquals(Float.valueOf(5), Float.valueOf(emo.getAmplitudeSustain()));
            Assert.assertEquals(Float.valueOf(1), Float.valueOf(emo.getAttack()));
            Assert.assertEquals(Float.valueOf(2), Float.valueOf(emo.getDecay()));
            Assert.assertEquals(Float.valueOf(3), Float.valueOf(emo.getSustain()));
            Assert.assertEquals(Float.valueOf(4), Float.valueOf(emo.getRelease()));
        });
    }

    @Test
    public void emoBuilderTest() {
        Arrays.asList(
                Emotion.builder().emotionType(EmotionType.AFRAID).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.AGONY).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.ALIVE).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.ANGER).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.CONFUSED).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.DEPRESSED).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HAPPY).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HELPLESS).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HURT).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.INDIFFERENT).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.INTERESTED).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.JUDGEMENTAL).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.LOVING).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.OPEN).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.PEACEFUL).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.POSITIVE).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.RELAXED).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.SADNESS).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.STRONG).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.RELIEF).amplitudePeak(10).amplitudeSustain(5).attack(1).decay(2).sustain(3).release(4).build()
        ).forEach(emo -> {
            Assert.assertEquals(Float.valueOf(10), Float.valueOf(emo.getAmplitudePeak()));
            Assert.assertEquals(Float.valueOf(5), Float.valueOf(emo.getAmplitudeSustain()));
            Assert.assertEquals(Float.valueOf(1), Float.valueOf(emo.getAttack()));
            Assert.assertEquals(Float.valueOf(2), Float.valueOf(emo.getDecay()));
            Assert.assertEquals(Float.valueOf(3), Float.valueOf(emo.getSustain()));
            Assert.assertEquals(Float.valueOf(4), Float.valueOf(emo.getRelease()));
        });
    }
}