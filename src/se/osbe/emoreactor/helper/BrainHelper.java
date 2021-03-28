package se.osbe.emoreactor.helper;

import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;

import java.util.UUID;
import java.util.stream.Stream;

public class BrainHelper {

    public static EmotionType getEmotionEnumForPattern(String str) {
        EmotionType result = null;
        String candidate = str.toLowerCase() + "\\w*";
        EmotionType[] types = EmotionType.values();
        for (EmotionType type : types) {
            String mnmonic =  type.getMnmonic().toLowerCase();
            String name = type.name().toLowerCase();
            String description = type.getEmotionName().toLowerCase();
            if (mnmonic.matches(candidate) || name.matches(candidate) || description.matches(candidate)) {
                result = type;
                break;
            }
        }
        return result;
    }

    public static String createUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.split("-")[0];
    }

    public static String getTimeAsString(long millis) {
        StringBuilder sb = new StringBuilder();
        if (millis <= 1000) {
            sb.append(millis).append("ms");
        } else if (millis < 60000) {
            sb.append(millis / 1000).append("s");
        } else if (millis < 3600000) {
            sb.append(millis / 60000).append("m");
        } else if (millis < 8640000) {
            sb.append(millis / 3600000).append("h");
        } else if (millis < 604800000) {
            sb.append(millis / 8640000).append("d");
        } else {
            long tmp = millis / 604800000;
            sb.append("w");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EmotionType emoEnum = null;
        String matchThis = "ago";
        emoEnum = BrainHelper.getEmotionEnumForPattern(matchThis);
        System.out.println("Found a matching emotion for pattern: '" + matchThis + "' to --> " + emoEnum);
        System.out.println("createUUID: " + createUUID());
        System.out.println();
        DiceHelper dh = new DiceHelper();
        Float x = dh.getRandomFibonacci(10).floatValue();
        Float y = dh.getRandomFibonacci(10).floatValue();
        float z = x;
        x = x <= y ? x : y;
        y = y >= z ? y : z;
        System.out.println("Odds: " + x + " av " + y + ", utfall: " + (dh.isLucky(x, y) ? "lyckat" : "misslyckat"));
    }
}