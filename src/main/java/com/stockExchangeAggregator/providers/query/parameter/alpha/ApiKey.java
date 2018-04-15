package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class ApiKey extends QueryParameter
{
	public final static ApiKey DEFAULT = new ApiKey("BTIUZBLM7BTSIF7Z");

	public ApiKey(String value)
	{
		super("apikey", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
