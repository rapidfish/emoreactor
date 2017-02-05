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

	private final FeelingType _feelingType;
	private int _intencity;
	private static final String PARAM_FEELING_NULL = "Parametrar feeling is null";

	@SuppressWarnings("unused")
	private AbstractFeeling() throws ReactorException {
		this(null, 0);
	}
	
	protected AbstractFeeling(FeelingType feeling, int intencity) throws ReactorException {
		if (feeling == null) {
			throw new ReactorException(PARAM_FEELING_NULL);
		}
		_feelingType = feeling;
		_intencity = intencity;
	}

	public int getIntencity() {
		return _intencity;
	}

	public void setIntencity(int intencity) {
		_intencity = intencity;
	}

	public FeelingType getFeelingType() {
		return _feelingType;
	}

	public String getFeelingName() {
		return _feelingType.description();
	}

	@Override
	public String toString() {
		String result = _feelingType + "(" + _intencity + ")";
		return result;
	}
}