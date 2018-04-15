package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.Symbol;

public class AlphaSymbol extends Symbol
{
	private AlphaSymbol(String value)
	{
		super("symbol", value);
	}

	public Symbol addSymbol(String name)
	{
		return symbols.put(name, new AlphaSymbol(name));
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
