package com.stockExchangeAggregator.providers;

import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.model.wrapper.AlphaRow;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.query.AlphaQueryBuilder;
import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;
import com.stockExchangeAggregator.providers.query.parameter.alpha.Function;

@SuppressWarnings("rawtypes")
public class FeedManager
{
	public final static String YAHOO_FEED_URL = "https://query1.finance.yahoo.com/v8/finance/chart/BTC-EUR?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";
	private APIWrapper<Yahoo, YahooRow> yahooApiWrapper;

	public final static String ALPHA_BASE_URL = "https://www.alphavantage.co/query?symbol=SAF.PA&interval=1min&apikey=BTIUZBLM7BTSIF7Z";
	public final static String ALPHA_FEED_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=SAF.PA&interval=1min&apikey=BTIUZBLM7BTSIF7Z";
	private APIWrapper<Alpha, AlphaRow> alphaApiWrapper;
	
	private AlphaQueryBuilder queryMgr = new AlphaQueryBuilder(ALPHA_BASE_URL);

	public FeedManager()
	{
		yahooApiWrapper = new APIWrapper<Yahoo, YahooRow>(Yahoo.class, YahooRow.class, YAHOO_FEED_URL);
		queryMgr.addParam(Function.TIME_SERIES_DAILY);
		alphaApiWrapper = new APIWrapper<Alpha, AlphaRow>(Alpha.class, AlphaRow.class, queryMgr.build().toString());
	}
	
	public String updateParam(QueryParameter param)
	{
		queryMgr.addParam(param);
		return queryMgr.build().toString();
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
