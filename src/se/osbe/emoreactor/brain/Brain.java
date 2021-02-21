package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigTimeBasedImpl;
import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.PerceptionType;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.brain.reactor.Reactor.ProgressTrendType;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Brain {

    private final BrainConfig _brainConfig;
    private long _ticCounter;
    private Queue<Emotion> _perceptionQueue;
    private Reactor _reactor;
    private DiceHelper _dice;

    @SuppressWarnings("unused")
    private Brain() throws ReactorException {
        // protected from being instantiated
        this(null);
    }

    public Brain(BrainConfig config) throws ReactorException {
        _ticCounter = 0;
        _brainConfig = config == null ? new BrainConfigTimeBasedImpl(new Personality()) : config;
        _perceptionQueue = new LinkedList<Emotion>();
        _reactor = new Reactor(getBrainConfig());
        _dice = getBrainConfig().getDiceHelper();
    }

    // Add only if within brains attention span
    public boolean addInboundPerception(Perception perception) {
        PerceptionType perceptionType = perception.getPerceptionType();
        Emotion perceptionEmoCandidate = perception.getEmotionCandidate();
        boolean isAccepted = false;
        if (_dice.getRandomPercentage() <= getBrainConfig().getPerceptionAwareness()) {
            isAccepted = _perceptionQueue.offer(perceptionEmoCandidate);
            if (!isAccepted) {
                System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + perception);
            }
            return true;
        }
        return false;
    }

    public Personality getPersonality() {
        return getBrainConfig().getPersonality();
    }

    /**
     * Process all inbound emotions supplied by the perception queue. Then
     * it does internal calculations to summarize changes inside its emotion
     * registry. And at last it returns its current emotion (list of feelings
     * and their magnitude right now)
     *
     * @return What emotion the brain is feeling right now! The result is
     * reflecting latest emotional changes inside the brain since last
     * unit of time has past.
     * @throws ReactorException
     */
    public Map<FeelingType, Double> tic() throws ReactorException {
        // Poll perception from queue!
        Emotion inboundEmotion = _perceptionQueue.poll();
        if (inboundEmotion != null) {
            _reactor.addEmotion(inboundEmotion);
        }

        // Delegate one unit of processing (time) emotions to the reactor
        Map<FeelingType, Double> emotionNow = _reactor.ticTac();
        _ticCounter++;
        return emotionNow;
    }

    public Integer getPerceptionAwarenessPercentage() {
        return getBrainConfig().getPerceptionAwareness();
    }

    public void setPerceptionAwarenessPercentage(Integer percentage) {
        getBrainConfig().setPerceptionAwareness(percentage);
    }

    public long getTickCounter() {
        return _ticCounter;
    }

    public boolean isReactorDry() {
        return _reactor.isRegistryEmpty();
    }

    public ProgressTrendType getProgressType(FeelingType type) {
        return _reactor.getProgressForFeeling(type);
    }

    public String getProgressSign(FeelingType type) {
        ProgressTrendType pt = _reactor.getProgressForFeeling(type);
        String result = null;
        String k = String.format("%.2f", pt.getK());
        if (pt == ProgressTrendType.NEUTRAL) {
            result = k;
        } else if (pt == ProgressTrendType.POSITIVE) {
            result = "+" + k;
        } else if (pt == ProgressTrendType.NEGATIVE) {
            result = k;
        }
        return result;
    }

    public BrainConfig getBrainConfig() {
        return _brainConfig;
    }
}