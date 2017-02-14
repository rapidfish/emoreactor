package se.osbe.emoreactor.brain.emotions.feelings;

import se.osbe.emoreactor.brain.reactor.ReactorException;

public class AfraidFeeling extends AbstractFeeling {
	
	public AfraidFeeling(Double amplitude) throws ReactorException {
		super(FeelingType.AFRAID, amplitude);
	}
	
	public AfraidFeeling(int amplitude) throws ReactorException {
		super(FeelingType.AFRAID, new Double(amplitude));
	}
	
	public static void main(String[] args) throws ReactorException {
		AfraidFeeling afraidFeeling = new AfraidFeeling(100d);
		Double level = new Double(0);
		int i=0;
		do {
//			System.out.println((i++) + " " + afraidFeeling + " : " + level);
			level = afraidFeeling.tic();
			System.out.println(printEq(level) + " : " + level);
		} while (level != null);
	}
	
	public static String printEq(Double tal) {
		Double res = tal;
		int i = tal.intValue();
		StringBuilder sb = new StringBuilder();
		for(int x = 0; x < i;x++) {
			sb.append("=");
		}
		return sb.toString();
	}
}
