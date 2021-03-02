package se.osbe.emoreactor.brain.feelings.emotions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.osbe.emoreactor.brain.emotions.*;
import se.osbe.emoreactor.brain.reactor.ReactorException;

import java.util.Arrays;
import java.util.List;

public class FeelingTest {

    private List<AbstractEmotion> _emotionList;

    @Before
    public void setUp() throws Exception {
        Float amplitude = 100.0f;
        long initialTime = 1;
        long duration = 100;
        _emotionList = Arrays.asList(
                new AfraidEmotion(amplitude, initialTime, duration),
                new AgonyEmotion(amplitude, initialTime, duration),
                new AliveEmotion(amplitude, initialTime, duration),
                new AngerEmotion(amplitude, initialTime, duration),
                new ConfusedEmotion(amplitude, initialTime, duration),
                new DepressedEmotion(amplitude, initialTime, duration),
                new HappyEmotion(amplitude, initialTime, duration),
                new HelplessEmotion(amplitude, initialTime, duration),
                new HurtEmotion(amplitude, initialTime, duration),
                new IndifferentEmotion(amplitude, initialTime, duration),
                new InterestedEmotion(amplitude, initialTime, duration),
                new JudgementalEmotion(amplitude, initialTime, duration),
                new LovingEmotion(amplitude, initialTime, duration),
                new OpenEmotion(amplitude, initialTime, duration),
                new PeacefulEmotion(amplitude, initialTime, duration),
                new PositiveEmotion(amplitude, initialTime, duration),
                new RelaxedEmotion(amplitude, initialTime, duration),
                new SadEmotion(amplitude, initialTime, duration),
                new StrongEmotion(amplitude, initialTime, duration),
                new ReliefEmotion(amplitude, initialTime, duration)
        );
    }

    @Test
    public void emoAmplitudeTest() throws ReactorException {
        final Float amplitude = 100.0f;
        _emotionList.forEach(emo -> {
            Assert.assertEquals(amplitude, emo.getAmplitude());
        });
    }

    @Test
    public void emoInitialTimeTest() throws ReactorException {
        final long initialTime = 1;
        _emotionList.forEach(emo -> {
            Assert.assertEquals(initialTime, emo.getInitialTime());
        });
    }

    @Test
    public void emoDurationValueTest() throws ReactorException {
        final long duration = 100;
        _emotionList.forEach(emo -> Assert.assertEquals(duration, emo.getDuration()));
    }

    @Test
    public void emoFeelingTypeTest() throws ReactorException {
        _emotionList.forEach(emo -> Assert.assertNotNull(emo.getFeelingType()));
    }

    @After
    public void tearDown() throws Exception {
        _emotionList = null; // gc
    }
}