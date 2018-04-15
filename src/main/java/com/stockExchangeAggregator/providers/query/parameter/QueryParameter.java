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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	//Based on name only
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryParameter other = (QueryParameter) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
