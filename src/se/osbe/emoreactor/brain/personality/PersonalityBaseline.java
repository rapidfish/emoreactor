package se.osbe.emoreactor.brain.personality;

import se.osbe.emoreactor.brain.reactor.ReactorException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Configuration class to configure Brain objects
 *
 * @author Oskar Bergström
 */
public class PersonalityBaseline {

    private final Map<PersonalityType, Double> _properties;

    /**
     * Default personality having all values set to 50% (introvertVsExtrovert, intuitionVsSensing, feelingVsThinking, percievingVsJudging)
     *
     * @throws ReactorException
     */
    public PersonalityBaseline() throws ReactorException {
        this(50d, 50d, 50d, 50d); // Percentage in pair
    }

    /**
     * Constructor for setting percentage for introvert (and Extrovert), intuition (and Sensing), feeling (and Thinking), percieving (and Judging).
     * We only have to set one value for each value pair, the other value will be set automatically based on the first value, so that the
     * total sum of both values makes up 100% in total.
     *
     * @param introvert  Value for introvert, sets its counter part Extrovert implicitly (100 minus the value entered for introvert) making the total sum of both values 100%, always!
     * @param intuition  Value for intuition, sets its counter part Sensing implicitly (100 minus the value entered for introvert) making the total sum of both values 100%, always!
     * @param feeling    Value for feeling, sets its counter part Thinking implicitly (100 minus the value entered for introvert) making the total sum of both values 100%, always!
     * @param percieving Value for percieving, sets its counter part Judging implicitly (100 minus the value entered for introvert) making the total sum of both values 100%, always!
     * @throws ReactorException
     */
    public PersonalityBaseline(Double introvert, Double intuition, Double feeling, Double percieving) throws ReactorException {
        _properties = new HashMap<>(8);
        if (!validateRange(introvert)) {
            throw new ReactorException("IntrovertVsExtrovert illegal value, out of range!");
        }
        if (!validateRange(intuition)) {
            throw new ReactorException("IntuitionVsSensing illegal value, out of range!");
        }
        if (!validateRange(feeling)) {
            throw new ReactorException("FeelingVsThinking illegal value, out of range!");
        }
        if (!validateRange(percieving)) {
            throw new ReactorException("PercievingVsJudging illegal value, out of range!");
        }
        setIntrovertVsExtravert(introvert, (100d - introvert));
        setIntuitionVsSensing(intuition, (100d - intuition));
        setFeelingVsThinking(feeling, (100d - feeling));
        setPerceivingVsJudging(percieving, (100d - percieving));
    }

    public Double getIntensityForType(PersonalityType type) {
        return _properties.get(type);
    }

    public PersonalityType getIntrovertOrExtrovert() {
        return isIntrovert() ? PersonalityType.INTROVERT
                : isExtrovert() ? PersonalityType.EXTROVERT : PersonalityType.NEUTRAL;
    }

    public PersonalityType getIntuitionVsSensing() {
        return isIntuition() ? PersonalityType.INTUITION
                : isSensing() ? PersonalityType.SENSING : PersonalityType.NEUTRAL;
    }

    public PersonalityType getFeelingVsThinking() {
        return isFeeling() ? PersonalityType.FEELING
                : isThinking() ? PersonalityType.THINKING : PersonalityType.NEUTRAL;
    }

    public PersonalityType getPercievingVsJudging() {
        return isPercieving() ? PersonalityType.PERCIEVING
                : isJudging() ? PersonalityType.JUDGING : PersonalityType.NEUTRAL;
    }

    public Double getIntrovert() {
        return _properties.get(PersonalityType.INTROVERT);
    }

    public Double getExtrovert() {
        return _properties.get(PersonalityType.EXTROVERT);
    }

    public Double getIntuition() {
        return _properties.get(PersonalityType.INTUITION);
    }

    public Double getSensing() {
        return _properties.get(PersonalityType.SENSING);
    }

    public Double getFeeling() {
        return _properties.get(PersonalityType.FEELING);
    }

    public Double getThinking() {
        return _properties.get(PersonalityType.THINKING);
    }

