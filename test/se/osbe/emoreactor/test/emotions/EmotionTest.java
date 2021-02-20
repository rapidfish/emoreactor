package se.osbe.emoreactor.test.emotions;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.osbe.emoreactor.brain.emotions.feelings.AbstractFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AfraidFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AgonyFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AliveFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AngerFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.ConfusedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.DepressedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.HappyFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.HelplessFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.HurtFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.IndifferentFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.InterestedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.JudgementalFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.LovingFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.OpenFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.PeacefulFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.PositiveFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.RelaxedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.ReliefFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.SadFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.StrongFeeling;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class EmotionTest {

	private List<AbstractFeeling> _emotionList;

	@Before
	public void setUp() throws Exception {
		_emotionList = new ArrayList<AbstractFeeling>();
		Double amplitude = 100.0;
		long initialTime = 1;
		long duration = 100;

		AbstractFeeling emo0 = new AfraidFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo1 = new AgonyFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo2 = new AliveFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo3 = new AngerFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo4 = new ConfusedFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo5 = new DepressedFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo6 = new HappyFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo7 = new HelplessFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo8 = new HurtFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo9 = new IndifferentFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo10 = new InterestedFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo11 = new JudgementalFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo12 = new LovingFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo13 = new OpenFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo14 = new PeacefulFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo15 = new PositiveFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo16 = new RelaxedFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo17 = new SadFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo18 = new StrongFeeling(amplitude, initialTime, duration);
		AbstractFeeling emo19 = new ReliefFeeling(amplitude, initialTime, duration);
		
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