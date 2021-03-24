package se.osbe.emoreactor.brain;

import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Emotions vs Feelings
 * <p>
 * Emotions are associated with bodily reactions that are activated through neurotransmitters and hormones
 * released by the brain, feelings are the conscious experience of emotional reactions.
 * <p>
 * source: https://imotions.com/blog/difference-feelings-emotions/#emotions
 */
public class Brain {
    private static final Float PI = (float) Math.PI;
    private static final boolean IS_DEBUG = Boolean.FALSE;

    private final DiceHelper dice;
    private final PersonalityCharacteristics personalityBaseline;
    private List<Feeling> feelings;
    private final Map<EmotionType, Integer> previousRegistry;
    private final Map<EmotionType, Integer> currentRegistry;
    private String id;
    private String brainName;
    private long turnCounter;
    private Integer perceptionAwareness;
    private long now = 0;

    private Brain() {
        this(null, null);
    }

    public Brain(String name, PersonalityCharacteristics baseline) {
        Objects.requireNonNull(baseline);
        id = BrainHelper.createUUID();
        brainName = name;
        turnCounter = 0;
        perceptionAwareness = 0;
        dice = new DiceHelper();
        personalityBaseline = baseline;
        feelings = new LinkedList<>();
        final int registrySize = EmotionType.values().length;
        previousRegistry = new HashMap<>(registrySize);
        currentRegistry = new HashMap<>(registrySize);

    }

    public static void main(String[] args) {
        System.out.println(new Brain("John Doe", new PersonalityCharacteristics()));
    }

    public void doReaction(Map<EmotionType, Integer> feelingRegistry) {
        // reaction
    }

    public Map<EmotionType, Integer> getCurrentFeeling() {
        return currentRegistry;
    }

    // Getters

    public boolean offerInboundFeeling(Feeling feeling) {
        Objects.requireNonNull(feeling);
        Float randomPercentage = dice.getRandomPercentage();
        Float intencity = perceptionAwareness != 0 ? (randomPercentage / perceptionAwareness) : 0;
        if (intencity > 0 && randomPercentage <= perceptionAwareness) {
            return feelings.add(feeling);
        }
        return false;
    }

    public Map<EmotionType, Integer> nextTurn() {
        previousRegistry.clear();
        previousRegistry.putAll(currentRegistry);
        // Cleanup old expired feelings
        feelings = feelings.stream().filter(f -> !f.isExpired()).collect(Collectors.toList());
        if (!feelings.isEmpty()) {
            currentRegistry.clear();
            now = System.currentTimeMillis();
            feelings.stream().forEach(feeling -> {
                feeling.getEmotions().forEach(emo -> {
                    if (now - feeling.getInitialTimeStamp() <= emo.getDurationTime()) {
                        int amp = calculateAmplitude(emo, (now - feeling.getInitialTimeStamp()));
                        if (amp >= 0) {
                            Integer v = currentRegistry.get(emo.getEmotionType());
                            currentRegistry.put(emo.getEmotionType(), (Objects.nonNull(v) ? v : 0) + amp);
                        } else {
                            currentRegistry.remove(emo.getEmotionType());
                        }
                    } else {
                        emo.setExpired();
                    }
                });
            });
        }
        turnCounter++;
        return currentRegistry;
    }

    // https://www.geogebra.org/graphing?lang=en
    Integer calculateAmplitude(Emotion emo, long elapsedTime) {

        long attackTimeLimit = (long) (emo.getDurationTime() * (emo.getAttackPercent() / 100f));

        if (elapsedTime <= attackTimeLimit) {
            // return (int) (emo.getAmplitudeHigh() * Math.sin((PI / 2) / emo.getAttack() * elapsedTime));
            float result = (float) (elapsedTime * (1f * emo.getAmplitudePeak() / attackTimeLimit));
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [attack(" + emo.getAttackPercent() + "%)]: " + result);
            }
            return Math.round(result);
        }

        long decayTimeLimit = (long) (emo.getDurationTime() * (emo.getDecayPercent() / 100f));
        if (elapsedTime <= (attackTimeLimit + decayTimeLimit)) {
            // return (int) ((emo.getAmplitudeHigh() - emo.getAmplitudeLow()) * Math.sin((PI / 2) / emo.getDecay() * elapsedTime * (-1)) + emo.getAmplitudeHigh());
            float result = (float) emo.getAmplitudePeak() - ((elapsedTime - attackTimeLimit) * (1f * emo.getAmplitudePeak() - emo.getAmplitudeSustain()) / decayTimeLimit);
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [decay(" + emo.getDecayPercent() + "%)]: " + result);
            }
            return Math.round(result);
        }

        long sustainTimeLimit = (long) (emo.getDurationTime() * (emo.getSustainPercent() / 100f));
        if (elapsedTime <= (attackTimeLimit + decayTimeLimit + sustainTimeLimit)) {
            // targetAmplitude * ((float) Math.sin((Math.PI / duration) * elapsedTime));
            float result = (float) emo.getAmplitudeSustain();
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [sustain(" + emo.getSustainPercent() + "%)]: " + result);
            }
            return Math.round(result);
        }

        long releaseTimeLimit = (long) (emo.getDurationTime() * (emo.getRelease() / 100f));
        if (elapsedTime <= emo.getDurationTime()) { //  emo.getTotalDuration()
            // return (int) (emo.getAmplitudeLow() * Math.sin((PI / 2) / emo.getDecay() * elapsedTime * (-1)) + emo.getAmplitudeLow());
            float result = (float) emo.getAmplitudeSustain() - ((elapsedTime - (attackTimeLimit + decayTimeLimit + sustainTimeLimit)) * (1f * emo.getAmplitudeSustain() / releaseTimeLimit));
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [release(" + emo.getReleasePercent() + "%)]: " + result);
            }
            return (result >= 0) ? Math.round(result) : 0;
        }

        return 0;
    }

    public Integer getPerceptionAwareness() {
        return this.perceptionAwareness != null ? this.perceptionAwareness : Integer.valueOf(0);
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
        return dice;
    }

    public PersonalityCharacteristics getPersonalityBaseline() {
        return personalityBaseline;
    }


//    private long elapsedTime(long initialTime) {
//        return (now - initialTime);
//    }

    private float calculateAttackAmplitude(float maxAmplitude, long attackDuration, long elapsedTime) {
        if (attackDuration <= 0) {
            return maxAmplitude;
        }
        return (float) (maxAmplitude * Math.sin((PI / 2) / attackDuration * elapsedTime));
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

    public Map<EmotionType, Float> getInclinations() {
        Map<EmotionType, Float> inclination = new HashMap<>(EmotionType.values().length);
        Arrays.stream(EmotionType.values()).forEach(et -> {
                    var i = getInclination(et);
                    if (Objects.nonNull(i)) {
                        inclination.put(et, getInclination(et));
                    }
                }
        );
        return inclination;
    }

    public Float getInclination(EmotionType type) {
        if (Objects.isNull(currentRegistry.get(type)) || Objects.isNull(previousRegistry.get(type))) {
            return null;
        }
        return currentRegistry.get(type).floatValue() - previousRegistry.get(type).floatValue();
    }

    /**
     * Gets a unmodifiable list of all feelings residing in the brain this moment (now)!
     * @return Unmodifiable list of feelings in the brain
     */
    public List<Feeling> getFeelings() {
        return Collections.unmodifiableList(feelings);
    }
}