package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.*;

/**
 * Emotions vs Feelings
 * <p>
 * Emotions are associated with bodily reactions that are activated through neurotransmitters and hormones
 * released by the brain, feelings are the conscious experience of emotional reactions.
 * <p>
 * source: https://imotions.com/blog/difference-feelings-emotions/#emotions
 */
public class Brain {
    private final DiceHelper diceHelper;
    private final PersonalityCharacteristics personalityBaseline;
    private final List<Feeling> feelings;
    private final Map<EmotionType, Float> previousFeelingRegistry;
    private final Map<EmotionType, Float> currentFeelingRegistry;
    private String id;
    private String brainName;
    private long turnCounter;
    private Integer perceptionAwareness;

    private Brain() {
        this(null, null);
    }

    public Brain(String name, PersonalityCharacteristics baseline) {
        Objects.requireNonNull(baseline);
        id = BrainHelper.createUUID();
        brainName = name;
        turnCounter = 0;
        perceptionAwareness = 0;
        diceHelper = new DiceHelper();
        personalityBaseline = baseline;
        feelings = new LinkedList<>();
        final int registrySize = EmotionType.values().length;
        previousFeelingRegistry = new HashMap<>(registrySize);
        currentFeelingRegistry = new HashMap<>(registrySize);
    }

    public static void main(String[] args) {
        System.out.println(new Brain("John Doe", new PersonalityCharacteristics()));
    }

    public Map<EmotionType, Float> getCurrentFeeling() {
        return currentFeelingRegistry;
    }

    public Map<EmotionType, Float> getPreviousFeeling() {
        return previousFeelingRegistry;
    }

    public boolean offerInboundFeeling(Feeling feeling) {
        Objects.requireNonNull(feeling);
        Float randomPercentage = diceHelper.getRandomPercentage();
        Float intencity = perceptionAwareness != 0 ? (randomPercentage / perceptionAwareness) : 0;
        if (intencity > 0 && randomPercentage <= perceptionAwareness) {
            return feelings.add(feeling);
        }
        return false;
    }

    // Getters

    public Map<EmotionType, Float> nextTurn() {

        // return amplitude * ((float) Math.sin((Math.PI / duration)) * (now - initialTime));

        // save currentRecistry to previe
        previousFeelingRegistry.putAll(currentFeelingRegistry);
        currentFeelingRegistry.clear();

        long now = System.currentTimeMillis(); // Read only once per tic
        if (!feelings.isEmpty()) {
            feelings.stream().forEach(feeling -> {
                // behandla nya feelings...
            });
        }
        feelings.clear();
        turnCounter++;
        return currentFeelingRegistry;
    }

    /**
     * Sets the value of how likely the brain will react to any stimuli being fed to it.
     * This is a way to simulate the fact all messages/stimuli needs to get above a certain 'threshold level' to be
     * noticed by the brain. Once a 'message' like this is getting noticed, an input can no longer be ignored, hence
     * a reaction will take place.<br><br>
     * <p>
     * E.g If set to 25 (25%), it will statistically react on every fourth stimuli it is being fed.<br>
     * <p>
     * NOTE: When setting a percentage value below 0%, or above 100%, it will only result in being set to 0%, and 100% respectively.
     *
     * @param awareness perception awareness (a value between 0% and 100%)
     */
    public void setAwarenessPercentage(Integer awareness) {
        if (awareness < 0) {
            perceptionAwareness = 0;
            return;
        } else if (awareness > 100) {
            perceptionAwareness = 100;
            return;
        }
        perceptionAwareness = awareness;
    }

    public long getTurnCounter() {
        return turnCounter;
    }

    public String getUUID() {
        return id;
    }

    public String getName() {
        return brainName;
    }

    public DiceHelper getDiceHelper() {
        return diceHelper;
    }

    public PersonalityCharacteristics getPersonalityBaseline() {
        return personalityBaseline;
    }

    @Override
    public String toString() {
        return "Brain {\n" +
                "id='" + id + "\'\n" +
                "brainName='" + brainName + "\'\n" +
                "turnCounter=" + turnCounter + "\n" +
                "perceptionAwareness=" + perceptionAwareness + "\n" +
                personalityBaseline + "\n}";
    }
}