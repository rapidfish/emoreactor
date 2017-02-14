package se.osbe.emoreactor.brain.emotions.feelings;

public interface Feeling {

	public Double tic();
	
	public Double getAmplitude();
	
	public Double getDuration();
	
	public FeelingType getFeelingType();
	
	public String getFeelingName();
	
}
