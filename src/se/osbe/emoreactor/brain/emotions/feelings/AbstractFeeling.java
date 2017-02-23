package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;

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
		if (initialTime > 0) {
			_initialTime = initialTime;
		} else if (initialTime == 0) {
			_initialTime = new BrainHelper().getTimeNow();
		} else {
			throw new ReactorException("Not possible to initialize with a negative initial time");
		}
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
		StringBuilder sb = new StringBuilder();
		if (getAmplitude() != null) {
			BrainHelper helper = new BrainHelper();
			String duration = helper.getFormattedWithPrefix(getDuration());
			sb.append(_feelingType.name()).append("(amplitude=").append(getAmplitude().intValue()).append(", duration=")
					.append(duration).append(")");
		}
		return sb.toString();
	}
}