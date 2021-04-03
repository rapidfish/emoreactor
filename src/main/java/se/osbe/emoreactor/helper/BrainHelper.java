package se.osbe.emoreactor.helper;

import org.apache.commons.lang3.StringUtils;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BrainHelper {

    public static Optional<Emotion> getEmotionForPattern(String str) {
        final Pattern p = Pattern.compile("\\w{3,11}[-]D?\\d+[-]H?\\d+[-]L?\\d+[-]A?\\d+[-]D?\\d+[-]S?\\d+[-]R?\\d+", Pattern.CASE_INSENSITIVE); //
        if(StringUtils.isBlank(str) || !p.matcher(str).matches()){
            return Optional.empty();
        }
        String candidate = str.toUpperCase();
        boolean isSyntaxOk = p.matcher(candidate).matches();
        if (!isSyntaxOk) {
            return Optional.empty();
        }

        String[] parts = candidate.split("-");
        String emoTypeStr = parts[0];
        String emoDurationStr = parts[1].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoHighStr = parts[2].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoLowStr = parts[3].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoAttackStr = parts[4].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoDecayStr = parts[5].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoSustainStr = parts[6].replaceAll("\\D", "").replaceAll("^0+", "");
        String emoReleaeStr = parts[7].replaceAll("\\D", "").replaceAll("^0+", "");
        Optional<EmotionType> emoType = getEmotionEnumForPattern(emoTypeStr);
        if(Arrays.stream(parts).filter(StringUtils::isBlank).findAny().isPresent() || !emoType.isPresent()){
            System.out.println("Syntax error - Emotion string is not correct: " + str);
            return Optional.empty();
        }
        return Optional.ofNullable(Emotion.builder()
                .emotionType(emoType.get())
                .durationTime(Long.valueOf(emoDurationStr))
                .amplitudePeak(Integer.valueOf(emoHighStr.replaceFirst("^[0]?", "")).intValue())
                .amplitudeSustain(Integer.valueOf(emoLowStr).intValue())
                .attack(Integer.valueOf(emoAttackStr).intValue())
                .decay(Integer.valueOf(emoDecayStr).intValue())
                .sustain(Integer.valueOf(emoSustainStr).intValue())
                .release(Integer.valueOf(emoReleaeStr).intValue())
                .build());
    }

    public static Optional<EmotionType> getEmotionEnumForPattern(String str) {
        EmotionType result = null;
        String candidate = str.toLowerCase() + "\\w*";
        EmotionType[] types = EmotionType.values();
        for (EmotionType type : types) {
            String mnmonic = type.getMnmonic().toLowerCase();
            String name = type.name().toLowerCase();
            String description = type.getEmotionName().toLowerCase();
            if (mnmonic.matches(candidate) || name.matches(candidate) || description.matches(candidate)) {
                result = type;
                break;
            }
        }
        return Optional.ofNullable(result);
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
        Optional<EmotionType> emoEnumOpt = null;
        String matchThis = "ago";
        emoEnumOpt = BrainHelper.getEmotionEnumForPattern(matchThis);
        System.out.println("Found a matching emotion for pattern: '" + matchThis + "' to --> " + emoEnumOpt.get());
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