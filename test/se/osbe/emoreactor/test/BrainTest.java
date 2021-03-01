package se.osbe.emoreactor.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigDefaultImpl;
import se.osbe.emoreactor.brain.emotions.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.brain.feelings.FeelingBuilder;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;

public class BrainTest {

    private Brain brain; // null = default configuration
    private FeelingBuilder feelingBuilder; // Builder to create input stimula for the reactor and the brain
    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process

    @Before
    public void before() throws Exception {
        // Starting EmoReactor for John Doe, with default personalityBaseline
        PersonalityBaseline personalityBaseline = new PersonalityBaseline(); // no arg constructor gives default where all personalityBaseline param pairs is in perfect balance (50%/50%)
        BrainConfig brainConfig = new BrainConfigDefaultImpl(personalityBaseline);
        brain = new Brain(brainConfig); // default, if set to null
        brain.setPerceptionAwarenessPercentage(10);
        dice = brain.getBrainConfig().getDiceHelper();
        feelingBuilder = brain.getBrainConfig().getFeelingBuilder();
    }

    @Test
    public void brainSmokeTest() throws Exception {
        Map<EmotionType, Float> emotionNow;

        int intensity = 5;
        int duration = 10; //demo.dice.getRandomDoubleBetween(5d, 350d).intValue(); // 2s - 5min
        brain.setPerceptionAwarenessPercentage(100); // awareness 100%

        Feeling feeling = feelingBuilder
                .addFeelings("anger=" + intensity + "," + duration + "s;")
                .build();

        Assert.assertTrue(brain.offerInboundFeeling(feeling));
        emotionNow = brain.tic();

        Assert.assertNotNull(emotionNow);
        brain.tic();

        Assert.assertEquals(Reactor.ProgressTrendType.POSITIVE, brain.getProgressType(EmotionType.ANGER));
    }
}
