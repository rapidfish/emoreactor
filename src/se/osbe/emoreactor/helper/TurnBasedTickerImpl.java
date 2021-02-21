package se.osbe.emoreactor.helper;

public class TurnBasedTickerImpl implements Ticker {
	private long ticker;

	public TurnBasedTickerImpl() {
		ticker = 0;
	}

	public long getTicTimeNow() {
		ticker++;
		return ticker;
	}
}
