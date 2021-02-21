package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigTimeBasedImpl;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;

public class Demo {

    private Brain brain; // null = default configuration

    private EmotionBuilder emotionBuilder; // Builder to create input stimula for the reactor and the brain
    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process

    public Demo() throws InterruptedException {
        try {
            Personality personality = new Personality(); // no arg constructor gives default where all personality param pairs is in perfect balance (50%/50%)
            BrainConfig brainConfig = new BrainConfigTimeBasedImpl(personality);
            brain = new Brain(brainConfig); // default, if set to null
            brain.setPerceptionAwarenessPercentage(10);
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
            int duration = 10; //demo.dice.getRandomDoubleBetween(5d, 350d).intValue(); // 2s - 5min

            demo.brain.setPerceptionAwarenessPercentage(15); // awareness %
//            demo.brain.setPerceptionAwarenessPercentage(demo.dice.getRandomDoubleBetween(30d, 60d).intValue());
            System.out.println(String.format("|%d|", demo.brain.getTickCounter()));

            // Perception perception = new SightPerception(demo.emotionBuilder.addFeelings("ang=" + intensity + "," + duration + "s; Pos=3,3;").build(null));
            Perception perception = new SightPerception(demo.emotionBuilder.addFeelings("*=" + intensity + "," + duration + "s;").build(null));

            if (demo.brain.addInboundPerception(perception)) {
                System.err.println("Perception detected: " + perception.toString());
            } else {
                // System.out.println(perception.getPerceptionType().getDescription() + " - Perception passed undetected");
            }

            System.out.println(demo.brain.tic());

//            Stream.of(FeelingType.values()).map(t -> t.description() + ": " + demo.brain.getProgressType(t) + ", ").forEach(System.out::print);
//            System.out.println();

            Thread.sleep(1000);
        }
    }
}