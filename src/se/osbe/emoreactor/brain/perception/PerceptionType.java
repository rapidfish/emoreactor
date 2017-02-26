package se.osbe.emoreactor.brain.perception;

public enum PerceptionType {
	
	TASTING("Taste"),
	SEEING("See"), 
	TOUCHING("Touch"), 
	SMELLING("Smell"), 
	HEARING("Hear");
	
	private String _description;

	private PerceptionType(String description) {
		_description = description;
	}

	public String getDescription() {
		return _description;
	}
	
	@Override
	public String toString() {
		return _description;
	}
}