package se.osbe.emoreactor.brain;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.PerceptionType;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public class Brain {

	private final String _id;
	private final Personality _personality;
	private Integer _awarenessPercentage;
	
	private DiceHelper _dice;
	private long _mainDurationCount;
	
	private final Queue<Emotion> _perceptionQueue;
	
	private Reactor _reactor;
	
	private Emotion _emoNow;
	
	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		_id = BrainHelper.createUUID();
		_personality = personality;
		_awarenessPercentage = 100; // 100%
		_perceptionQueue = new LinkedList<Emotion>(); // incomming emo's from perception
		_dice = new DiceHelper();
		_mainDurationCount = 0;
		_reactor = new Reactor();
	}

	// Add only if within brains attention span
	public boolean addInboundPerception(Perception perception) {
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();
		boolean isAccepted = false;
		if (_dice.getRandomPercentage() <= _awarenessPercentage) {
			isAccepted = _perceptionQueue.offer(emoCandidate);
			if (!isAccepted) {
				System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
			}
		}
		return isAccepted;
	}

	public Personality getPersonality() {
		return _personality;
	}

	public Map<FeelingType, Double> tic() throws ReactorException {

		// Poll perception from queue!
		Emotion inboundEmotion = _perceptionQueue.poll();
		
		if (inboundEmotion != null) {
			inboundEmotion.getFeelings().forEach(feeling -> {
				_emoNow.addFeeling(feeling);
				System.out.println("id: " + _id + ", " + feeling.getFeelingType().name() + ": " + _emoNow.getFeelings());
			});
		}

		Map<FeelingType, Double> emotionNow = _reactor.tic();
		
		return emotionNow;
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
			System.out.println(brain.getEmoNow());
		}
	}
}