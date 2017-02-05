package se.osbe.emoreactor.brain;

import java.util.LinkedList;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.PerceptionType;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public class Brain {

	private final int EMO_SPAN_SIZE_MAX = 20;
	private final int EMO_SPAN_SIZE_DEFAULT = 3;
	private final int AWARENESS_PERCENTAGE_DEFAULT = 100; // 70%

	private final String _id;
	private final Personality _personality;
	private final Queue<Emotion> _emoQueue;

	private DiceHelper _dice;
	private int _emoSpanSize;
	private int _awareness;

	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		_id = BrainHelper.createUUID();
		_personality = personality;
		_emoQueue = new LinkedList<Emotion>();
		_emoSpanSize = EMO_SPAN_SIZE_DEFAULT;
		_awareness = AWARENESS_PERCENTAGE_DEFAULT;
		_dice = new DiceHelper();
	}

	public void tic() {
		;
	}

	public boolean feedPerceptionToBrain(Perception perception) {

		boolean perceptionDetected = false;
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();

		// does the perception got the brains attention?
		perceptionDetected = _dice.percentageChance(_awareness);

		// if a perception is detected, a reaction can not be avoided!
		if (perceptionDetected) {
			// process emo according to personality here!
			Emotion effectiveEmoCandidate = emoCandidate;
			if (_emoSpanSize < EMO_SPAN_SIZE_MAX) {
//				System.out.println("I " + perceptionType.getDescription() + " something");
//				System.out.println("emo offerd to queue: " + effectiveEmoCandidate);
				perceptionDetected = _emoQueue.offer(effectiveEmoCandidate);
				if (!perceptionDetected) {
					System.err.println("WARNING! EMOTION QUEUE IS TOO BIG - OVERLOADING!!!");
				}
			}
		} else {
			System.out.println("The perception: " + perceptionType.name() + ", went undetected");
		}
		return perceptionDetected;
	}

	public Personality getPersonality() {
		return _personality;
	}

	public String getId() {
		return _id;
	}

	public static void main(String[] args) throws ReactorException {
		Personality personality = new Personality();
		Brain brain = new Brain(personality);
		System.out.println("Personality:\n" + brain.getPersonality());
		System.out.println("---------------------------------------------------");
		System.out.println("Awareness: " + brain._awareness + "%");
		System.out.println("---------------------------------------------------");
		EmotionBuilder eb = new EmotionBuilder();
		for(int i=0; i < 10; i++) {
			Emotion emo = eb.addFeelings("agony=?;afraid=?").build("Emo" + i);
			Perception perception = new SightPerception(emo);
			boolean isAdded = brain.feedPerceptionToBrain(perception);
		}
		
		for(int i=0; i < 10; i++) {
			System.out.println(brain._emoQueue.poll());
		}
	}
}