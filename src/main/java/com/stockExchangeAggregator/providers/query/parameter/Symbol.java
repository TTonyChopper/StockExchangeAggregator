package com.stockExchangeAggregator.providers.query.parameter;

import java.util.Map;
import java.util.TreeMap;

public class Symbol extends QueryParameter
{
	protected final Map<String, Symbol> symbols = new TreeMap<>();

	public Symbol(String name, String value)
	{
		super(name, value);
	}

	@Override
	public boolean validate()
	{
		return false;
	}
}
