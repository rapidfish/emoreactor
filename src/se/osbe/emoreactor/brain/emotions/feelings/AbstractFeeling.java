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

	private final static String PARAM_IS_NULL = "One or more parameter(s) is set to null";
	
	private final FeelingType _feelingType;
	private final Double _amplitude;
	private final long _duration;
	private long _initialTime;

	@SuppressWarnings("unused")
	private AbstractFeeling() throws ReactorException {
		this(null, 0d, 0, 0);
	}

	protected AbstractFeeling(FeelingType feeling, Double amplitude, long initialTime, long duration)
			throws ReactorException {
		
		if (feeling == null) {
			throw new ReactorException(PARAM_IS_NULL);
		}
		
		_feelingType = feeling;
		_amplitude = amplitude;
		_duration = duration;
		_initialTime = initialTime;
	}

	public FeelingType getFeelingType() {
		return _feelingType;
	}

	public Double getAmplitude() {
		return _amplitude;
	}

	public long getInitialTime() {
		return _initialTime;
	}
	
	public long getDuration() {
		return _duration;
	}
	
	@Override
	public String toString() {
		String result = null;
		if(getAmplitude() != null){
			result = _feelingType + "(" + getAmplitude().intValue() + ":)";
		}
		return result;
	}
}