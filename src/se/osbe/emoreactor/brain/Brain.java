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

	private final String _id;
	private final Personality _personality;
	private Integer _awarenessPercentage;
	
	private final Queue<Emotion> _emoQueue;
	private Emotion _emoNow;

	private DiceHelper _dice;
	private EmotionBuilder _eb;

	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		_id = BrainHelper.createUUID();
		_personality = personality;
		_awarenessPercentage = 100; // 100%
		_emoQueue = new LinkedList<Emotion>(); // slamma upp emotions
		_dice = new DiceHelper();
		_eb = new EmotionBuilder();
		_emoNow = new Emotion("Emotion now") {
		};
	}

	public boolean addInboundPerception(Perception perception) {
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();
		boolean isAccepted = false;
		if (_dice.getRandomPercentage() <= _awarenessPercentage) {
			isAccepted = _emoQueue.offer(emoCandidate);
			if (!isAccepted) {
				System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
			}
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
		
			// The polled emotion never got attention from the brain, but gets
			// consumed and brain cannot react
			// Here is room for sub-consciousness behaviour
		inboundEmotion.getFeelings().forEach(feeling -> {
			Double feelingVal = feeling.tic();
			_emoNow.addFeeling(feeling);
			System.out.println("id: " + _id + ", " + feeling.getFeelingName() + ": " + _emoNow.getFeelings());
		});
	}

	public String getId() {
		return _id;
	}

	public Integer getAwarenessPercentage() {
		return _awarenessPercentage;
	}

	public Emotion getEmoNow() {
		return _emoNow;
	}

	public static void main(String[] args) throws ReactorException {
		Personality personality = new Personality();
		Brain brain = new Brain(personality);
		System.out.println("Personality:\n" + brain.getPersonality());
		System.out.println("---------------------------------------------------");
		System.out.println("Awareness: " + brain.getAwarenessPercentage() + "%");
		System.out.println("---------------------------------------------------");
		EmotionBuilder eb = new EmotionBuilder();
		
		Emotion emo = eb.addFeelings("Afra=100;").build("AfraidEmo");
		Perception perception = new SightPerception(emo);
		brain.addInboundPerception(perception);
		
		for (int i = 0; i < 10; i++) {
			brain.tic();
			System.out.println(brain.getEmoNow());
		}
	}
}