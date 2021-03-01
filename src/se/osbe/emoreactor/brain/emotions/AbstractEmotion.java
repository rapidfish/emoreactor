package se.osbe.emoreactor.brain.emotions;

import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;

/**
 * Difference between feelings and emotions
 * <p>
 * https://www.quora.com/Whats-the-differences-between-feelings-and-emotions
 *
 * @author Oskar Bergstrom <rapidfish@me.com>
 */
public abstract class AbstractEmotion implements Emotion {

    private final static String ERROR_PARAM_IS_NULL = "One or more parameter(s) is set to null";

    private final EmotionType _emotionType;
    private final Float _amplitude;
    private final long _duration;
    private long _initialTime;

    @SuppressWarnings("unused")
    private AbstractEmotion() throws ReactorException {
        this(null, 0f, 0, 0);
    }

    protected AbstractEmotion(EmotionType feeling, Float amplitude, long initialTime, long duration)
            throws ReactorException {

        if (feeling == null) {
            throw new ReactorException(ERROR_PARAM_IS_NULL);
        }

        _emotionType = feeling;
        _amplitude = amplitude;
        _duration = duration;
        if (initialTime >= 0) {
            _initialTime = initialTime;
        } else {
            throw new ReactorException("Not possible to initialize with a negative initial time");
        }
    }

    public EmotionType getFeelingType() {
        return _emotionType;
    }

    public Float getAmplitude() {
        return _amplitude;
    }

    public long getInitialTime() {
        return _initialTime;
    }

    public void setInitialTime(long time) {
        _initialTime = time;
    }

    public long getDuration() {
        return _duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_amplitude == null) ? 0 : _amplitude.hashCode());
        result = prime * result + (int) (_duration ^ (_duration >>> 32));
        result = prime * result + ((_emotionType == null) ? 0 : _emotionType.hashCode());
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
        AbstractEmotion other = (AbstractEmotion) obj;
        if (_amplitude == null) {
            if (other._amplitude != null)
                return false;
        } else if (!_amplitude.equals(other._amplitude))
            return false;
        if (_duration != other._duration)
            return false;
        if (_emotionType != other._emotionType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getAmplitude() != null) {
            String duration = BrainHelper.getFormattedWithPrefix(getDuration());
            sb.append(_emotionType.name()).append("(amplitude=").append(getAmplitude().intValue()).append(", duration=")
                    .append(duration).append(")");
        }
        return sb.toString();
    }
}