package se.osbe.emoreactor.brain.feelings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.emotions.Emotion;
import se.osbe.emoreactor.brain.emotions.EmotionType;

public class Feeling implements Cloneable {

	private final Map<EmotionType, Emotion> _feelingMap;

	protected Feeling() {
		_feelingMap = new HashMap<>();
	}

	public void storeFeelings(List<Emotion> emotions) {
		for (Emotion emotion : emotions) {
			storeFeeling(emotion);
		}
	}

	public void storeFeeling(Emotion emotion) {
		EmotionType type = emotion.getFeelingType();
		_feelingMap.put(type, emotion);
	}
	
	public boolean removeFeeling(EmotionType type) {
		if (_feelingMap.containsKey(type)) {
			_feelingMap.remove(type);
		}
		return false;
	}

	public boolean hasEmotion(EmotionType type) {
		return _feelingMap.containsKey(type);
	}

	public Emotion getEmotion(EmotionType type) {
		return _feelingMap.get(type);
	}

	public List<Emotion> getEmotions() {
		return new LinkedList<Emotion>(_feelingMap.values());
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_feelingMap == null) ? 0 : _feelingMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feeling other = (Feeling) obj;
		if (_feelingMap == null) {
			if (other._feelingMap != null)
				return false;
		} else if (!_feelingMap.equals(other._feelingMap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(_feelingMap.values());
		return sb.toString();
	}
}