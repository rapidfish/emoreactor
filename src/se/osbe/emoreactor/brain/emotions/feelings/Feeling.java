package se.osbe.emoreactor.brain.emotions.feelings;

public interface Feeling {

	FeelingType getFeelingType();

	Double getAmplitude();
	
	long getInitialTime();
	
	long getDuration();

	void setInitialTime(long defaultTime);

}