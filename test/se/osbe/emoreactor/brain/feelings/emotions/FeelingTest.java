package se.osbe.emoreactor.brain.feelings.emotions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.osbe.emoreactor.brain.emotions.*;
import se.osbe.emoreactor.brain.reactor.ReactorException;

import java.util.ArrayList;
import java.util.List;

public class FeelingTest {

    private List<AbstractEmotion> _emotionList;

    @Before
    public void setUp() throws Exception {
        _emotionList = new ArrayList<>();
        Double amplitude = 100.0;
        long initialTime = 1;
        long duration = 100;

        AbstractEmotion emo0 = new AfraidEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo1 = new AgonyEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo2 = new AliveEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo3 = new AngerEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo4 = new ConfusedEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo5 = new DepressedEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo6 = new HappyEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo7 = new HelplessEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo8 = new HurtEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo9 = new IndifferentEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo10 = new InterestedEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo11 = new JudgementalEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo12 = new LovingEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo13 = new OpenEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo14 = new PeacefulEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo15 = new PositiveEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo16 = new RelaxedEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo17 = new SadEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo18 = new StrongEmotion(amplitude, initialTime, duration);
        AbstractEmotion emo19 = new ReliefEmotion(amplitude, initialTime, duration);

        _emotionList.add(emo0);
        _emotionList.add(emo1);
        _emotionList.add(emo2);
        _emotionList.add(emo3);
        _emotionList.add(emo4);
        _emotionList.add(emo5);
        _emotionList.add(emo6);
        _emotionList.add(emo7);
        _emotionList.add(emo8);
        _emotionList.add(emo9);
        _emotionList.add(emo10);
        _emotionList.add(emo11);
        _emotionList.add(emo12);
        _emotionList.add(emo13);
        _emotionList.add(emo14);
        _emotionList.add(emo15);
        _emotionList.add(emo16);
        _emotionList.add(emo17);
        _emotionList.add(emo18);
        _emotionList.add(emo19);
    }

    @Test
    public void emoAmplitudeTest() throws ReactorException {
        Double amplitude = 100.0;
        _emotionList.forEach(emo -> {
            Assert.assertEquals(amplitude, emo.getAmplitude());
        });
    }

    @Test
    public void emoInitialTimeTest() throws ReactorException {
        long initialTime = 1;
        _emotionList.forEach(emo -> {
            Assert.assertEquals(initialTime, emo.getInitialTime());
        });
    }

    @Test
    public void emoDurationValueTest() throws ReactorException {
        long duration = 100;
        _emotionList.forEach(emo -> {
            Assert.assertEquals(duration, emo.getDuration());
        });
    }

    @Test
    public void emoFeelingTypeTest() throws ReactorException {
        _emotionList.forEach(emo -> {
            Assert.assertNotNull(emo.getFeelingType());
        });
    }

    @After
    public void tearDown() throws Exception {
        _emotionList = null; // gc
    }
}