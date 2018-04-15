package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class ToCurrency extends QueryParameter
{
	public ToCurrency(String value)
	{
		super("to_currency", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
