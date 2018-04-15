package com.stockExchangeAggregator.providers.query.parameter;

public abstract class QueryParameter
{
	protected final String name;
	protected final String value;

	public QueryParameter(String name, String value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public abstract boolean validate();

	public String getParam()
	{
		return name + "=" + value;
	}
}
