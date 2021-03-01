package se.osbe.emoreactor.brain.emotions;

public interface Emotion {

	EmotionType getFeelingType();

	Float getAmplitude();
	
	long getInitialTime();
	
	long getDuration();

	void setInitialTime(long defaultTime);

}