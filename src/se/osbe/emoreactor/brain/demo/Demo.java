package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigDefaultImpl;
import se.osbe.emoreactor.brain.emotions.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.brain.feelings.FeelingBuilder;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;
import java.util.stream.Stream;

public class Demo {

    // Zeh Brane!
    private Brain brain; // null = default configuration

    public Demo() throws InterruptedException {

        try {
            PersonalityBaseline personality = new PersonalityBaseline(); // no arg constructor gives default where all personality param pairs is in perfect balance (50%/50%)
            BrainConfig brainConfig = new BrainConfigDefaultImpl(personality);
            brainConfig.setPerceptionAwareness(25); // This is 0% by default, so we need to set it.
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
        var feelingBuilder = demo.brain.getBrainConfig().getFeelingBuilder();

        // A dice helper to produce random numbers as we don not (yet) have 'real world' stimuli input
        final DiceHelper dice = demo.brain.getBrainConfig().getDiceHelper();

        Map<EmotionType, Double> feelingNow;

        while (true) {
            int intensity = dice.getRandomDoubleBetween(1d, 5d).intValue(); // 1 - 10
            int duration = dice.getRandomDoubleBetween(5d, 30d).intValue(); // 2s - 5min

//            demo.brain.setPerceptionAwarenessPercentage(demo.dice.getRandomDoubleBetween(30d, 60d).intValue());
            System.out.println(String.format("Time unit |%d|", demo.brain.getTickCounter()));

            // Perception perception = new SightPerception(demo.emoBuilder.addFeelings("ang=" + intensity + "," + duration + "s; Pos=3,3;").build(null));
            Feeling feeling = feelingBuilder.addFeelings("dep=" + intensity + "," + duration + "s;").build(null);

            if (demo.brain.addInboundPerception(feeling)) {
                System.err.println("Detected inbound feeling: " + feeling.toString());
            } else {
                // System.out.println("No change...");
            }

            if (!demo.brain.isReactorDry()) {
                Stream.of(demo.brain.tic()).filter(i -> i.get(EmotionType.DEPRESSED).intValue() > 0).map(i -> i.get(EmotionType.DEPRESSED)).findAny().ifPresent(System.out::println);
            }

//            Stream.of(EmotionType.values()).map(t -> t.description() + ": " + demo.brain.getProgressType(t) + ", ").forEach(System.out::print);
//            System.out.println();

            Thread.sleep(200);
        }
    }
}