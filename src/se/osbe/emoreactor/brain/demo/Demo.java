package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigDefaultImpl;
import se.osbe.emoreactor.brain.emotions.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;
import java.util.stream.Collectors;

public class Demo {

    private static final String DELIMITER = ", ";

    // Zeh Brane!
    private Brain brain; // null = default configuration

    public Demo() throws InterruptedException {

        try {
            PersonalityBaseline personality = new PersonalityBaseline(); // no arg constructor gives default where all personality param pairs is in perfect balance (50%/50%)
            BrainConfig brainConfig = new BrainConfigDefaultImpl(personality);
            brainConfig.setPerceptionAwareness(5); // This is 0% by default, so we need to set it.
            brain = new Brain(brainConfig); // gives default brain config when set to null
            brain.setPerceptionAwarenessPercentage(5);
        } catch (ReactorException e) {
            e.printStackTrace();
        }
        System.out.println("Starting EmoReactor for John Doe, with personality:");
        System.out.println(brain.getPersonality().toString() + "\n");
        System.out.println(String.format("Awareness: %s", brain.getPerceptionAwarenessPercentage()) + "%\n");
    }

    public static void main(String[] args) throws Exception {

        Demo demo = new Demo();
        // Builder to create input stimuli for the reactor and the brain
        var feelingBuilder = demo.brain.getBrainConfiguration().getFeelingBuilder();

        // A dice helper to produce random numbers as we don not (yet) have 'real world' stimuli input
        final DiceHelper dice = demo.brain.getBrainConfiguration().getDiceHelper();

        Map<EmotionType, Float> feelingNow;
        boolean isGo = true;
        while (true) {
            int intensity = dice.getRandomFloatBetween(5f, 20f).intValue(); // 1 - 10
            int duration = dice.getRandomFloatBetween(5f, 60f).intValue(); // 2s - 5min
//            int awareness = dice.getRandomFloatBetween(5f, 15f).intValue();
            int awareness = 100;
            demo.brain.setPerceptionAwarenessPercentage(awareness);
            System.out.println(String.format("Time unit |%d|, Awareness: %d", demo.brain.getTickerCounter(), awareness) + "%");
            // Perception perception = new SightPerception(demo.emoBuilder.addFeelings("ang=" + intensity + "," + duration + "s; Pos=3,3;").build(null));
            Feeling feeling = isGo ? feelingBuilder.addFeelings("agony=" + intensity + "," + duration + "s;").build(null) : null;
            if (demo.brain.offerInboundFeeling(feeling)) {
                isGo = false;
                System.out.println("Detected inbound feeling: " + feeling.toString());
            } else {
                System.out.println("No change...");
            }
            feelingNow = demo.brain.tic();
            if (!demo.brain.isReactorDry()) {
                System.out.println("Emotions -> " +
                        feelingNow.entrySet().stream()
                                .filter(e -> e.getValue() > 0d)
                                .map(e -> e.toString())
                                .collect(Collectors.joining(DELIMITER)));

//                if (
//                        feelingNow.get(EmotionType.AGONY).intValue() > 5 &&
//                        feelingNow.get(EmotionType.AFRAID).intValue() > 5
//                ) {
//                    System.out.println("------------------------------------------------> I'm scared for real!!!");
//                }

            } else {
                System.out.println("I feel nothing!");
            }

            System.out.println();
            Thread.sleep(1000); // 1000 = 1s
        }
    }
}