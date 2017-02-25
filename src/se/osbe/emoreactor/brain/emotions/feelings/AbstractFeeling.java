package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.BrainHelperImpl;

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
		if (initialTime >= 0) {
			_initialTime = initialTime;
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

	public void setInitialTime(long time){
		_initialTime = time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_amplitude == null) ? 0 : _amplitude.hashCode());
		result = prime * result + (int) (_duration ^ (_duration >>> 32));
		result = prime * result + ((_feelingType == null) ? 0 : _feelingType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFeeling other = (AbstractFeeling) obj;
		if (_amplitude == null) {
			if (other._amplitude != null)
				return false;
		} else if (!_amplitude.equals(other._amplitude))
			return false;
		if (_duration != other._duration)
			return false;
		if (_feelingType != other._feelingType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (getAmplitude() != null) {
			BrainHelper helper = new BrainHelperImpl();
			String duration = helper.getFormattedWithPrefix(getDuration());
			sb.append(_feelingType.name()).append("(amplitude=").append(getAmplitude().intValue()).append(", duration=")
					.append(duration).append(")");
		}
		return sb.toString();
	}
}