package se.osbe.emoreactor.helper;

public class TurnTickerImpl implements Ticker {
	private long _ticker;

	public TurnTickerImpl() {
		_ticker = 0;
	}

	public long getTicTimeNow() {
		_ticker++;
		return _ticker;
	}
}
