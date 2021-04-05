package se.osbe.emoreactor.brain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;
import java.util.stream.Collectors;

public class BrainTest {

    private Brain brain; // null = default configuration
    private Feeling feeling;
    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process

    @Before
    public void before() throws Exception {
        // Starting EmoReactor for John Doe, with default personalityBaseline
        Personality personalityBaseline = new Personality(); // no arg constructor gives default where all personalityBaseline param pairs is in perfect balance (50%/50%)
        Personality baseline = new Personality();
        brain = new Brain("Average Joe", baseline); // default, if set to null
        brain.setAwarenessPercentage(100);
        dice = brain.getDiceHelper();
    }

    @Test
    public void brainSmokeTest() throws Exception {
        Feeling feeling = Feeling.builder()
                .addEmotion(Emotion.builder()
                        .emotionType(EmotionType.AGONY)
                        .amplitudePeak(100)
                        .amplitudeSustain(50)
                        .durationTime(30000)
                        .attack(20)
                        .decay(20)
                        .sustain(40)
                        .release(20)
                        .build()
                )
                .build();
        System.out.println("Starting brain with name: " + brain.getName());
        System.out.println("Offering feeling to brain: " + feeling);
        Thread.sleep(1000);
        brain.offerInboundFeeling(feeling);
        int timeUnit = 0;
        Map<EmotionType, Float> emotionNow = null;
        do {
            System.out.println("Feelings in progress: " + brain.getFeelings());
            emotionNow = brain.nextTurn();
            System.out.println("Time unit   : " + timeUnit++);
            System.out.println("Amplitude   : " + emotionNow);
            System.out.println("Amp (delta) : " + brain.getInclinations());

            System.out.println();
            System.out.println("Feelings expired: " + brain.getFeelings().stream().filter(f -> f.isExpired()).map(f -> f.getUID()).collect(Collectors.joining(", ")));
            Thread.sleep(1000);
        } while (!emotionNow.isEmpty());

        Assert.assertTrue(true);
    }

    @Test
    public void testCalculateAmplitudeTest() {
        int attack = 3000; // sec
        int decay = 5000; // sec
        int sustain = 10000; // sec
        int release = 6000; // sec
        Emotion emo = Emotion.builder()
                .emotionType(EmotionType.AGONY)
                .amplitudePeak(100)
                .amplitudeSustain(50)
                .durationTime(10000)
                .attack(5000)
                .decay(5000)
                .sustain(10000)
                .release(5000)
                .build();

        Assert.assertEquals(Float.valueOf(0), Float.valueOf(brain.calculateAmplitude(emo, -1)));
        Assert.assertEquals(Float.valueOf(0), Float.valueOf(brain.calculateAmplitude(emo, 0)));

        // Start of duration
        Assert.assertEquals(Float.valueOf(2), Float.valueOf(brain.calculateAmplitude(emo, 49)));
        Assert.assertEquals(Float.valueOf(3), Float.valueOf(brain.calculateAmplitude(emo, 50)));

        // Attack
        Assert.assertEquals(Float.valueOf(25), Float.valueOf(brain.calculateAmplitude(emo, 500)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 1000)));
        Assert.assertEquals(Float.valueOf(100), Float.valueOf(brain.calculateAmplitude(emo, 2000)));

        // Decay, 5000 ms
        Assert.assertEquals(Float.valueOf(75), Float.valueOf(brain.calculateAmplitude(emo, 3000)));

        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 6000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 7000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 8000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 9000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 9900)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 10000)));

        // Sustain, 10000 ms
        Assert.assertEquals(Float.valueOf(75), Float.valueOf(brain.calculateAmplitude(emo, 10001)));

        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 11000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 12000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 13000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 14000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 15000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 16000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 17000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 18000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 19000)));
        Assert.assertEquals(Float.valueOf(50), Float.valueOf(brain.calculateAmplitude(emo, 20000)));

        // Release, 5000 ms
        Assert.assertEquals(Float.valueOf(49), Float.valueOf(brain.calculateAmplitude(emo, 20001)));
        Assert.assertEquals(Float.valueOf(40), Float.valueOf(brain.calculateAmplitude(emo, 21000)));
        Assert.assertEquals(Float.valueOf(30), Float.valueOf(brain.calculateAmplitude(emo, 22000)));
        Assert.assertEquals(Float.valueOf(20), Float.valueOf(brain.calculateAmplitude(emo, 23000)));
        Assert.assertEquals(Float.valueOf(10), Float.valueOf(brain.calculateAmplitude(emo, 24000)));
        Assert.assertEquals(Float.valueOf(0), Float.valueOf(brain.calculateAmplitude(emo, 25000)));

        Assert.assertEquals(Float.valueOf(-1), Float.valueOf(brain.calculateAmplitude(emo, 25001)));
    }
}
