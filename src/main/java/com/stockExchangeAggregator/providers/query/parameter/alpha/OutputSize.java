package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class OutputSize extends QueryParameter
{
	public final static OutputSize COMPACT = new OutputSize("COMPACT");
	public final static OutputSize FULL = new OutputSize("FULL");

	public OutputSize(String value)
	{
		super("outputsize", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
