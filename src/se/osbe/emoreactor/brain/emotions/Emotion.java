package se.osbe.emoreactor.brain.emotions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.osbe.emoreactor.brain.emotions.feelings.Feeling;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;

public class Emotion implements Cloneable {

	private final Map<FeelingType, Feeling> _feelingMap;

	protected Emotion() {
		_feelingMap = new HashMap<FeelingType, Feeling>();
	}

	public void storeFeelings(List<Feeling> feelings) {
		for (Feeling feeling : feelings) {
			storeFeeling(feeling);
		}
	}

	public void storeFeeling(Feeling feeling) {
		FeelingType type = feeling.getFeelingType();
		_feelingMap.put(type, feeling);
	}
	
	public boolean removeFeeling(FeelingType type) {
		if (_feelingMap.containsKey(type)) {
			_feelingMap.remove(type);
		}
		return false;
	}

	public boolean hasFeeling(FeelingType type) {
		return _feelingMap.containsKey(type);
	}

	public Feeling getFeeling(FeelingType type) {
		return _feelingMap.get(type);
	}

	public List<Feeling> getFeelings() {
		return new LinkedList<Feeling>(_feelingMap.values());
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
		Emotion other = (Emotion) obj;
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