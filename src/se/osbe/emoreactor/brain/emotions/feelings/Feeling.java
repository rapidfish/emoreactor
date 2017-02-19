package se.osbe.emoreactor.brain.emotions.feelings;

public interface Feeling {

	public FeelingType getFeelingType();

	public Double getAmplitude();
	
	public long getInitialTime();
	
	public long getDuration();

}