package se.osbe.emoreactor.helper;

import java.util.UUID;

import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.PerceptionType;

public class BrainHelper {

	/**
	 * Getting the FeelingEnum for the string (regexp pattern)
	 * 
	 * @param str
	 *            regexp pattern matching enum value- or description string with
	 *            implicit wildcard (* inside enum. example: both "dep" or "DEP"
	 *            is matching the value (Depressed). matching not case
	 *            sensitive;
	 * @return the matching enum value or throws an exception if no match.
	 */
	public FeelingType getEmotionEnumForPattern(String str) {
		FeelingType result = null;
		String candidate = str.toLowerCase() + ".*";
		FeelingType[] types = FeelingType.values();
		for (FeelingType type : types) {
			String name = type.name().toLowerCase();
			String description = type.description().toLowerCase();
			if (name.matches(candidate) || description.matches(candidate)) {
				result = type;
				break;
			}
		}
		return result;
	}

	public PerceptionType getPerceptionTypeForPattern(String str) {
		PerceptionType result = null;
		String candidate = str.toLowerCase() + ".*";
		PerceptionType[] types = PerceptionType.values();
		boolean match = false;
		for (PerceptionType type : types) {
			String name = type.name().toLowerCase();
			String description = type.getDescription().toLowerCase();
			if (name.matches(candidate) || description.matches(candidate)) {
				match = true;
				result = type;
			}
		}
		if(!match){
			System.out.println("No match!!!");
		}
		return result;
	}

	public static String createUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.split("-")[0];
	}

	public static void main(String[] args) {
		FeelingType emoEnum = null;
		String matchThis = "dEP";
		emoEnum = new BrainHelper().getEmotionEnumForPattern(matchThis);
		System.out.println("Found a matching emotion for pattern: '" + matchThis + "' to --> " + emoEnum);
		System.out.println("createUUID: " + createUUID());
		System.out.println();
		DiceHelper dh = new DiceHelper();
		Double x=5d;
		Double y=10d;
		System.out.println("Chans: " + x + " av " + y + ": " + dh.isLucky(x, y));
	}
}