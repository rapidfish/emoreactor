package se.osbe.emoreactor.brain.feelings;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeelingBuilderTest {

    @Test
    public void feelingBuilderTest() {
        Feeling feeling = Feeling.builder()
                // using builder
                .addEmotion(Emotion.builder()
                        .emotionType(EmotionType.AGONY)
                        .durationTime(50000)
                        .amplitudePeak(10)
                        .amplitudeSustain(5)
                        .attack(1)
                        .decay(2)
                        .sustain(3)
                        .release(4)
                        .build())
                // using all arg constructor directly
                .addEmotion(new Emotion(EmotionType.AFRAID,
                        60000,
                        100,
                        50,
                        20,
                        20,
                        30,
                        30))
                .build();

        assertTrue(new Date().getTime() >= feeling.getInitialTimeStamp());
        assertTrue(!feeling.getEmotions().isEmpty());
        assertEquals(new Emotion(
                        EmotionType.AGONY,
                        50000, 10,
                        5, 10, 20, 30, 40
                ),
                feeling.getEmotions().get(0));
    }
}