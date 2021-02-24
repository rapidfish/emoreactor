package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigDefaultImpl;
import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.personality.PersonalityBaseline;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;
import java.util.stream.Stream;

public class Demo {

    private Brain brain; // null = default configuration

    private EmotionBuilder emotionBuilder; // Builder to create input stimula for the reactor and the brain
    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process

    public Demo() throws InterruptedException {
        try {
            PersonalityBaseline personality = new PersonalityBaseline(); // no arg constructor gives default where all personality param pairs is in perfect balance (50%/50%)
            BrainConfig brainConfig = new BrainConfigDefaultImpl(personality);
            brainConfig.setPerceptionAwareness(25); // This is 0% by default, so we need to set it.
            brain = new Brain(brainConfig); // gives default brain config when set to null
            brain.setPerceptionAwarenessPercentage(5);
            dice = brain.getBrainConfig().getDiceHelper();
            emotionBuilder = brain.getBrainConfig().getEmotionBuilder();
        } catch (ReactorException e) {
            e.printStackTrace();
        }
        System.out.println("Starting EmoReactor for John Doe, with personality:");
        System.out.println(brain.getPersonality().toString() + "\n");
        System.out.println(String.format("Awareness: %s", brain.getPerceptionAwarenessPercentage()) + "%\n");
    }

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        Map<FeelingType, Double> emotionNow;

        while (true) {
            int intensity = demo.dice.getRandomDoubleBetween(1d, 5d).intValue(); // 1 - 10
            int duration = demo.dice.getRandomDoubleBetween(5d, 30d).intValue(); // 2s - 5min

//            demo.brain.setPerceptionAwarenessPercentage(demo.dice.getRandomDoubleBetween(30d, 60d).intValue());
            System.out.println(String.format("Time unit |%d|", demo.brain.getTickCounter()));

            // Perception perception = new SightPerception(demo.emotionBuilder.addFeelings("ang=" + intensity + "," + duration + "s; Pos=3,3;").build(null));
            Emotion emotion = demo.emotionBuilder.addFeelings("dep=" + intensity + "," + duration + "s;").build(null);

            if (demo.brain.addInboundPerception(emotion)) {
                System.err.println("Detected change: " + emotion.toString());
            } else {
                // System.out.println("No change...");
            }

            Stream.of(demo.brain.tic()).filter(i -> i.get(FeelingType.DEPRESSED).intValue() > 0).forEach(System.out::println);

//            Stream.of(FeelingType.values()).map(t -> t.description() + ": " + demo.brain.getProgressType(t) + ", ").forEach(System.out::print);
//            System.out.println();

            Thread.sleep(200);
        }
    }
}