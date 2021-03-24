package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.PersonalityCharacteristics;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {

    private static final String DELIMITER = ", ";
    private static final DiceHelper dice = new DiceHelper();

    public static void main(String[] args) throws Exception {
        // Zeh Braine, ja!
        final Brain brain = new Brain("John Doe", new PersonalityCharacteristics());
        Map<EmotionType, Integer> feelingNow;
        brain.setAwarenessPercentage(100); // This is 0% by default, so we need to set it.

        System.out.println("Starting EmoReactor with John Doe, having personality baseline:");
        System.out.println(brain.getPersonalityBaseline().toString() + "\n");
        System.out.println(String.format("Awareness: %s", brain.getPerceptionAwareness()) + "%\n");

        // Builder to create input stimuli for the reactor and the brain
        // A dice helper to produce random numbers as we don not (yet) have 'real world' stimuli input
        while (true) {
//          int awareness = dice.getRandomFloatBetween(5f, 15f).intValue();
//          int intensity = dice.getRandomFloatBetween(5f, 20f).intValue(); // 1 - 10
//          int duration = dice.getRandomFloatBetween(5f, 60f).intValue(); // 2s - 5min
            System.out.println(String.format("Time unit |%d|, Awareness: %d", brain.getTurnCounter(), brain.getPerceptionAwareness()) + "%");
            // feelingBuilder.addFeelings("agony=" + intensity + "," + duration + "s;").build(null) : null;
            Feeling feeling = generateRandomFeeling();
            if (brain.offerInboundFeeling(feeling)) {
                System.out.println("Detected inbound feeling: " + feeling.toString());
            } else {
                System.out.println("No new feeling detected...");
            }
            feelingNow = brain.nextTurn();
            Thread.sleep(100); // 1000 = 1s
            System.out.println("Emotions -> " +
                    feelingNow.entrySet().stream()
                            .filter(emo -> emo.getValue() >= 0f)
                            .map(e -> e.toString())
                            .collect(Collectors.joining(DELIMITER)));
            System.out.println();
            if (feelingNow.entrySet().stream().filter(e -> e.getValue() >= 100).count() > 0) {
                System.out.println(brain.getName() + " has passed out from emotional trauma!");
                System.out.println("He was overwelmed by the emotion(s): " + feelingNow.entrySet().stream().filter(e -> e.getValue() >= 90).collect(Collectors.toList()));
                break;
            }
            if (feelingNow.entrySet().stream().filter(e -> e.getValue() > 0).count() == 0) {
                System.out.println(brain.getName() + " is emotionally dead inside - he/she has no feelings left what so ever!!!");
                break;
            }
        }
    }

    private static Feeling generateFeelingWithAgony() {
        Feeling feeling = Feeling.builder()
                .addEmotions(Arrays.asList(
                        Emotion.builder()
                                .emotionType(EmotionType.AGONY)
                                .amplitudePeak(99).amplitudeSustain(50)
                                .durationTime(20000)
                                .attack(10).decay(20).sustain(50).release(20)
                                .build()
                        )
                ).build();
        return feeling;
    }

    private static Feeling generateFeelingWithAntiAgony() {
        Feeling feeling = Feeling.builder()
                .addEmotions(Arrays.asList(
                        Emotion.builder()
                                .emotionType(EmotionType.AGONY)
                                .amplitudePeak(-20).amplitudeSustain(-10)
                                .durationTime(10000)
                                .attack(10).decay(20).sustain(50).release(20)
                                .build()
                        )
                ).build();
        return feeling;
    }

    private static Feeling generateRandomFeeling() {
        EmotionType emotionType = dice.randomEmotionType();
        Integer amplitudePeak = dice.getRandomFibonacci(7);
        Integer amplitudeSustain = dice.getRandomFibonacci(5);
        long duration = dice.getRandomFloatBetween(5000f, 60000f).longValue();
        Feeling feeling = Feeling.builder()
                .addEmotions(Arrays.asList(
                        Emotion.builder()
                                .emotionType(emotionType)
                                .amplitudePeak(amplitudePeak).amplitudeSustain(amplitudeSustain)
                                .durationTime(duration)
                                .attack(10).decay(30).sustain(50).release(10)
                                .build()
                        )
                ).build();
        return feeling;
    }
}