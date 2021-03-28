package se.osbe.emoreactor.brain;

import org.apache.commons.lang3.StringUtils;
import se.osbe.emoreactor.brain.config.BasicBrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.*;

import static java.util.stream.Collectors.toList;

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

    private BrainConfig brainConfig;
    private Personality personalityBaseline;
    private DiceHelper dice;
    private Map<EmotionType, Float> previousRegistry;
    private Map<EmotionType, Float> currentRegistry;
    private List<Feeling> feelings;
    // private Map<EmotionType, Integer> inclinationRegistry;
    private String id;
    private String brainName;
    private long turnCounter;
    private float perceptionAwareness;
    private long now = 0;

    public Brain() {
        this(null, null, null);
    }

    public Brain(String name) {
        this(name, null, null);
    }

    public Brain(BrainConfig config) {
        this(null, null, config);
    }
    public Brain(String name, BrainConfig config) {
        this(name, null, config);
    }

    public Brain(String name, Personality baseline) {
        this(name, baseline, null);
    }

    public Brain(String name, Personality baseline, BrainConfig config) {
        id = BrainHelper.createUUID();
        dice = new DiceHelper();
        turnCounter = 0;

        brainConfig = Objects.nonNull(config) ? config : new BasicBrainConfig();
        perceptionAwareness = brainConfig.getDefaultAwareness();
        brainName = StringUtils.isNotBlank(name) ? name : brainConfig.getDefaultBrainName();
        personalityBaseline = Objects.nonNull(baseline) ? baseline : brainConfig.getDefaultPersonality();
        feelings = new LinkedList<>();
        // inclinationRegistry = Arrays.stream(EmotionType.values()).collect(toMap(k -> EmotionType.AGONY, v -> Integer.valueOf(0)));
        final int registrySize = EmotionType.values().length;
        previousRegistry = new HashMap<>(registrySize);
        currentRegistry = new HashMap<>(registrySize);

    }

    public static void main(String[] args) {
        System.out.println(new Brain("John Doe", new Personality()));
    }

    public void doReaction(Map<EmotionType, Integer> feelingRegistry) {
        // reaction
    }

    public Map<EmotionType, Float> getCurrentFeeling() {
        return currentRegistry;
    }

    // Getters

    public boolean offerInboundFeeling(Feeling feeling) {
        Objects.requireNonNull(feeling);
        Float randomPercentage = dice.getRandomPercentage();
        if (randomPercentage <= perceptionAwareness) {
            return feelings.add(feeling);
        }
        return false;
    }

    public Map<EmotionType, Float> nextTurn() {
        previousRegistry.clear();
        previousRegistry.putAll(currentRegistry);
        // Cleanup old expired feelings
        feelings = feelings.stream().filter(f -> !f.isExpired()).collect(toList());
        if (!feelings.isEmpty()) {
            currentRegistry.clear();
            now = System.currentTimeMillis();
            feelings.stream().forEach(feeling -> {
                feeling.getEmotions().forEach(emo -> {
                    if (now - feeling.getInitialTimeStamp() <= emo.getDurationTime()) {
                        float amp = calculateAmplitude(emo, (now - feeling.getInitialTimeStamp()));
                        if (amp >= 0) {
                            Float v = currentRegistry.get(emo.getEmotionType());
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
    float calculateAmplitude(Emotion emo, long elapsedTime) {

        long attackTimeLimit = (long) (emo.getDurationTime() * (emo.getAttackPercent() / 100f));

        if (elapsedTime <= attackTimeLimit) {
            // return (int) (emo.getAmplitudeHigh() * Math.sin((PI / 2) / emo.getAttack() * elapsedTime));
            float result = (float) (elapsedTime * (1f * emo.getAmplitudePeak() / attackTimeLimit));
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [attack(" + emo.getAttackPercent() + "%)]: " + result);
            }
            return result;
        }

        long decayTimeLimit = (long) (emo.getDurationTime() * (emo.getDecayPercent() / 100f));
        if (elapsedTime <= (attackTimeLimit + decayTimeLimit)) {
            // return (int) ((emo.getAmplitudeHigh() - emo.getAmplitudeLow()) * Math.sin((PI / 2) / emo.getDecay() * elapsedTime * (-1)) + emo.getAmplitudeHigh());
            float result = (float) emo.getAmplitudePeak() - ((elapsedTime - attackTimeLimit) * (1f * emo.getAmplitudePeak() - emo.getAmplitudeSustain()) / decayTimeLimit);
            if (IS_DEBUG) {
                System.out.println(emo.getEmotionType().getMnmonic() + " [decay(" + emo.getDecayPercent() + "%)]: " + result);
            }
            return result;
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

    public float getPerceptionAwareness() {
        return this.perceptionAwareness;
    }

    /**
     * Overloaded ( See setAwarenessPercentage(float awareness) )
     *
     * @param awareness perception awareness (a value between 0% and 100%)
     */
    public void setAwarenessPercentage(int awareness) {
        setAwarenessPercentage(Float.valueOf(awareness));
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
     * @return old value for awareness, before setting it
     */
    public float setAwarenessPercentage(float awareness) {
        float oldValue = perceptionAwareness;
        if (awareness < 0) {
            perceptionAwareness = 0;
        } else if (awareness > 100) {
            perceptionAwareness = 100;
        } else {
            perceptionAwareness = awareness;
        }
        return oldValue;
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

    public Personality getPersonalityBaseline() {
        return personalityBaseline;
    }

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
     *
     * @return Unmodifiable list of feelings in the brain
     */
    public List<Feeling> getFeelings() {
        return Collections.unmodifiableList(feelings);
    }
}