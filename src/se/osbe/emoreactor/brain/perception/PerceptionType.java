package se.osbe.emoreactor.brain.perception;

public enum PerceptionType {
	
	TASTE("taste"),
	SIGHT("see"), 
	TOUCH("touch"), 
	SMELL("smell"), 
	HEARING("hear");
	
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