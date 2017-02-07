package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

/**
 * Difference between feelings and emotions
 * 
 * https://www.quora.com/Whats-the-differences-between-feelings-and-emotions
 * 
 * @author Oskar Bergstrom <rapidfish@me.com>
 *
 */
public abstract class AbstractFeeling implements Feeling {

	private final static Integer RESOLUTION = 1;
	private final static String PARAM_FEELING_NULL = "parametrar feeling is null";

	private final FeelingType _feelingType;
	private Integer _amplitude;
	private Integer _duration;
	private Integer _attack;
	private Integer _sustain;
	
	// Aggregated
	private Integer _release;
	private Integer _burndown;
	private Integer _level;
	private Integer _attackKoefficient;
	private Integer _sustainKoefficient;
	private Integer _releaseKoefficient;
	
	@SuppressWarnings("unused")
	private AbstractFeeling() throws ReactorException {
		this(null, 0);
	}

	protected AbstractFeeling(FeelingType feeling, int amplitude) throws ReactorException {
		// amplitude squared and attack=sustain=release=33%
		this(feeling, amplitude, amplitude, 33, 33);
	}

	protected AbstractFeeling(FeelingType feeling, Integer amplitude, Integer duration, Integer attack, Integer sustain)
			throws ReactorException {
		if (feeling == null) {
			throw new ReactorException(PARAM_FEELING_NULL);
		}
		_feelingType = feeling;
		_amplitude = amplitude;
		_attack = attack;
		_sustain = sustain;

		// aggregated values
		_level = 0;
		_release = duration - (_attack + _sustain);
		_burndown = duration;
		
		// TODO:
		_attackKoefficient = new Integer(Math.abs(0)); // Positive
		_sustainKoefficient = new Integer(0);
		_releaseKoefficient = new Integer(0 * (-1)); // Negative
	}

	public Integer getLevel() {
		return _level;
	}

	public FeelingType getFeelingType() {
		return _feelingType;
	}

	public String getFeelingName() {
		return _feelingType.description();
	}

	/**
	 * Declines the emotion (by one resolution). Depending on which phase the feeling
	 * is in, the returning level is either in its attack-, sustain- or release- phase depending
	 * on how long duration (no of calls to this methods) has passed.
	 * 
	 * Basically it just returns the now value, and the rest is managed inside this method
	 * (phase state is kept within this class).
	 * 
	 * @return the present level of the feeling, after the most resent decline is done, null if consumed (duration has 0 steps left)!
	 */
	public Integer decline() {

		
		// Problem: keep duration in Emotion or Feeling??? 
		// Send duration from Emotion to Feeling
		
		if(_duration > 0) {
			// Returnera Y, genom att räkna fram dess level (Y)
				// Kontrollera i vilken fas vi är i
					boolean isAttackPhase;
					boolean isSustainPhase;
					boolean isReleasePhase;
			
			// decline duration by one resolution
			_burndown -= RESOLUTION;
			_level = 0;
			return _level;
		}
		
		return _level;
	}
	
	public void prolongEmotionBy(Integer duration){
		_burndown += duration;
	}
	
	@Override
	public String toString() {
		String result = _feelingType + "(" + _amplitude + ")";
		return result;
	}
}