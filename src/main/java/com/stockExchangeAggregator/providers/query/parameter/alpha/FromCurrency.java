package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class FromCurrency extends QueryParameter
{
	public FromCurrency(String value)
	{
		super("from_currency", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
