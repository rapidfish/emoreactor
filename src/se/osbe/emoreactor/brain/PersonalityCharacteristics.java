package se.osbe.emoreactor.brain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonalityCharacteristics {

    private final Map<PersonalityType, Float> properties;


    public PersonalityCharacteristics() {
        // Default personality having all values set to 50%
        this(50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f); // Percentage in pair
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
     */
    public PersonalityCharacteristics(float introvert, float extrovert, float intuition, float sensing, float feeling, float thinking, float percieving, float judging) {
        properties = new HashMap<>(8);
        setIntrovertVsExtravert(introvert, extrovert);
        setIntuitionVsSensing(intuition, sensing);
        setFeelingVsThinking(feeling, thinking);
        setPerceivingVsJudging(percieving, judging);
    }

    private static float calcTotal(float op1, float op2) {
        return Math.abs(op1) + Math.abs(op2);
    }

    private static float calcPercentage(float value, float total) {
        if (total > 0) {
            return 100f * (value / total);
        } else {
            return 50f;
        }
    }

    private static boolean isValueWithinValidRange(float param) {
        Objects.requireNonNull(param);
        return (param >= 0 && param <= 100f);
    }

    public float getCharacteristics(PersonalityType type) {
        return properties.get(type);
    }

    public float getIntrovert() {
        return properties.get(PersonalityType.INTROVERT);
    }

    public float getExtrovert() {
        return properties.get(PersonalityType.EXTROVERT);
    }

    public float getIntuition() {
        return properties.get(PersonalityType.INTUITION);
    }

    public float getSensing() {
        return properties.get(PersonalityType.SENSING);
    }

    public float getFeeling() {
        return properties.get(PersonalityType.FEELING);
    }

    public float getThinking() {
        return properties.get(PersonalityType.THINKING);
    }

    public float getPercieving() {
        return properties.get(PersonalityType.PERCIEVING);
    }

    public float getJudging() {
        return properties.get(PersonalityType.JUDGING);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        float percentage;
        int typeLen = PersonalityType.values().length;
        for (int i = 0; i < typeLen; i++) {
            PersonalityType type = PersonalityType.values()[i];
            percentage = properties.get(type);
            sb.append(type.name().toLowerCase())
                    .append(" (%): ")
                    .append(percentage)
                    .append((i < (typeLen - 1)) ? "\n" : "");
        }
        return sb.toString();
    }

    private PersonalityCharacteristics setIntrovertVsExtravert(float introvert, float extrovert) {
        float i = isValueWithinValidRange(introvert) ? introvert : 50f;
        float e = isValueWithinValidRange(extrovert) ? extrovert : 50f;
        float tot = calcTotal(introvert, extrovert);
        this.setPercentage(PersonalityType.INTROVERT, calcPercentage(i, tot));
        this.setPercentage(PersonalityType.EXTROVERT, calcPercentage(e, tot));
        return this;
    }

    private PersonalityCharacteristics setIntuitionVsSensing(float intuition, float sensing) {
        float i = isValueWithinValidRange(intuition) ? intuition : 50f;
        float s = isValueWithinValidRange(sensing) ? sensing : 50f;
        float tot = calcTotal(intuition, sensing);
        this.setPercentage(PersonalityType.INTUITION, calcPercentage(i, tot));
        this.setPercentage(PersonalityType.SENSING, calcPercentage(s, tot));
        return this;
    }

    private PersonalityCharacteristics setFeelingVsThinking(float feeling, float thinking) {
        float f = isValueWithinValidRange(feeling) ? feeling : 50f;
        float t = isValueWithinValidRange(thinking) ? thinking : 50f;
        float tot = calcTotal(feeling, thinking);
        this.setPercentage(PersonalityType.FEELING, calcPercentage(f, tot));
        this.setPercentage(PersonalityType.THINKING, calcPercentage(t, tot));
        return this;
    }

    private PersonalityCharacteristics setPerceivingVsJudging(float percieving, float judging) {
        float p = isValueWithinValidRange(percieving) ? percieving : 50f;
        float j = isValueWithinValidRange(judging) ? judging : 50f;
        float tot = calcTotal(percieving, judging);
        this.setPercentage(PersonalityType.PERCIEVING, calcPercentage(p, tot));
        this.setPercentage(PersonalityType.JUDGING, calcPercentage(j, tot));
        return this;
    }

    private void setPercentage(PersonalityType type, float intecity) {
        properties.put(type, intecity);
    }
}
