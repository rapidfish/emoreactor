package se.osbe.emoreactor.brain.emotions.feelings;

public enum FeelingType {

	AGONY("Agony"), ANGER("Anger"), DEPRESSED("Depressed"), CONFUSED("Confused"), HELPLESS("Helpless"), INDIFFERENT(
			"Indifferent"), AFRAID("Afraid"), HURT("Hurt"), SAD("Sad"), JUDGEMENTAL("Judgemental"), OPEN("Open"), LOVING(
			"Loving"), HAPPY("Happy"), INTERESTED("Interested"), ALIVE("Alive"), POSITIVE("Positive"), PEACEFUL(
			"Peaceful"), STRONG("Strong"), RELAXED("Relaxed"), RELIEF("Relieved");

	private String _description;

	private FeelingType(String description) {
		_description = description;
	}

	public String description() {
		return _description;
	}

	@Override
	public String toString() {
		return _description;
	}
}