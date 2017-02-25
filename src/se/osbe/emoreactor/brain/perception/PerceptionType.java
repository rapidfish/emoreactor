package se.osbe.emoreactor.brain.perception;

public enum PerceptionType {
	
	TASTE("Tasting"),
	SEE("Seeing"), 
	TOUCH("Touching"), 
	SMELL("Smelling"), 
	HEAR("Hearing");
	
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