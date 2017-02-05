package se.osbe.emoreactor.helper;

import java.util.Random;

public class DiceHelper {

	private final Random _rnd;

	public DiceHelper() {
		_rnd = new Random(System.nanoTime());
	}

	public boolean getFiftyFifty() {
		return getRandomIntBetween(0, 1) == 0;
	}
	public boolean isNotLucky(int chance, int dice) {
		return !isLucky(chance, dice);
	}

	public boolean isLucky(int chance, int dice) {
		Integer result = getRandomIntBetween(1, dice);
		return result <= chance;
	}

	public boolean percentageChance(int percentage){
		if(percentage == 0) {
			return false;
		} else if(percentage == 100) {
			return true;
		}
		return getRandomIntBetween(0, 100) < (percentage % 100);
	}
	
	public int getRandomPercentage() {
		return getRandomIntBetween(0, 100);
	}
	
	public int getRandomIntBetween(int min, int max) {
		if (min == max) {
			return min;
		}
		int lowest = getLowestInt(min, max);
		int offset = getHighestInt(min, max) - lowest;
		int num = _rnd.nextInt(offset + 1);
		return (lowest + num);
	}

	private int getLowestInt(int t1, int t2) {
		return (t1 < t2) ? t1 : t2;
	}

	private int getHighestInt(int t1, int t2) {
		return (t1 < t2) ? t2 : t1;
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
		for(int i = 0; i < 46; i++) {
			System.out.println(new DiceHelper().fibonacci(i));
		}
	}
}
