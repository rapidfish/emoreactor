package se.osbe.emoreactor.helper;

import java.util.Random;

public class DiceHelper {

	private final static Random _rnd = new Random(System.currentTimeMillis());

	public DiceHelper() {
	}

	public boolean getFiftyFifty() {
		return DiceHelper._rnd.nextBoolean();
	}

	public boolean isNotLucky(Double chance, Double dice) {
		return !isLucky(chance, dice);
	}

	public boolean isLucky(Double chance, Double dice) {
		Double result = getRandomDoubleBetween(1d, dice);
		return (result.compareTo(chance) <= 0);
	}

	public boolean percentageChance(int percentage){
		if(percentage == 0) {
			return false;
		} else if(percentage == 100) {
			return true;
		}
		return getRandomDoubleBetween(0d, 100d).compareTo((double) (percentage % 100)) < 0;
	}

	public Double getRandomPercentage() {
		return getRandomDoubleBetween(0d, 100d);
	}

	public Double getRandomDoubleBetween(Double min, Double max) {
		if (min.compareTo(max) == 0) {
			return min;
		}
		Double lowest = getLowestDouble(min, max);
		Double diff = getHighestDouble(min, max) - lowest;
		Double num = diff * DiceHelper._rnd.nextDouble(); // (offset + 1);
		return (lowest + num);
	}

	private Double getLowestDouble(Double t1, Double t2) {
		return (t1.compareTo(t2) < 0) ? t1 : t2;
	}

	private Double getHighestDouble(Double t1, Double t2) {
		return (t1.compareTo(t2) < 0) ? t2 : t1;
	}

	public Integer getRandomFibonacci(int order){
		return fibonacci(_rnd.nextInt(order));
	}

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
		DiceHelper dice = new DiceHelper();
		System.out.println(dice.fibonacci(10));
		System.out.println("---");
		for(int i = 0; i < 10; i++) {
			System.out.println(dice.getRandomFibonacci(10));
		}
	}

}