    public Double getPercieving() {
        return _properties.get(PersonalityType.PERCIEVING);
    }

    public Double getJudging() {
        return _properties.get(PersonalityType.JUDGING);
    }

    public Boolean isIntrovert() {
        Double introvert = _properties.get(PersonalityType.INTROVERT);
        Double extrovert = _properties.get(PersonalityType.EXTROVERT);
        return (introvert > extrovert);
    }

    public Boolean isExtrovert() {
        Double extrovert = _properties.get(PersonalityType.EXTROVERT);
        Double introvert = _properties.get(PersonalityType.INTROVERT);
        return (extrovert > introvert);
    }

    public Boolean isIntuition() {
        Double intuition = _properties.get(PersonalityType.INTUITION);
        Double sensing = _properties.get(PersonalityType.SENSING);
        return (intuition > sensing);
    }

    public Boolean isSensing() {
        Double sensing = _properties.get(PersonalityType.SENSING);
        Double intuition = _properties.get(PersonalityType.INTUITION);
        return (sensing > intuition);
    }

    public Boolean isFeeling() {
        Double feeling = _properties.get(PersonalityType.FEELING);
        Double thinking = _properties.get(PersonalityType.THINKING);
        return (feeling > thinking);
    }

    public Boolean isThinking() {
        Double thinking = _properties.get(PersonalityType.THINKING);
        Double feeling = _properties.get(PersonalityType.FEELING);
        return (thinking > feeling);
    }

    public Boolean isPercieving() {
        Double percieving = _properties.get(PersonalityType.PERCIEVING);
        Double judging = _properties.get(PersonalityType.JUDGING);
        return (percieving > judging);
    }

    public Boolean isJudging() {
        Double judging = _properties.get(PersonalityType.JUDGING);
        Double percieving = _properties.get(PersonalityType.PERCIEVING);
        return (judging > percieving);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Double percentage;
        boolean even = true;
//        IntStream.range(0, PersonalityType.values().length)
//				.filter(i -> PersonalityType.values()[i] != PersonalityType.NEUTRAL)
//				.map(i -> _properties.get(PersonalityType.values()[i]))
//				.map(p -> new StringBuilder().append(type.name().toLowerCase()).append(":\t").append(percentage.intValue()).append("%"))
//		;
        int typeLen = PersonalityType.values().length;
        for (int i = 0; i < typeLen; i++) {
            PersonalityType type = PersonalityType.values()[i];
            if (type == PersonalityType.NEUTRAL) {
                continue;
            }
            percentage = _properties.get(type);
            sb.append(type.name().toLowerCase()).append(":\t").append(percentage.intValue()).append("%");
            if (even) {
                sb.append("\t<-->\t");
            } else {
                if (i < (typeLen - 2)) {
                    sb.append("\n");
                }
            }
            even = !even;
        }
        return sb.toString();
    }

    private boolean validateRange(Double param) {
        return (param >= 0d && param <= 100d);
    }

    private PersonalityBaseline setIntrovertVsExtravert(Double introvert, Double extrovert) {
        this.setPercentage(PersonalityType.INTROVERT, introvert);
        this.setPercentage(PersonalityType.EXTROVERT, extrovert);
        return this;
    }

    private PersonalityBaseline setIntuitionVsSensing(Double intuition, Double sensing) {
        this.setPercentage(PersonalityType.INTUITION, intuition);
        this.setPercentage(PersonalityType.SENSING, sensing);
        return this;
    }

    private PersonalityBaseline setFeelingVsThinking(Double feeling, Double thinking) {
        this.setPercentage(PersonalityType.FEELING, feeling);
        this.setPercentage(PersonalityType.THINKING, thinking);
        return this;
    }

    private PersonalityBaseline setPerceivingVsJudging(Double percieving, Double judging) {
        this.setPercentage(PersonalityType.PERCIEVING, percieving);
        this.setPercentage(PersonalityType.JUDGING, judging);
        return this;
    }

    private void setPercentage(PersonalityType type, Double intecity) {
        _properties.put(type, intecity);
    }
}