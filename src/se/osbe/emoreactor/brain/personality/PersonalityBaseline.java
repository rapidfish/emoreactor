package se.osbe.emoreactor.brain.personality;

import java.util.HashMap;
import java.util.Map;

import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

/**
 * Configuration class to configure Brain objects
 * 
 * @author Oskar Bergström
 *
 */
public class PersonalityBaseline {

	private final Map<PersonalityType, Double> _properties;

	/**
	 * Default personality having all values set to 50% (introvertVsExtrovert, intuitionVsSensing, feelingVsThinking, percievingVsJudging)
	 * @throws ReactorException
	 */
	public PersonalityBaseline() throws ReactorException {
		this(50d, 50d, 50d, 50d); // Percentage in pair
	}

	/**
	 * Constructor for setting percentage values (individually) for introvertVsExtrovert, intuitionVsSensing, feelingVsThinking, percievingVsJudging.
	 * @param introvertVsExtrovert Value is set for Introvert only, Extrovert gets what is left of remaining percentage.
	 * @param intuitionVsSensing
	 * @param feelingVsThinking
	 * @param percievingVsJudging
	 * @throws ReactorException
	 */
	public PersonalityBaseline(Double introvertVsExtrovert, Double intuitionVsSensing, Double feelingVsThinking, Double percievingVsJudging) throws ReactorException {
		_properties = new HashMap<>(8);
		setIntrovertVsExtravert(introvertVsExtrovert, (100 - introvertVsExtrovert));
		setIntuitionVsSensing(intuitionVsSensing, (100 - intuitionVsSensing));
		setFeelingVsThinking(feelingVsThinking, (100 - feelingVsThinking));
		setPerceivingVsJudging(percievingVsJudging, (100 - percievingVsJudging));
		if(!validateRange(introvertVsExtrovert)) {
			throw new ReactorException("IntrovertVsExtrovert illegal value, out of range!");
		}
		if(!validateRange(intuitionVsSensing)) {
			throw new ReactorException("IntuitionVsSensing illegal value, out of range!");
		}
		if(!validateRange(feelingVsThinking)) {
			throw new ReactorException("FeelingVsThinking illegal value, out of range!");
		}
		if(!validateRange(percievingVsJudging)) {
			throw new ReactorException("PercievingVsJudging illegal value, out of range!");
		}
	}
	
	public Double getIntensityForType(PersonalityType type){
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
		int typeLen = PersonalityType.values().length;
		for (int i = 0; i < typeLen ; i++) {
			PersonalityType type = PersonalityType.values()[i];
			if(type == PersonalityType.NEUTRAL) {
				continue;
			}
			percentage = _properties.get(type);
			sb.append(type.name().toLowerCase()).append(":\t").append(percentage.intValue()).append("%");
			if (even) {
				sb.append("\t<-->\t");
			} else {
				if(i < (typeLen - 2)) {
					sb.append("\n");
				}
			}
			even = !even;
		}
		return sb.toString();
	}

	private boolean validateRange(Double param) {
		return (param >= 0 || param <= 100);
	}

	private PersonalityBaseline setIntrovertVsExtravert(Double introvert, Double extrovert) {
		this.setIntecity(PersonalityType.INTROVERT, introvert);
		this.setIntecity(PersonalityType.EXTROVERT, extrovert);
		return this;
	}

	private PersonalityBaseline setIntuitionVsSensing(Double intuition, Double sensing) {
		this.setIntecity(PersonalityType.INTUITION, intuition);
		this.setIntecity(PersonalityType.SENSING, sensing);
		return this;
	}

	private PersonalityBaseline setFeelingVsThinking(Double feeling, Double thinking) {
		this.setIntecity(PersonalityType.FEELING, feeling);
		this.setIntecity(PersonalityType.THINKING, thinking);
		return this;
	}

	private PersonalityBaseline setPerceivingVsJudging(Double percieving, Double judging) {
		this.setIntecity(PersonalityType.PERCIEVING, percieving);
		this.setIntecity(PersonalityType.JUDGING, judging);
		return this;
	}

	private void setIntecity(PersonalityType type, Double intecity) {
		_properties.put(type, intecity);
	}
	
	public PersonalityBaseline getRandomPersonality() throws ReactorException{
		DiceHelper dice = new DiceHelper();
		PersonalityBaseline p = new PersonalityBaseline(dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage());
		return p;
	}
}