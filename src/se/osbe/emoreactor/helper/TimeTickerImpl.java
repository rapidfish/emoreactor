package se.osbe.emoreactor.helper;

public class TimeTickerImpl implements Ticker {
	public long getTicTimeNow() {
		return System.currentTimeMillis();
	}
}
