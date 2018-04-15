package com.stockExchangeAggregator.providers.query.parameter.alpha;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class DataType extends QueryParameter
{
	public final static Function JSON = new Function("json");
	public final static Function CVS = new Function("csv ");

	public DataType(String value)
	{
		super("datatype", value);
	}

	@Override
	public boolean validate()
	{
		return true;
	}
}
