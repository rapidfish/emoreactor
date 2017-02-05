package se.osbe.emoreactor.brain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.PerceptionType;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public class Brain {

	private final String 			_id;
	private final Personality 		_personality;
	private Emotion 				_emotionRegister;
	private final Queue<Emotion> 	_emoQueue;

	private DiceHelper 				_dice;
	private Integer 				_emoSpanSize;
	private Integer 				_emoSpanSizeMax;
	private Integer 				_awarenessPercentage;
	private EmotionBuilder _eb;

	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		
		// Meta
		_id = BrainHelper.createUUID();

		// Brain configuration - start values
		_personality = personality;
		_emoSpanSize = personality.getEmoSpanSize(); // EMO_SPAN_SIZE_DEFAULT;
		_emoSpanSizeMax = personality.getEmoSpanSizeMax(); // EMO_SPAN_SIZE_DEFAULT;
		_awarenessPercentage = personality.getAwarenessPercentage(); //AWARENESS_PERCENTAGE_DEFAULT;

		// Setup central part for processing emotions
		_emoQueue = new LinkedList<Emotion>();
		
		// Setup Helpers
		_dice = new DiceHelper();
		_eb = new EmotionBuilder();
		_emotionRegister = null;
	}

	public boolean feedPerceptionToBrain(Perception perception) {

		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();

			// process emo according to personality here!
			boolean isAccepted = false;
			if (_emoSpanSize < _emoSpanSizeMax) {
				isAccepted = _emoQueue.offer(emoCandidate);
				if (!isAccepted) {
					System.err.println("WARNING! EMOTION QUEUE IS TOO BIG - OVERLOADING!!!");
				}
			} else if(_emoSpanSize >= _emoSpanSizeMax) {
				// Trauma level
			}
		return isAccepted;
	}

	public Personality getPersonality() {
		return _personality;
	}

	public void tic() {
		Emotion inboundEmotion = _emoQueue.poll();
		if (inboundEmotion == null) {
			return;
		}

		// Does the perception get through the attention barrier and create an
		// emotion reaction?
		// If a perception is detected, a reaction can not be avoided!
		boolean perceptionDetected = false;
		perceptionDetected = _dice.percentageChance(_awarenessPercentage);
		if (perceptionDetected) {
			List<Feeling> inboundFeelings = inboundEmotion.getEmotionsAsList();
			_eb.reset();
			_eb.addFeelings();
		} else {
			// The polled emotion never got attention from the brain, but gets
			// consumed and brain cannot react
			// Here is room for sub-consciousness behaviour
		}
	}

	public String getId() {
		return _id;
	}

	public Integer getEmoSpanSize() {
		return _emoSpanSize;
	}

	public Integer getEmoSpanSizeMax() {
		return _emoSpanSizeMax;
	}

	public Integer getAwarenessPercentage() {
		return _awarenessPercentage;
	}

	public static void main(String[] args) throws ReactorException {
		Personality personality = new Personality();
		Brain brain = new Brain(personality);
		System.out.println("Personality:\n" + brain.getPersonality());
		System.out.println("---------------------------------------------------");
		System.out.println("Awareness: " + brain.getAwarenessPercentage() + "%");
		System.out.println("---------------------------------------------------");
		EmotionBuilder eb = new EmotionBuilder();
		for (int i = 0; i < 10; i++) {
			Emotion emo = eb
					.addFeelings(
							"Agony=?;Anger=?;Depressed=?;Confused=?;Helpless=?;Indifferent=?;Afraid=?;Hurt=?;Sad=?;Judgemental=?;Open=?;Loving=?;Happy=?;Interested=?;Alive=?;Positive=?;Peaceful=?;Strong=?;Relaxed=?")
					.build("Emo" + i);
			Perception perception = new SightPerception(emo);
			brain.feedPerceptionToBrain(perception);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(brain._emoQueue.poll());
		}
	}
}