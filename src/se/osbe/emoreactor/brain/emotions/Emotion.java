package se.osbe.emoreactor.brain.emotions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.emotions.feelings.AbstractFeeling;
import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

/*
	At runtime every brain is having an composite emotion, a sum of all added emotion at one point range in time.
	Emotions tends to fade towards their brains preset personalities over time. Like equalizing towards that as a stable fix point of balance for the individual personalities.
	The only thing that can change what that point of balance is, is if the brain experince anything that disturbs it teporary, when new emotions arrive from perceptions, or if an disorder related event occurs (personality changing moment in runtime).
*/
public class Emotion implements Cloneable {

	private final Map<FeelingType, Feeling> _feelingMap;
	private String _description;

	protected Emotion() {
		this("");
	}

	protected Emotion(String description) {
		_description = description;
		_feelingMap = new HashMap<FeelingType, Feeling>();
	}

	public Map<FeelingType, Feeling> getUnderlayingFeelings() {
		return _feelingMap;
	}

	public void addFeeling(Feeling feeling) {
		if (_feelingMap.containsKey(feeling.getFeelingType())) {
			Feeling tmp = _feelingMap.get(feeling.getFeelingType());
			int intencity = tmp.getIntencity() + feeling.getIntencity();
			((AbstractFeeling) tmp).setIntencity(intencity);
			_feelingMap.put(tmp.getFeelingType(), tmp);
		} else {
			_feelingMap.put(feeling.getFeelingType(), feeling);
		}
	}

	public void addFeelings(List<Feeling> feelings) {
		for (Feeling feeling : feelings) {
			addFeeling(feeling);
		}
	}

	public boolean removeFeeling(FeelingType emo) {
		if (_feelingMap.containsKey(emo)) {
			_feelingMap.remove(emo);
		}
		return false;
	}

	public boolean isFeelingWithinEmotion(FeelingType emo) {
		return _feelingMap.containsKey(emo);
	}

	public boolean isFeelingEmpty() {
		return _feelingMap.isEmpty();
	}

	public Feeling getEmotionFromFeelingOrNull(FeelingType type) {
		return _feelingMap.get(type);
	}

	public List<Feeling> getEmotionsAsList() {
		return (List<Feeling>) _feelingMap.values();
	}

	public String getDescription() {
		return _description;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Emotion: ");
		sb.append(_description).append(" ").append(_feelingMap.values());
		return sb.toString();
	}
}