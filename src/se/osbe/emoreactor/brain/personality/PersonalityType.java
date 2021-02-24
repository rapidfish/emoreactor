package se.osbe.emoreactor.brain.personality;

/**
 * source: http://typelogic.com/faq.html
 * 
 * @author Oskar Bergström
 * 
 */
public enum PersonalityType {
	INTROVERT("Introverted: turned toward the inner world of symbols, ideals and forms. An introvert, or introverted type, is one whose dominant function is inwardly focused. Introverts are inclined to express themselves, using their primary function, indirectly, through inference and nuance."), 
	EXTROVERT("Extraverted: turned toward the outer world, of people and things. An extravert, or extraverted type, is one whose dominant function is focused in an external direction. Extraverts are inclined to express themselves, using their primary function, directly."),
	INTUITION("Intuition: Unconscious perceiving. Intuition involves the recognition of patterns, the perception of the abstract; it is a visionary sense. Extraverted intuition perceives the patterns and possibilities of life. Introverted intuition compares the \"rightness\" of real-world circumstances with that which is ideal. In Jung's typology, intuition is an irrational function. Intuition's opposite function is Sensing."),
	SENSING("Sensing: physiological perception; perceiving with the five natural senses. Extraverted sensors are attuned to the world of sights, sounds, smells, touches and tastes. Introverted sensors are most aware of how those perceptions compare with their ideal internal standards. In Jung's typology, sensing is an irrational function. Sensing's opposite is iNtuition."),
	FEELING("Emotion: Making decisions from a personal perspective. In Jung's typology, feeling is a rational function. Emotion's opposite is Thinking."),
	THINKING("Thinking: Making decisions impersonally. In Jung's typology, thinking is a rational function. Thinking's opposite is Emotion."),
	PERCIEVING("Perceiving. For the E (extraverted) types, it's simple enough - P means that the dominant function is a Perceiving function (iNtuition or Sensing). P types display the more open-ended perceiving, data-collecting function"),
	JUDGING("Judging. The dominant function is a deciding or Judging function. J types show the world their decision-making Judging function"),
	NEUTRAL("50%/50%");
	private String _description;

	private PersonalityType(String description) {
		_description = description;
	}

	public String getDescription() {
		return _description;
	}
}