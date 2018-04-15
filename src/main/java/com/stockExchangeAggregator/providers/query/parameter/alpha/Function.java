package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class Function extends QueryParameter
{
	public final static Function TIME_SERIES_INTRADAY = new Function("TIME_SERIES_INTRADAY");
	public final static Function TIME_SERIES_DAILY = new Function("TIME_SERIES_DAILY");
	public final static Function TIME_SERIES_DAILY_ADJUSTED = new Function("TIME_SERIES_DAILY_ADJUSTED");
	public final static Function TIME_SERIES_WEEKLY = new Function("TIME_SERIES_WEEKLY");
	public final static Function TIME_SERIES_WEEKLY_ADJUSTED = new Function("TIME_SERIES_WEEKLY_ADJUSTED");
	public final static Function TIME_SERIES_MONTHLY = new Function("TIME_SERIES_MONTHLY");
	public final static Function TIME_SERIES_MONTHLY_ADJUSTED = new Function("TIME_SERIES_MONTHLY_ADJUSTED");
	public final static Function BATCH_STOCK_QUOTES = new Function("BATCH_STOCK_QUOTES");

	public Function(String value)
	{
		super("function", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
