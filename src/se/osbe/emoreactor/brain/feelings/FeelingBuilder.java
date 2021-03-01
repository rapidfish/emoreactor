package se.osbe.emoreactor.brain.feelings;

import se.osbe.emoreactor.brain.emotions.*;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FeelingBuilder {

    private final DiceHelper _diceHelper;
    private List<Emotion> _emotions;

    public FeelingBuilder() {
        _emotions = new LinkedList<>();
        _diceHelper = new DiceHelper();
    }


    public FeelingBuilder addEmotion(Feeling feeling) {
        feeling.getEmotions();
        return this;
    }

    public FeelingBuilder addFeeling(EmotionType emotionEnum, Float intensity, long initialTime, long duration)
            throws ReactorException {
        Emotion emotion;
        switch (emotionEnum) {
            case AGONY:
                emotion = (AbstractEmotion) new AgonyEmotion(intensity, initialTime, duration);
                break;
            case AFRAID:
                emotion = (AbstractEmotion) new AfraidEmotion(intensity, initialTime, duration);
                break;
            case ALIVE:
                emotion = (AbstractEmotion) new AliveEmotion(intensity, initialTime, duration);
                break;
            case ANGER:
                emotion = (AbstractEmotion) new AngerEmotion(intensity, initialTime, duration);
                break;
            case CONFUSED:
                emotion = (AbstractEmotion) new ConfusedEmotion(intensity, initialTime, duration);
                break;
            case DEPRESSED:
                emotion = (AbstractEmotion) new DepressedEmotion(intensity, initialTime, duration);
                break;
            case HAPPY:
                emotion = (AbstractEmotion) new HappyEmotion(intensity, initialTime, duration);
                break;
            case HELPLESS:
                emotion = (AbstractEmotion) new HelplessEmotion(intensity, initialTime, duration);
                break;
            case HURT:
                emotion = (AbstractEmotion) new HurtEmotion(intensity, initialTime, duration);
                break;
            case INDIFFERENT:
                emotion = (AbstractEmotion) new IndifferentEmotion(intensity, initialTime, duration);
                break;
            case INTERESTED:
                emotion = (AbstractEmotion) new InterestedEmotion(intensity, initialTime, duration);
                break;
            case JUDGEMENTAL:
                emotion = (AbstractEmotion) new JudgementalEmotion(intensity, initialTime, duration);
                break;
            case LOVING:
                emotion = (AbstractEmotion) new LovingEmotion(intensity, initialTime, duration);
                break;
            case OPEN:
                emotion = (AbstractEmotion) new OpenEmotion(intensity, initialTime, duration);
                break;
            case PEACEFUL:
                emotion = (AbstractEmotion) new PeacefulEmotion(intensity, initialTime, duration);
                break;
            case POSITIVE:
                emotion = (AbstractEmotion) new PositiveEmotion(intensity, initialTime, duration);
                break;
            case RELAXED:
                emotion = (AbstractEmotion) new RelaxedEmotion(intensity, initialTime, duration);
                break;
            case SADNESS:
                emotion = (AbstractEmotion) new SadEmotion(intensity, initialTime, duration);
                break;
            case STRONG:
                emotion = (AbstractEmotion) new StrongEmotion(intensity, initialTime, duration);
                break;
            case RELIEF:
                emotion = (AbstractEmotion) new ReliefEmotion(intensity, initialTime, duration);
                break;
            default:
                throw new ReactorException("Missing enum for constructor");
        }
        _emotions.add(emotion);
        return this;
    }

    public FeelingBuilder addFeeling(Emotion emotion) {
        _emotions.add(emotion);
        return this;
    }

    public FeelingBuilder addFeelings(Emotion... emotions) {
        for (Emotion emotion : emotions) {
            _emotions.add(emotion);
        }
        return this;
    }

    /**
     * Add scripted feelings in plain text format.<br>
     * <br>
     * <strong>Feelings are:</strong><br>
     * Agony, Anger, Depressed, Confused, Helpless, Indifferent, Afraid, Hurt,
     * Sad, Judgemental,<br>
     * Open, Loving, Happy, Interested, Alive, Positive, Peaceful, Strong,
     * Relaxed<br>
     * <br>
     * <p>
     * Feelings written on the form [feeling name]=[intensity];<br>
     * where [feeling name] is the name of the basic feeling<br>
     * and [intensity] is the intensity of that feeling,<br>
     * finally every feeling is terminated using a semicolon ';'.<br>
     * <br>
     * <p>
     * You can write a short for feelings as long as you use enough significant
     * letters to identify a feeling<br>
     * like this examples: "agon=5;a=-4;p=-1;p=-1;hel=14;he=-5". It state that
     * agony, positive, Helpless should be set<br>
     * <br>
     * <p>
     * Feelings may be set multiple times, but are combined into just one in the
     * end. All intensity values are all added together into just one value for
     * intensity for each distict feeling.
     *
     * @param script with feelings in plain text, eg. "agony=5:3s;"
     * @return
     * @throws NumberFormatException
     * @throws ReactorException
     */
    public FeelingBuilder addFeelings(String script) throws NumberFormatException, ReactorException {
        if (script == null || script.length() == 0 || !script.contains(";")) {
            throw new ReactorException(
                    "Error, no script to process due to null or zero length or missing ';' termination");
        }
        List<String> statements = new ArrayList<>(Arrays.asList(script.replaceAll("[ ]", "").split(";")));
        statements.forEach(statement -> {

            // split into parts for each operand: operand=4,10[s|m|h]
            String parts[] = statement.split("=");
            EmotionType emotionType = null;
            if (parts[0].contains("*")) {
                Float rnd = _diceHelper.getRandomFloatBetween(0f, ((float) EmotionType.values().length));
                emotionType = EmotionType.values()[rnd.intValue()];
            } else {
                emotionType = BrainHelper.getEmotionEnumForPattern(parts[0]);
            }
            Float intensity = null;
            String durationString = null;
            String prefix = null;
            long duration = 0;
            // split params to operand
            String params[] = parts[1].split(",");
            if (params.length > 0) {
                intensity = Float.parseFloat(params[0]);
                durationString = new String(params[1]);
                if (durationString.contains("*")) {
                    duration = _diceHelper.getRandomPercentage().longValue();
                } else {
                    duration = Long.parseLong(durationString.replaceAll("\\D", ""));
                }
                prefix = durationString.replaceAll("[0-9*]", "");
                if (prefix.startsWith("s")) {
                    duration *= 1000;
                } else if (prefix.startsWith("m")) {
                    duration *= 60000;
                } else if (prefix.startsWith("h")) {
                    duration *= 3600000;
                } else if (prefix.startsWith("d")) {
                    duration *= 86400000;
                } else if (prefix.startsWith("w")) {
                    duration *= 604800000;
                }
            } else {
                intensity = _diceHelper.getRandomFloatBetween(1f, 100f);
                duration = 1000 * _diceHelper.getRandomFloatBetween(1f, 60f).longValue();
            }
            try {
                addFeeling(emotionType, intensity, 0, duration);
            } catch (ReactorException e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    public Feeling build() throws ReactorException {
        return build(null);
    }

    public Feeling build(Long initialTime) throws ReactorException {
        if (_emotions == null) {
            throw new ReactorException("EmotiongBuilder has no feelings to build up on!");
        }
        if (initialTime == null) {
            long now = System.currentTimeMillis();
            _emotions.forEach(emotion -> {
                emotion.setInitialTime(now);
            });
        }
        Feeling feeling = new Feeling();
        feeling.storeFeelings(_emotions);
        reset();
        return feeling;
    }

    public FeelingBuilder reset() {
        _emotions.clear();
        return this;
    }

//    public static void main(String[] args) throws Exception {
//        FeelingBuilder eb = new FeelingBuilder();
//        eb.addFeeling(new RelaxedEmotion(3d, 0, 60000));
//        eb.addFeeling(new LovingEmotion(40d, 0, 5 * 60 * 1000));
//        eb.addFeelings("*=10,40s;");
//        Feeling feeling1 = eb.build(null);
//
//        eb.addFeeling(new RelaxedEmotion(3d, 0, 60000));
//        eb.addFeeling(new LovingEmotion(40d, 0, 5 * 60 * 1000));
//        eb.addFeelings("*=10,40s;");
//        Feeling feeling2 = eb.build(null);
//
//        // System.out.println(emotionBuilder);
//        System.out.println("Emotion1:");
//        System.out.println(feeling1);
//        feeling1.getFeelings().forEach(f -> {
//            System.out.println("feeling: " + f.getFeelingType() + ", max amplitude: " + f.getAmplitude() + ", initial time: " + f.getInitialTime() + ", duration: " + f.getDuration() + "ms");
//        });
//        ;
//        System.out.println();
//        System.out.println("Emotion2:");
//        System.out.println(feeling2);
//        feeling2.getFeelings().forEach(f -> {
//            System.out.println("feeling: " + f.getFeelingType() + ", max amplitude: " + f.getAmplitude() + ", initial time: " + f.getInitialTime() + ", duration: " + f.getDuration() + "ms");
//        });
//        ;
//    }
}
