package com.stockExchangeAggregator.providers;

import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.model.wrapper.AlphaRow;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.yahoo.Yahoo;

@SuppressWarnings("rawtypes")
public class FeedManager
{
	public final static String YAHOO_FEED_URL = "https://query1.finance.yahoo.com/v8/finance/chart/BTC-EUR?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";
	private APIWrapper<Yahoo, YahooRow> yahooApiWrapper;

	public final static String ALPHA_FEED_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=SAF.PA&interval=1min&apikey=BTIUZBLM7BTSIF7Z";
	private APIWrapper<Alpha, AlphaRow> alphaApiWrapper;

	public FeedManager()
	{
		yahooApiWrapper = new APIWrapper<Yahoo, YahooRow>(Yahoo.class, YahooRow.class, YAHOO_FEED_URL);
		alphaApiWrapper = new APIWrapper<Alpha, AlphaRow>(Alpha.class, AlphaRow.class, ALPHA_FEED_URL);
	}

	public APIWrapper getYahooApiWrapper()
	{
		return yahooApiWrapper;
	}

	public APIWrapper getAlphaApiWrapper()
	{
		return alphaApiWrapper;
	}
}
