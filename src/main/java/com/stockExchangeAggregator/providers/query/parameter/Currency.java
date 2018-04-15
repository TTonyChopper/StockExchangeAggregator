package com.stockExchangeAggregator.providers.query.parameter;

import java.util.Set;
import java.util.TreeSet;

public final class Currency
{
	public final Set<String> currencies = new TreeSet<>();
	
	private Currency()
	{
	}
	
	public boolean addCurrency(String name)
	{
		return currencies.add(name);
	}
}
