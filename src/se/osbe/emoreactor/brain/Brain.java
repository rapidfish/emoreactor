package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigDefaultImpl;
import se.osbe.emoreactor.brain.emotions.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.brain.reactor.Reactor;
import se.osbe.emoreactor.brain.reactor.Reactor.ProgressTrendType;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Emotions vs Feelings
 * <p>
 * Emotions are associated with bodily reactions that are activated through neurotransmitters and hormones
 * released by the brain, feelings are the conscious experience of emotional reactions.
 * <p>
 * source: https://imotions.com/blog/difference-feelings-emotions/#emotions
 */
public class Brain {

    private final BrainConfig brainConfig;
    private long ticCounter;
    private final Queue<Feeling> perceptionQueue;
    private final Reactor reactor;
    private final DiceHelper diceHelper;

    @SuppressWarnings("unused")
    private Brain() throws ReactorException {
        // protected from being instantiated
        this(null);
    }

    public Brain(BrainConfig config) throws ReactorException {
        ticCounter = 0;
        brainConfig = (config == null) ? new BrainConfigDefaultImpl(new PersonalityBaseline()) : config;
        perceptionQueue = new LinkedList<>();
        reactor = new Reactor(getBrainConfig());
        // Each brain config has its own (unique seed value) instance of a dice helper
        diceHelper = getBrainConfig().getDiceHelper();
    }

    // Add only if within brains attention span
    public boolean addInboundPerception(Feeling feelingCandidate) {
        boolean isAccepted;
        if (diceHelper.getRandomPercentage() <= getBrainConfig().getPerceptionAwareness()) {
            isAccepted = perceptionQueue.offer(feelingCandidate);
            if (!isAccepted) {
                System.err.println("WARNING! EMOTION QUEUE IS OVERLOADED!!! " + feelingCandidate);
            }
            return true;
        }
        return false;
    }

    public PersonalityBaseline getPersonality() {
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
     */
    public Map<EmotionType, Double> tic()  {
        // Poll perception from queue!
        Feeling inboundFeeling = perceptionQueue.poll();
        if (inboundFeeling != null) {
            reactor.addEmotion(inboundFeeling);
        }

        // Delegate one unit of processing to the reactor
        Map<EmotionType, Double> emotionNow = reactor.tic();
        ticCounter++;
        return emotionNow;
    }

    public Integer getPerceptionAwarenessPercentage() {
        return getBrainConfig().getPerceptionAwareness();
    }

    /**
     * Sets the value of how likely the brain will react to any stimuli being fed to it.
     * This is a way to simulate the fact all messages/stimuli needs to get above a certain 'threshold level' to be
     * noticed by the brain. Once a 'message' like this is getting noticed, an input can no longer be ignored, hence
     * a reaction will take place.<br><br>
     * <p>
     * E.g If set to 25 (25%), it will statistically react on every fourth stimuli it is being fed.<br>
     * <p>
     * NOTE: When setting a percentage value below 0, or above 100, it will only result in being set to 0%, and 100% respectively.
     *
     * @param percentage perception awareness as an Integer (representing a value between 0% and 100%)
     */
    public void setPerceptionAwarenessPercentage(Integer percentage) {
        if (percentage < 0) {
            getBrainConfig().setPerceptionAwareness(0);
        } else if (percentage > 100) {
            getBrainConfig().setPerceptionAwareness(100);
        } else {
            getBrainConfig().setPerceptionAwareness(percentage);
        }
    }

    public long getTickCounter() {
        return ticCounter;
    }

    public boolean isReactorDry() {
        return reactor.isRegistryEmpty();
    }

    public ProgressTrendType getProgressType(EmotionType type) {
        return reactor.getProgressForFeeling(type);
    }

    public BrainConfig getBrainConfig() {
        return brainConfig;
    }
}