package se.osbe.emoreactor.brain.perception;

public enum PerceptionType {
	
	TASTE("Taste"),
	SEE("See"), 
	TOUCH("Touch"), 
	SMELL("Smell"), 
	HEAR("Hear");
	
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