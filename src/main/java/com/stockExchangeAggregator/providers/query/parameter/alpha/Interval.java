package com.stockExchangeAggregator.providers.query.parameter.alpha;

import java.util.ArrayList;
import java.util.List;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class Interval extends QueryParameter
{
	public final static List<Interval> intervals = new ArrayList<>();
	
	public final static Interval MIN_1 = new Interval("1min");
	public final static Interval MIN_5 = new Interval("5min");
	public final static Interval MIN_15 = new Interval("15min");
	public final static Interval MIN_30 = new Interval("30min");
	public final static Interval MIN_60 = new Interval("60min");

	public Interval(String value)
	{
		super("interval", value);
		intervals.add(this);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
