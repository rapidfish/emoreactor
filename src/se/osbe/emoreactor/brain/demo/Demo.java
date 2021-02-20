package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.BrainConfig;
import se.osbe.emoreactor.brain.config.BrainConfigTurnBasedTickerImpl;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.personality.Personality;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.Map;

public class Demo {
    private Brain _brain; // null = default configuration
    private DiceHelper _dice;
    private BrainHelper _brainhelper;
    private EmotionBuilder _eb;
    private Map<FeelingType, Double> _emoNow;

    public Demo() throws InterruptedException {
        try {
            Personality personality = new Personality(); // default, balance is 50% for all personality params
            BrainConfig brainConfig = new BrainConfigTurnBasedTickerImpl(personality);
            _brain = new Brain(brainConfig); // default, if set to null
            _brain.setPerceptionAwarenessPercentage(70);
            _dice = _brain.getBrainConfig().getDiceHelper();
            _brainhelper = _brain.getBrainConfig().getBrainHelper();
            _eb = _brain.getBrainConfig().getEmotionBuilder();
        } catch (ReactorException e) {
            e.printStackTrace();
        }

        System.out.println("Starting Demo with Joe Smith, having a personality like this... ");
        System.out.println(_brain.getPersonality().toString() + "\n");
        Thread.sleep(3000);

        try {
            while (true) {
                Thread.sleep(1000);
                nextValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Demo();
    }

    private void nextValue() throws NumberFormatException, ReactorException {
//        if (_brain.isReactorDry()) {
        int intensity = _dice.getRandomDoubleBetween(3d, 15d).intValue();
        int duration = _dice.getRandomDoubleBetween(3d, 60d).intValue();
        Perception perception = new SightPerception(_eb.addFeelings("ang=" + intensity + "," + duration + "s; Pos=3,3;").build(null));
        _brain.addInboundPerception(perception);
//        }
        _emoNow = _brain.tic();
        System.out.println(_emoNow.toString() + "\n");
    }
}