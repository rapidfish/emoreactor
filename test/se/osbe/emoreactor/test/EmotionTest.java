package se.osbe.emoreactor.test;

import java.util.LinkedList;
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
import se.osbe.emoreactor.brain.emotions.feelings.SadFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.StrongFeeling;
import se.osbe.emoreactor.brain.reactor.ReactorException;

public class EmotionTest {

	private List<AbstractFeeling> _emotionList;

	@Before
	public void setUp() throws Exception {
		_emotionList = new LinkedList<AbstractFeeling>();
	}

	@Test
	public void constructorTest() throws ReactorException {

		Integer value = 100;

		AbstractFeeling emo0 = new AfraidFeeling(value);
		AbstractFeeling emo1 = new AgonyFeeling(value);
		AbstractFeeling emo2 = new AliveFeeling(value);
		AbstractFeeling emo3 = new AngerFeeling(value);
		AbstractFeeling emo4 = new ConfusedFeeling(value);
		AbstractFeeling emo5 = new DepressedFeeling(value);
		AbstractFeeling emo6 = new HappyFeeling(value);
		AbstractFeeling emo7 = new HelplessFeeling(value);
		AbstractFeeling emo8 = new HurtFeeling(value);
		AbstractFeeling emo9 = new IndifferentFeeling(value);
		AbstractFeeling emo10 = new InterestedFeeling(value);
		AbstractFeeling emo11 = new JudgementalFeeling(value);
		AbstractFeeling emo12 = new LovingFeeling(value);
		AbstractFeeling emo13 = new OpenFeeling(value);
		AbstractFeeling emo14 = new PeacefulFeeling(value);
		AbstractFeeling emo15 = new PositiveFeeling(value);
		AbstractFeeling emo16 = new RelaxedFeeling(value);
		AbstractFeeling emo17 = new SadFeeling(value);
		AbstractFeeling emo18 = new StrongFeeling(value);

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

		Assert.assertEquals(value, emo0.getLevel());
		Assert.assertEquals(value, emo1.getLevel());
		Assert.assertEquals(value, emo2.getLevel());
		Assert.assertEquals(value, emo3.getLevel());
		Assert.assertEquals(value, emo4.getLevel());
		Assert.assertEquals(value, emo5.getLevel());
		Assert.assertEquals(value, emo6.getLevel());
		Assert.assertEquals(value, emo7.getLevel());
		Assert.assertEquals(value, emo8.getLevel());
		Assert.assertEquals(value, emo9.getLevel());
		Assert.assertEquals(value, emo10.getLevel());
		Assert.assertEquals(value, emo11.getLevel());
		Assert.assertEquals(value, emo12.getLevel());
		Assert.assertEquals(value, emo13.getLevel());
		Assert.assertEquals(value, emo14.getLevel());
		Assert.assertEquals(value, emo15.getLevel());
		Assert.assertEquals(value, emo16.getLevel());
		Assert.assertEquals(value, emo17.getLevel());
		Assert.assertEquals(value, emo18.getLevel());
	}

	@After
	public void tearDown() throws Exception {
		_emotionList = null; // gc
	}
}