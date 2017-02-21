package se.osbe.emoreactor.brain;

import java.util.Iterator;
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

public class Brain {

	private final String _id;
	private long _ticCounter;
	private final Personality _personality;
	private Integer _perceptionAwarenessPercentage;
	private final Queue<Emotion> _perceptionQueue;
	private Reactor _reactor;

	@SuppressWarnings("unused")
	private Brain() {
		this(null);
	}

	public Brain(Personality personality) {
		_id = BrainHelper.createUUID();
		_personality = personality;
		_perceptionAwarenessPercentage = 100; // 100%
		_perceptionQueue = new LinkedList<Emotion>(); // incomming emo's from
														// perception
		_reactor = new Reactor();
		_ticCounter = 0;
	}

	// Add only if within brains attention span
	public boolean addInboundPerception(Perception perception) {
		PerceptionType perceptionType = perception.getPerceptionType();
		Emotion emoCandidate = perception.getEmotionCandidate();
		boolean isAccepted = false;
		// if (_dice.getRandomPercentage() <= _awarenessPercentage) {
		isAccepted = _perceptionQueue.offer(emoCandidate);
		if (!isAccepted) {
			System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
		}
		// }
		return isAccepted;
	}

	public Personality getPersonality() {
		return _personality;
	}

	public Map<FeelingType, Double> tic() throws ReactorException {

		// Poll perception from queue!
		Emotion inboundEmotion = _perceptionQueue.poll();
		if (inboundEmotion != null) {
			System.out.println("Inbound emo: " + inboundEmotion);
			_reactor.addEmotion(inboundEmotion);
		}

		// Consume one ticTac in reactor
		Map<FeelingType, Double> emotionNow = _reactor.ticTac();
		_ticCounter++;
		return emotionNow;
	}

	public String getId() {
		return _id;
	}

	public Integer getPerceptionAwarenessPercentage() {
		return _perceptionAwarenessPercentage;
	}

	public void setPerceptionAwarenessPercentage(Integer percentage) {
		_perceptionAwarenessPercentage = (percentage >= 0 && percentage <= 100) ? percentage : 0;
	}

	public long getTickCounter() {
		return _ticCounter;
	}

	boolean isReactorDry() {
		return _reactor.isRegistryEmpty();
	}

	public static void main(String[] args) throws ReactorException, InterruptedException {
		Personality personality = new Personality();
		Brain brain = new Brain(personality);
		System.out.println("Personality:\n" + brain.getPersonality());
		System.out.println("---------------------------------------------------");
		System.out.println("Awareness: " + brain.getPerceptionAwarenessPercentage() + "%");
		System.out.println("---------------------------------------------------");
		EmotionBuilder eb = new EmotionBuilder();
		Emotion emo = eb.addFeelings("Anger=60,30s; Agony=20,15s; Indifferent=40,20s;").build("AngerEmo");
		Emotion emo2 = eb.addFeelings("Anger=60,30s; Agony=20,15s; Indifferent=40,20s;").build("AngerEmo");
		Perception perception = new SightPerception(emo);
		brain.addInboundPerception(perception);
		do {
			if (brain.isReactorDry()) {
				System.out.println("Adding perception: " + perception);
				brain.addInboundPerception(
						new SightPerception(eb.addFeelings("Anger=60,30s; Agony=20,15s; Indifferent=40,20s;")
								.build("Emo" + brain.getTickCounter())));
			}

			Map<FeelingType, Double> emoNow = brain.tic();
			System.out.print(brain.getTickCounter() + ":");

			Iterator<FeelingType> i = emoNow.keySet().iterator();
			while (i.hasNext()) {
				FeelingType t = i.next();
				int v = emoNow.get(t).intValue();
				if (v > 0) {
					System.out.print(t.description() + ":" + v + ", ");
				}
			}
			System.out.println();

			Thread.sleep(1000);
		} while (brain.getTickCounter() < 100);
	}
}