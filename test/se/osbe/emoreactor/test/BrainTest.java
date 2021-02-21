package se.osbe.emoreactor.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigTimeBasedImpl;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;

public class BrainTest {

    private Brain brain; // null = default configuration
    private EmotionBuilder emotionBuilder; // Builder to create input stimula for the reactor and the brain
    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process

    @Before
    public void before() throws Exception {
        // Starting EmoReactor for John Doe, with default personality
        Personality personality = new Personality(); // no arg constructor gives default where all personality param pairs is in perfect balance (50%/50%)
        BrainConfig brainConfig = new BrainConfigTimeBasedImpl(personality);
        brain = new Brain(brainConfig); // default, if set to null
        brain.setPerceptionAwarenessPercentage(10);
        dice = brain.getBrainConfig().getDiceHelper();
        emotionBuilder = brain.getBrainConfig().getEmotionBuilder();
    }

    @Test
    public void brainSmokeTest() throws Exception {
        Map<FeelingType, Double> emotionNow;

        int intensity = 5;
        int duration = 10; //demo.dice.getRandomDoubleBetween(5d, 350d).intValue(); // 2s - 5min
        brain.setPerceptionAwarenessPercentage(100); // awareness 100%
        Perception perception = new SightPerception(
                emotionBuilder
                        .addFeelings("anger=" + intensity + "," + duration + "s;")
                        .build()
        );

        Assert.assertTrue(brain.addInboundPerception(perception));
        emotionNow = brain.tic();

        Assert.assertNotNull(emotionNow);
        brain.tic();

        Assert.assertEquals(Reactor.ProgressTrendType.POSITIVE, brain.getProgressType(FeelingType.ANGER));
    }
}
