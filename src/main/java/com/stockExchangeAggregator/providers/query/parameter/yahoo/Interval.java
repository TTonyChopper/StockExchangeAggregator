package com.stockExchangeAggregator.providers.query.parameter.yahoo;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class Interval extends QueryParameter
{
	public final static Range DAY = new Range("1d");
	public final static Range FIVE_DAYS = new Range("5d");
	public final static Range QUARTER = new Range("3mo");
	public final static Range HALF_YEAR = new Range("6mo");
	public final static Range YEAR = new Range("1y");
	public final static Range TWO_YEARS = new Range("2y");
	public final static Range FIVE_YEARS = new Range("5y");
	public final static Range TEN_YEARS = new Range("10y");
	public final static Range YTD = new Range("ytd");
	public final static Range MAX = new Range("max");

	public Interval(String value)
	{
		super("interval", value);
	}

	@Override
	public boolean validate()
	{
		return false;
	}

}
