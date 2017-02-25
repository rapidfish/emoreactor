package se.osbe.emoreactor.helper;

public class TurnBasedTickerImpl implements Ticker {
	private long _ticker;

	public TurnBasedTickerImpl() {
		_ticker = 0;
	}

	public long getTicTimeNow() {
		_ticker++;
		return _ticker;
	}
}
