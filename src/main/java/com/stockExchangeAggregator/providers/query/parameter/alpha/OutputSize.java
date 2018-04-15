package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class OutputSize extends QueryParameter
{
	public final static Function COMPACT = new Function("COMPACT");
	public final static Function FULL = new Function("FULL");

	public OutputSize(String name, String value)
	{
		super("outputsize", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
