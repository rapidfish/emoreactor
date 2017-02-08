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
	private final static String PARAM_FEELING_ZERO_OR_LESS = "parameter must not be zero nor less than zero";

	private final FeelingType _feelingType;
	private Integer _amplitude;
	private Integer _duration;
	private Integer _attack;
	private Integer _sustain;

	// Aggregated
	private Integer _release;
	private Integer _level;

	private float _attackKoefficient;
	private float _sustainKoefficient;
	private float _releaseKoefficient;

	// state
	private Integer _burndown;

	@SuppressWarnings("unused")
	private AbstractFeeling() throws ReactorException {
		this(null, 0);
	}

	protected AbstractFeeling(FeelingType feeling, int amplitude) throws ReactorException {
		// Duration is bound to have its size equal to the amplitude.
		// Attack=sustain=release is evenly distributed (33% each, release has
		// 34%, the extra 1% to make up 100%)
		this(feeling, amplitude, amplitude, 33, 33);
	}

	protected AbstractFeeling(FeelingType feeling, Integer amplitude, Integer duration, float attack, float sustain)
			throws ReactorException {
		
		if (feeling == null) {
			throw new ReactorException(PARAM_FEELING_NULL);
		}
		
		if(_amplitude <= 0 || duration <= 0 || attack <= 0 || sustain <= 0){
			throw new ReactorException(PARAM_FEELING_ZERO_OR_LESS);
		}
		
		_feelingType = feeling;
		_amplitude = amplitude;
		_duration = duration;

		_burndown = _duration;

		_attack = resolvePercentageOfBurndown(attack);
		_sustain = resolvePercentageOfBurndown(sustain);
		_release = duration - (_attack + _sustain);

		_attackKoefficient = (_amplitude / _attack); // Positive
		_releaseKoefficient = (0 - _amplitude / _release); // Negative
		_releaseKoefficient = (_releaseKoefficient > 0) ? _releaseKoefficient * (-1f) : _releaseKoefficient ;
		
		_level = 0;
	}

	private Integer resolvePercentageOfBurndown(float percentage) {
		return (int)( (percentage / 100.0f) * _burndown);
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
	 * Declines the emotion (by one resolution). Depending on which phase the
	 * feeling is in, the returning level is either in its attack-, sustain- or
	 * release- phase depending on how long duration (no of calls to this
	 * methods) has passed.
	 * 
	 * Basically it just returns the now value, and the rest is managed inside
	 * this method (phase state is kept within this class).
	 * 
	 * @return the present level of the feeling, after the most resent decline
	 *         is done, null if consumed (duration has 0 steps left)!
	 */
	public Integer decline() {

		// Problem: keep duration in Emotion or Feeling???
		// Send duration from Emotion to Feeling

		if (_duration > 0) {
			// Returnera Y, genom att räkna fram dess level (Y)
			// Kontrollera i vilken fas vi är i


			// decline duration by one resolution
			_burndown -= RESOLUTION;
			_level = 0;
			return _level;
		}

		return _level;
	}

	public float getAttackKoefficient() {
		return _attackKoefficient;
	}

	public float getSustainKoefficient() {
		return _sustainKoefficient;
	}

	public float getReleaseKoefficient() {
		return _releaseKoefficient;
	}

	public boolean isAttackPhase() {
		return (_burndown > (_sustain + _release));
	}

	public boolean isSustainPhase() {
		return !isAttackPhase() && (_burndown > _release);
	}

	public boolean isReleasePhase() {
		return !isAttackPhase() && !isSustainPhase();
	}

	public void fastForward(Integer duration) {
		_burndown -= duration;
	}
	
	public void rewind(Integer duration) {
		_burndown += duration;
	}

	@Override
	public String toString() {
		String result = _feelingType + "(" + _amplitude + ")";
		return result;
	}
}