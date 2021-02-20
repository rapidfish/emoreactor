package se.osbe.emoreactor.brain.perception;

public enum PerceptionType {
	
	TASTING("Tasting"),
	EYE_SIGHT("Eye sight"),
	TOUCHING("Touching"),
	SMELLING("Smelling"),
	HEARING("Hearing");
	
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