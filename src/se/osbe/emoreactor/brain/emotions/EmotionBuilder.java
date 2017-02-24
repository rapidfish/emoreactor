package se.osbe.emoreactor.brain.emotions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
import se.osbe.emoreactor.helper.BrainHelperImpl;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelperImpl;
import se.osbe.emoreactor.helper.DiceHelper;

public class EmotionBuilder {

	private List<Feeling> _feelings;
	private final BrainHelper _brainHelper;
	private final DiceHelper _diceHelper;

	public EmotionBuilder() {
		_feelings = new LinkedList<Feeling>();
		_brainHelper = new BrainHelperImpl();
		_diceHelper =  new DiceHelperImpl();
	}

	public EmotionBuilder addEmotion(Emotion emo) {
		emo.getFeelings();
		return this;
	}

	public EmotionBuilder addFeeling(FeelingType emotionEnum, Double intensity, long initialTime, long duration)
			throws ReactorException {
		Feeling feeling;
		switch (emotionEnum) {
		case AGONY:
			feeling = (AbstractFeeling) new AgonyFeeling(intensity, initialTime, duration);
			break;
		case AFRAID:
			feeling = (AbstractFeeling) new AfraidFeeling(intensity, initialTime, duration);
			break;
		case ALIVE:
			feeling = (AbstractFeeling) new AliveFeeling(intensity, initialTime, duration);
			break;
		case ANGER:
			feeling = (AbstractFeeling) new AngerFeeling(intensity, initialTime, duration);
			break;
		case CONFUSED:
			feeling = (AbstractFeeling) new ConfusedFeeling(intensity, initialTime, duration);
			break;
		case DEPRESSED:
			feeling = (AbstractFeeling) new DepressedFeeling(intensity, initialTime, duration);
			break;
		case HAPPY:
			feeling = (AbstractFeeling) new HappyFeeling(intensity, initialTime, duration);
			break;
		case HELPLESS:
			feeling = (AbstractFeeling) new HelplessFeeling(intensity, initialTime, duration);
			break;
		case HURT:
			feeling = (AbstractFeeling) new HurtFeeling(intensity, initialTime, duration);
			break;
		case INDIFFERENT:
			feeling = (AbstractFeeling) new IndifferentFeeling(intensity, initialTime, duration);
			break;
		case INTERESTED:
			feeling = (AbstractFeeling) new InterestedFeeling(intensity, initialTime, duration);
			break;
		case JUDGEMENTAL:
			feeling = (AbstractFeeling) new JudgementalFeeling(intensity, initialTime, duration);
			break;
		case LOVING:
			feeling = (AbstractFeeling) new LovingFeeling(intensity, initialTime, duration);
			break;
		case OPEN:
			feeling = (AbstractFeeling) new OpenFeeling(intensity, initialTime, duration);
			break;
		case PEACEFUL:
			feeling = (AbstractFeeling) new PeacefulFeeling(intensity, initialTime, duration);
			break;
		case POSITIVE:
			feeling = (AbstractFeeling) new PositiveFeeling(intensity, initialTime, duration);
			break;
		case RELAXED:
			feeling = (AbstractFeeling) new RelaxedFeeling(intensity, initialTime, duration);
			break;
		case SAD:
			feeling = (AbstractFeeling) new SadFeeling(intensity, initialTime, duration);
			break;
		case STRONG:
			feeling = (AbstractFeeling) new StrongFeeling(intensity, initialTime, duration);
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
	 * Feelings written on the form [feeling name]=[intensity];<br>
	 * where [feeling name] is the name of the basic feeling<br>
	 * and [intensity] is the intensity of that feeling,<br>
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
	 * end. All intensity values are all added together into just one value for
	 * intensity for each distict feeling.
	 * 
	 * @param string
	 *            with feelings in plain text, eg. "agony=5:3s;"
	 * @return
	 * @throws NumberFormatException
	 * @throws ReactorException
	 */
	public EmotionBuilder addFeelings(String script) throws NumberFormatException, ReactorException {
		if (script == null || script.length() == 0 || !script.contains(";")) {
			throw new ReactorException(
					"Error, no script to process due to null or zero length or missing ';' termination");
		}
		List<String> statements = new ArrayList<>(Arrays.asList(script.replaceAll("[ ]", "").split(";")));
		statements.forEach(statement -> {
			
			// split into parts for each operand: operand=4,10[s|m|h]
			String parts[] = statement.split("=");
			FeelingType feelingType = null;
			if(parts[0].contains("*")){
				Double rnd = _diceHelper.getRandomDoubleBetween(0d, (new Double(FeelingType.values().length)));
				feelingType = FeelingType.values()[rnd.intValue()];
			} else {
				feelingType = new BrainHelperImpl().getEmotionEnumForPattern(parts[0]);
			}
			Double intensity = null;
			String durationString = null;
			String prefix = null;
			long duration = 0;
			// split params to operand
			String params[] = parts[1].split(",");
			if(params.length > 0) {
				intensity = Double.parseDouble(params[0]);
				durationString = new String(params[1]);
				prefix = durationString.replaceAll("\\d", "");
				duration = Long.parseLong(durationString.replaceAll("\\D", ""));	
				if(prefix.startsWith("s")) {
					duration *= 1000;
				} else if(prefix.startsWith("m")) { 
					duration *= 60000;
				} else if(prefix.startsWith("h")) { 
					duration *= 3600000;
				} else if(prefix.startsWith("d")) { 
					duration *= 86400000;
				}  else if(prefix.startsWith("w")) { 
					duration *= 604800000;
				}
			}else{ 
				intensity = _diceHelper.getRandomDoubleBetween(1d, 100d);
				duration = 1000 * _diceHelper.getRandomDoubleBetween(1d, 60d).longValue();
			}
			try {
				addFeeling(feelingType, intensity, _brainHelper.getTimeNow(), duration);
			} catch (ReactorException e) {
				e.printStackTrace();
			}
		});
		return this;
	}

	public Emotion build() throws ReactorException {
		if (_feelings == null) {
			throw new ReactorException("EmotiongBuilder has no feelings to build up on!");
		}
		Emotion emotion = new Emotion();
		emotion.storeFeelings(_feelings);
		reset();
		return emotion;
	}

	public EmotionBuilder reset() {
		_feelings.clear();
		return this;
	}

	public static void main(String[] args) throws Exception {
		EmotionBuilder eb = new EmotionBuilder();
		// Emotion feeling1 =
		// emotionBuilder.addFeelings("agon=15;afr=10;hel=24").build("My
		// Feeling1");
		eb.addFeeling(new RelaxedFeeling(3d, 0, 60000));
		eb.addFeeling(new LovingFeeling(40d, 0, 5*60*1000));
		eb.addFeelings("*=10,40s;");
		
		
		Emotion feeling = eb.build();
		
		// System.out.println(emotionBuilder);
		System.out.println(feeling);
		feeling.getFeelings().forEach(f->{System.out.println("feeling: " + f.getFeelingType() + ", max amplitude: " + f.getAmplitude() + ", initial time: " + f.getInitialTime() + ", duration: " + f.getDuration() + "ms");});;
	}
}