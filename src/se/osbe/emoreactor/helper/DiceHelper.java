package se.osbe.emoreactor.helper;

public interface DiceHelper {

	boolean getFiftyFifty();

	boolean isNotLucky(Double chance, Double dice);

	boolean isLucky(Double chance, Double dice);

	/**
	 *  Error +0,000001127 (when tested with one billion iterations) in favor for other half 
	 * @param percentage 
	 * @return true if random outcome is below or equal to percentage given as argument.
	 */
	boolean percentageChance(int percentage);

	Double getRandomPercentage();

	Double getRandomDoubleBetween(Double min, Double max);

	Integer getRandomFibonacci(int order);

	Integer fibonacci(Integer n);

}