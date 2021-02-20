package se.osbe.emoreactor.helper;

import java.util.UUID;

import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.PerceptionType;

public class BrainHelperImpl implements BrainHelper {

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
	
	public String getFormattedWithPrefix(long millis) {
		StringBuilder sb = new StringBuilder();
		if (millis <= 1000) {
			sb.append(millis).append("ms");
		} else if (millis < 60000) {
			sb.append(millis / 1000).append("sec");
		} else if (millis < 3600000) {
			sb.append(millis / 60000).append("min");
		} else if (millis < 8640000) {
			sb.append(millis / 3600000).append("h");
		} else if(millis < 604800000){
			sb.append(millis / 8640000).append("days");
		} else {
			long tmp = millis / 604800000;
			sb.append(tmp).append(tmp > 1 ? "weeks" : "week");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		FeelingType emoEnum = null;
		String matchThis = "hap";
		emoEnum = new BrainHelperImpl().getEmotionEnumForPattern(matchThis);
		System.out.println("Found a matching emotion for pattern: '" + matchThis + "' to --> " + emoEnum);
		System.out.println("createUUID: " + createUUID());
		System.out.println();
		DiceHelper dh = new DiceHelper();
		Double x=5d;
		Double y=10d;
		System.out.println("Chans: " + x + " av " + y + ": " + dh.isLucky(x, y));
	}
}