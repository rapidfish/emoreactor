package se.osbe.emoreactor.helper;

import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.PerceptionType;

public interface BrainHelper {

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
	FeelingType getEmotionEnumForPattern(String str);

	PerceptionType getPerceptionTypeForPattern(String str);

	long getTimeNow();

	String getFormattedWithPrefix(long millis);

}