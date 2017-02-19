package se.osbe.emoreactor.brain.emotions;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import se.osbe.emoreactor.brain.emotions.feelings.AbstractFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AfraidFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AgonyFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AliveFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.AngerFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.ConfusedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.DepressedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.emotions.feelings.HappyFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.HelplessFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.HurtFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.IndifferentFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.InterestedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.JudgementalFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.LovingFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.OpenFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.PeacefulFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.PositiveFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.RelaxedFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.SadFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.StrongFeeling;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public class EmotionBuilder {

	private List<Feeling> _feelings;
	private final DiceHelper _dice;

	public EmotionBuilder() {
		_feelings = new LinkedList<Feeling>();
		_dice = new DiceHelper();
	}

	public EmotionBuilder addEmotion(Emotion emo){
		emo.getFeelings();
		return this;
	}
	
	public EmotionBuilder addFeeling(FeelingType emotionEnum, Double intencity, long initialTime, long duration) throws ReactorException {
		AbstractFeeling feeling;
		switch (emotionEnum) {
		case AGONY:
			feeling = (AbstractFeeling) new AgonyFeeling(intencity, initialTime, duration);
			break;
		case AFRAID:
			feeling = (AbstractFeeling) new AfraidFeeling(intencity, initialTime, duration);
			break;
		case ALIVE:
			feeling = (AbstractFeeling) new AliveFeeling(intencity, initialTime, duration);
			break;
		case ANGER:
			feeling = (AbstractFeeling) new AngerFeeling(intencity, initialTime, duration);
			break;
		case CONFUSED:
			feeling = (AbstractFeeling) new ConfusedFeeling(intencity, initialTime, duration);
			break;
		case DEPRESSED:
			feeling = (AbstractFeeling) new DepressedFeeling(intencity, initialTime, duration);
			break;
		case HAPPY:
			feeling = (AbstractFeeling) new HappyFeeling(intencity, initialTime, duration);
			break;
		case HELPLESS:
			feeling = (AbstractFeeling) new HelplessFeeling(intencity, initialTime, duration);
			break;
		case HURT:
			feeling = (AbstractFeeling) new HurtFeeling(intencity, initialTime, duration);
			break;
		case INDIFFERENT:
			feeling = (AbstractFeeling) new IndifferentFeeling(intencity, initialTime, duration);
			break;
		case INTERESTED:
			feeling = (AbstractFeeling) new InterestedFeeling(intencity, initialTime, duration);
			break;
		case JUDGEMENTAL:
			feeling = (AbstractFeeling) new JudgementalFeeling(intencity, initialTime, duration);
			break;
		case LOVING:
			feeling = (AbstractFeeling) new LovingFeeling(intencity, initialTime, duration);
			break;
		case OPEN:
			feeling = (AbstractFeeling) new OpenFeeling(intencity, initialTime, duration);
			break;
		case PEACEFUL:
			feeling = (AbstractFeeling) new PeacefulFeeling(intencity, initialTime, duration);
			break;
		case POSITIVE:
			feeling = (AbstractFeeling) new PositiveFeeling(intencity, initialTime, duration);
			break;
		case RELAXED:
			feeling = (AbstractFeeling) new RelaxedFeeling(intencity, initialTime, duration);
			break;
		case SAD:
			feeling = (AbstractFeeling) new SadFeeling(intencity, initialTime, duration);
			break;
		case STRONG:
			feeling = (AbstractFeeling) new StrongFeeling(intencity, initialTime, duration);
			break;
		default:
			throw new ReactorException("Missing enum for constructor");
		}
		_feelings.add(feeling);
		return this;
	}

	public EmotionBuilder addFeeling(Feeling feeling) {
		_feelings.add(feeling);
		return this;
	}

	public EmotionBuilder addFeelings(Feeling... feelings) {
		for (Feeling feeling : feelings) {
			_feelings.add(feeling);
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
	 * 
	 * Feelings written on the form [feeling name]=[intencity];<br>
	 * where [feeling name] is the name of the basic feeling<br>
	 * and [intencity] is the intencity of that feeling,<br>
	 * finally every feeling is terminated using a semicolon ';'.<br>
	 * <br>
	 * 
	 * You can write a short for feelings as long as you use enough significant
	 * letters to identify a feeling<br>
	 * like this examples: "agon=5;a=-4;p=-1;p=-1;hel=14;he=-5". It state that
	 * agony, positive, Helpless should be set<br>
	 * <br>
	 * 
	 * Feelings may be set multiple times, but are combined into just one in the
	 * end. All intencity values are all added together into just one value for
	 * intencity for each distict feeling.
	 * 
	 * @param string
	 *            with feelings in plain text, eg. "agony=5;"
	 * @return
	 * @throws NumberFormatException
	 * @throws ReactorException
	 */
	public EmotionBuilder addFeelings(String script) throws NumberFormatException, ReactorException {
		if (script == null || script.length() == 0 || !script.contains(";")) {
			throw new ReactorException(
					"Error, no script to process due to null or zero length or missing ';' termination");
		}
		String[] statements = script.split(";");
		for (String statement : statements) {
			String[] operands = statement.split("=");
			if (operands.length == 2) {
				FeelingType emo = new BrainHelper().getEmotionEnumForPattern(operands[0]);
				Double tmp = new Double(0);
				if(operands[1].startsWith("?")){
					tmp = _dice.getRandomPercentage();
				} else if(operands[1].startsWith("*")){
					tmp = _dice.getRandomPercentage();
				}  else if(operands[1].startsWith("-*")) {
					Double p = _dice.getRandomPercentage();
					tmp = _dice.getFiftyFifty() ? p : ((-1) * p);
				} else {
					tmp = Double.parseDouble(operands[1]);
				}
				addFeeling(emo, tmp, 1, 100l);
			}
		}
		return this;
	}

	public Emotion build() throws ReactorException {
		return build(null);
	}

	public Emotion build(String description) throws ReactorException {
		if (_feelings == null) {
			throw new ReactorException("EmotiongBuilder has no feelings to build up on!");
		}
		Emotion emotion = new Emotion(StringUtils.isNotEmpty(description) ? description : "anonomus");
		emotion.addFeelings(_feelings);
		reset();
		return emotion;
	}

	public EmotionBuilder reset() {
		_feelings.clear();
		return this;
	}

	public static void main(String[] args) throws Exception {
		EmotionBuilder feelingBuilder = new EmotionBuilder();
//		Emotion feeling1 = feelingBuilder.addFeelings("agon=15;afr=10;hel=24").build("My Feeling1");
		Emotion feeling2 = feelingBuilder.reset().addFeeling(new RelaxedFeeling(3d, 1, 100)).build("My feeling2"); // .addFeelings("Alive=5;OPE=4;Confused=2").build();
//		System.out.println(feeling1);
		System.out.println(feeling2);
	}
}