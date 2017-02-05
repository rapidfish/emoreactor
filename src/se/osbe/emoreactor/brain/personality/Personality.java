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
public class Personality {

	private final Map<PersonalityType, Integer> _properties;

	private final Integer EMO_SPAN_SIZE_MAX_DEFAULT = 20;
	private final Integer EMO_SPAN_SIZE_DEFAULT = 3;
	private final Integer AWARENESS_PERCENTAGE_DEFAULT = 100;

	private Integer _emoSpanSizeMax;
	private Integer _emoSpanSize;
	private Integer _awarenessPercentage;
	
	public Personality() throws ReactorException {
		this(50, 50, 50, 50); // Percentage in pair
	}

	public Personality(Integer introvertVsExtrovert, Integer intuitionVsSensing, Integer feelingVsThinking, Integer percievingVsJudging) throws ReactorException {
		_properties = new HashMap<PersonalityType, Integer>(8);
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
	
	public Integer getEmoSpanSizeMax() {
		return _emoSpanSizeMax != null ? _emoSpanSizeMax : EMO_SPAN_SIZE_MAX_DEFAULT;
	}

	public void setEmoSpanSizeMax(Integer emoSpanSizeMax) {
		_emoSpanSizeMax = emoSpanSizeMax;
	}

	public Integer getEmoSpanSize() {
		return _emoSpanSize != null ? _emoSpanSize : EMO_SPAN_SIZE_DEFAULT;
	}

	public void setEmoSpanSize(Integer emoSpanSize) {
		_emoSpanSize = emoSpanSize;
	}

	public Integer getAwarenessPercentage() {
		return _awarenessPercentage != null ? _awarenessPercentage : AWARENESS_PERCENTAGE_DEFAULT;
	}

	public void setAwarenessPercentage(Integer awarenessPercentage) {
		_awarenessPercentage = awarenessPercentage;
	}

	public Integer getIntencityForType(PersonalityType type){
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

	public Integer getIntrovert() {
		return _properties.get(PersonalityType.INTROVERT);
	}

	public Integer getExtrovert() {
		return _properties.get(PersonalityType.EXTROVERT);
	}

	public Integer getIntuition() {
		return _properties.get(PersonalityType.INTUITION);
	}

	public Integer getSensing() {
		return _properties.get(PersonalityType.SENSING);
	}

	public Integer getFeeling() {
		return _properties.get(PersonalityType.FEELING);
	}

	public Integer getThinking() {
		return _properties.get(PersonalityType.THINKING);
	}

	public Integer getPercieving() {
		return _properties.get(PersonalityType.PERCIEVING);
	}

	public Integer getJudging() {
		return _properties.get(PersonalityType.JUDGING);
	}

	public Boolean isIntrovert() {
		int introvert = _properties.get(PersonalityType.INTROVERT);
		int extrovert = _properties.get(PersonalityType.EXTROVERT);
		return (introvert > extrovert);
	}

	public Boolean isExtrovert() {
		int extrovert = _properties.get(PersonalityType.EXTROVERT);
		int introvert = _properties.get(PersonalityType.INTROVERT);
		return (extrovert > introvert);
	}

	public Boolean isIntuition() {
		int intuition = _properties.get(PersonalityType.INTUITION);
		int sensing = _properties.get(PersonalityType.SENSING);
		return (intuition > sensing);
	}

	public Boolean isSensing() {
		int sensing = _properties.get(PersonalityType.SENSING);
		int intuition = _properties.get(PersonalityType.INTUITION);
		return (sensing > intuition);
	}

	public Boolean isFeeling() {
		int feeling = _properties.get(PersonalityType.FEELING);
		int thinking = _properties.get(PersonalityType.THINKING);
		return (feeling > thinking);
	}

	public Boolean isThinking() {
		int thinking = _properties.get(PersonalityType.THINKING);
		int feeling = _properties.get(PersonalityType.FEELING);
		return (thinking > feeling);
	}

	public Boolean isPercieving() {
		int percieving = _properties.get(PersonalityType.PERCIEVING);
		int judging = _properties.get(PersonalityType.JUDGING);
		return (percieving > judging);
	}

	public Boolean isJudging() {
		int judging = _properties.get(PersonalityType.JUDGING);
		int percieving = _properties.get(PersonalityType.PERCIEVING);
		return (judging > percieving);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int percentage;
		boolean even = true;
		int typeLen = PersonalityType.values().length;
		for (int i = 0; i < typeLen ; i++) {
			PersonalityType type = PersonalityType.values()[i];
			if(type == PersonalityType.NEUTRAL) {
				continue;
			}
			percentage = _properties.get(type).intValue();
			sb.append(type.name().toLowerCase()).append(":\t").append(percentage).append("%");
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

	private boolean validateRange(int param) {
		return (param >= 0 || param <= 100);
	}

	private Personality setIntrovertVsExtravert(int introvert, int extrovert) {
		this.setIntecity(PersonalityType.INTROVERT, introvert);
		this.setIntecity(PersonalityType.EXTROVERT, extrovert);
		return this;
	}

	private Personality setIntuitionVsSensing(int intuition, int sensing) {
		this.setIntecity(PersonalityType.INTUITION, intuition);
		this.setIntecity(PersonalityType.SENSING, sensing);
		return this;
	}

	private Personality setFeelingVsThinking(int feeling, int thinking) {
		this.setIntecity(PersonalityType.FEELING, feeling);
		this.setIntecity(PersonalityType.THINKING, thinking);
		return this;
	}

	private Personality setPerceivingVsJudging(int percieving, int judging) {
		this.setIntecity(PersonalityType.PERCIEVING, percieving);
		this.setIntecity(PersonalityType.JUDGING, judging);
		return this;
	}

	private void setIntecity(PersonalityType type, int intecity) {
		_properties.put(type, intecity);
	}
	
	public Personality getRandomPersonality() throws ReactorException{
		DiceHelper dice = new DiceHelper();
		Personality p = new Personality(dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage());
		return p;
	}

	public static void main(String[] args) throws ReactorException {
		DiceHelper dice = new DiceHelper();
		Personality p = new Personality(dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage(), dice.getRandomPercentage());
		System.out.print(p.getIntrovertOrExtrovert() + "(" + p.getIntencityForType(p.getIntrovertOrExtrovert()) +"%) <--> ");
		System.out.print(p.getIntuitionVsSensing() + "(" + p.getIntencityForType(p.getIntuitionVsSensing()) +"%) <--> ");
		System.out.print(p.getFeelingVsThinking() + "(" + p.getIntencityForType(p.getFeelingVsThinking()) +"%) <--> ");
		System.out.println(p.getPercievingVsJudging() + "(" + p.getIntencityForType(p.getPercievingVsJudging()) +"%)");
		System.out.println(p);
	}
}