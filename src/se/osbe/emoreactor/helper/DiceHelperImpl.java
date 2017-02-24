package se.osbe.emoreactor.helper;

import java.util.Random;

public class DiceHelperImpl implements DiceHelper {

	private final static Random _rnd = new Random(System.currentTimeMillis());

	public DiceHelperImpl() {
	}

	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#getFiftyFifty()
	 */
	@Override
	public boolean getFiftyFifty() {
		return DiceHelperImpl._rnd.nextBoolean();
	}
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#isNotLucky(java.lang.Double, java.lang.Double)
	 */
	@Override
	public boolean isNotLucky(Double chance, Double dice) {
		return !isLucky(chance, dice);
	}

	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#isLucky(java.lang.Double, java.lang.Double)
	 */
	@Override
	public boolean isLucky(Double chance, Double dice) {
		Double result = getRandomDoubleBetween(1d, dice);
		return (result.compareTo(chance) <= 0);
	}

	// Error +0,000001127 (when tested with one billion iterations) in favor for other half 
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#percentageChance(int)
	 */
	@Override
	public boolean percentageChance(int percentage){
		if(percentage == 0) {
			return false;
		} else if(percentage == 100) {
			return true;
		}
		return getRandomDoubleBetween(0d, 100d).compareTo(new Double(percentage % 100)) < 0;
	}
	
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#getRandomPercentage()
	 */
	@Override
	public Double getRandomPercentage() {
		return getRandomDoubleBetween(0d, 100d);
	}
	
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#getRandomDoubleBetween(java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double getRandomDoubleBetween(Double min, Double max) {
		if (min.compareTo(max) == 0) {
			return min;
		}
		Double lowest = getLowestDouble(min, max);
		Double diff = getHighestDouble(min, max) - lowest;
		Double num = diff * DiceHelperImpl._rnd.nextDouble(); // (offset + 1);
		return (lowest + num);
	}

	private Double getLowestDouble(Double t1, Double t2) {
		return (t1.compareTo(t2) < 0) ? t1 : t2;
	}

	private Double getHighestDouble(Double t1, Double t2) {
		return (t1.compareTo(t2) < 0) ? t2 : t1;
	}
	
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#getRandomFibonacci(int)
	 */
	@Override
	public Integer getRandomFibonacci(int order){
		return fibonacci(_rnd.nextInt(order));
	}
	
	/* (non-Javadoc)
	 * @see se.osbe.emoreactor.helper.DiceHelperIF#fibonacci(java.lang.Integer)
	 */
	@Override
	public Integer fibonacci(Integer n) {
		Integer before = 0;
		Integer after = 1;
		Integer result = 0;
		for(Integer i=0; i < n; i++) {
			result = before + after;
			before = after;
			after = result;
		}
		return after;
	}
	
	public static void main(String[] args) {
		DiceHelper dice = new DiceHelperImpl();
		System.out.println(dice.fibonacci(10));
		System.out.println("---");
		for(int i = 0; i < 10; i++) {
			System.out.println(dice.getRandomFibonacci(10));
		}
	}
}
