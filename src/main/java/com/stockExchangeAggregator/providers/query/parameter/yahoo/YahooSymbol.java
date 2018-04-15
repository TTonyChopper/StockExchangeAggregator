package com.stockExchangeAggregator.providers.query.parameter.yahoo;

import com.stockExchangeAggregator.providers.query.parameter.Symbol;

public class YahooSymbol extends Symbol
{
	public YahooSymbol(String name, String value)
	{
		super("symbol", value);
	}

	@Override
	public String getParam()
	{
		return value;
	}
	
	
}
