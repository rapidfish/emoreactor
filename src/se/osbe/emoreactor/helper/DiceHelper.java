package se.osbe.emoreactor.helper;

import java.util.Random;

public class DiceHelper {

	private final static Random _rnd = new Random(System.currentTimeMillis());

	public DiceHelper() {
	}

	public boolean getFiftyFifty() {
		return DiceHelper._rnd.nextBoolean();
	}

	public boolean isNotLucky(Float chance, Float dice) {
		return !isLucky(chance, dice);
	}

	public boolean isLucky(Float chance, Float dice) {
		Float result = getRandomFloatBetween(1f, dice);
		return (result.compareTo(chance) <= 0);
	}

	public boolean percentageChance(int percentage){
		if(percentage == 0) {
			return false;
		} else if(percentage == 100) {
			return true;
		}
		return getRandomFloatBetween(0f, 100f).compareTo((float) (percentage % 100)) < 0;
	}

	public Float getRandomPercentage() {
		return getRandomFloatBetween(0f, 100f);
	}

	public Float getRandomFloatBetween(Float min, Float max) {
		if (min.compareTo(max) == 0) {
			return min;
		}
		Float lowest = getLowestFloat(min, max);
		Float diff = getHighestFloat(min, max) - lowest;
		Float num = diff * DiceHelper._rnd.nextFloat(); // (offset + 1);
		return (lowest + num);
	}

	private Float getLowestFloat(Float t1, Float t2) {
		return (t1.compareTo(t2) < 0) ? t1 : t2;
	}

	private Float getHighestFloat(Float t1, Float t2) {
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