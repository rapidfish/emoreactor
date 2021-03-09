package se.osbe.emoreactor.brain.feelings.emotions;

import org.junit.Assert;
import org.junit.Test;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;

import java.util.Arrays;

public class FeelingTest {


    @Test
    public void emoConstructorTest() {
        Arrays.asList(
                new Emotion(EmotionType.AGONY, 1, 2, 3, 4),
                new Emotion(EmotionType.ALIVE, 1, 2, 3, 4),
                new Emotion(EmotionType.ANGER, 1, 2, 3, 4),
                new Emotion(EmotionType.CONFUSED, 1, 2, 3, 4),
                new Emotion(EmotionType.DEPRESSED, 1, 2, 3, 4),
                new Emotion(EmotionType.HAPPY, 1, 2, 3, 4),
                new Emotion(EmotionType.HELPLESS, 1, 2, 3, 4),
                new Emotion(EmotionType.HURT, 1, 2, 3, 4),
                new Emotion(EmotionType.INDIFFERENT, 1, 2, 3, 4),
                new Emotion(EmotionType.INTERESTED, 1, 2, 3, 4),
                new Emotion(EmotionType.JUDGEMENTAL, 1, 2, 3, 4),
                new Emotion(EmotionType.LOVING, 1, 2, 3, 4),
                new Emotion(EmotionType.OPEN, 1, 2, 3, 4),
                new Emotion(EmotionType.PEACEFUL, 1, 2, 3, 4),
                new Emotion(EmotionType.POSITIVE, 1, 2, 3, 4),
                new Emotion(EmotionType.RELAXED, 1, 2, 3, 4),
                new Emotion(EmotionType.SADNESS, 1, 2, 3, 4),
                new Emotion(EmotionType.STRONG, 1, 2, 3, 4),
                new Emotion(EmotionType.RELIEF, 1, 2, 3, 4)
        ).forEach(emo -> {
            Assert.assertEquals(1, emo.getAttack());
            Assert.assertEquals(2, emo.getDecay());
            Assert.assertEquals(3, emo.getSustain());
            Assert.assertEquals(4, emo.getRelease());
        });
    }

    @Test
    public void emoBuilderTest() {
        Arrays.asList(
                Emotion.builder().emotionType(EmotionType.AFRAID).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.AGONY).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.ALIVE).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.ANGER).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.CONFUSED).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.DEPRESSED).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HAPPY).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HELPLESS).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.HURT).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.INDIFFERENT).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.INTERESTED).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.JUDGEMENTAL).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.LOVING).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.OPEN).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.PEACEFUL).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.POSITIVE).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.RELAXED).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.SADNESS).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.STRONG).attack(1).decay(2).sustain(3).release(4).build(),
                Emotion.builder().emotionType(EmotionType.RELIEF).attack(1).decay(2).sustain(3).release(4).build()
        ).forEach(emo -> {
            Assert.assertEquals(1, emo.getAttack());
            Assert.assertEquals(2, emo.getDecay());
            Assert.assertEquals(3, emo.getSustain());
            Assert.assertEquals(4, emo.getRelease());
        });
    }
}