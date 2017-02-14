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

	private final static String PARAM_FEELING_NULL = "parametrar feeling is null";

	private final FeelingType _feelingType;
	protected Double _amplitude;
	protected Double _duration;
	protected Double _ticCounter;

	@SuppressWarnings("unused")
	private AbstractFeeling() throws ReactorException {
		this(null, 0d);
	}

	protected AbstractFeeling(FeelingType feeling, Double amplitude) throws ReactorException {
		// Duration is bound to have its size equal to the amplitude.
		// Attack=sustain=release is evenly distributed (33% each, release has
		// 34%, the extra 1% to make up 100%)
		this(feeling, amplitude, amplitude);
	}

	protected AbstractFeeling(FeelingType feeling, Double amplitude, Double duration)
			throws ReactorException {
		
		if (feeling == null) {
			throw new ReactorException(PARAM_FEELING_NULL);
		}
		
		_feelingType = feeling;
		_amplitude = amplitude;
		_duration = new Double(duration);
		_ticCounter = new Double(0);
	}

	public FeelingType getFeelingType() {
		return _feelingType;
	}

	public String getFeelingName() {
		return _feelingType.description();
	}

	public void fastForward(Integer duration) {
		_ticCounter -= duration;
	}
	
	public void rewind(Integer duration) {
		_ticCounter += duration;
	}

	@Override
	public String toString() {
		String result = null;
		if(getAmplitude() != null){
			result = _feelingType + "(" + getAmplitude().intValue() + ")";
		}
		return result;
	}

	public Double getAmplitude() {
		return _amplitude;
	}

	public Double getDuration() {
		return _duration;
	}

	// Using a "sinus wave" to spread amplitude smoothly over duration time
	public Double tic() {
		Double result = null;
		if(_duration != 0 && _ticCounter < _duration){
			result = _amplitude * Math.sin((Math.PI / 100) * _ticCounter);
			_ticCounter = Double.sum(_ticCounter, (100 / _duration));
		}
		return  result;
	}
}