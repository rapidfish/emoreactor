package se.osbe.emoreactor.brain.reactor;

public class ReactorException extends Exception {
	private static final long serialVersionUID = 1L;
	private String _description;

	public ReactorException(String description) {
		_description = description;
	}

	public String getDescription() {
		return _description;
	}

	@Override
	public String getMessage() {
		return getDescription();
	}
}
