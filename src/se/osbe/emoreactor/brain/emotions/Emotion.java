package se.osbe.emoreactor.brain.emotions;

public interface Emotion {

	EmotionType getFeelingType();

	Double getAmplitude();
	
	long getInitialTime();
	
	long getDuration();

	void setInitialTime(long defaultTime);

}