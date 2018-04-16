package com.stockExchangeAggregator.model.alpha;

import java.util.Map;

public abstract class TimeSerie
{
	//yyyy-MM-dd
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public abstract String getOpen();

	public abstract void setOpen(String _Open);

	public abstract String getHigh();

	public abstract void setHigh(String _High);

	public abstract String getLow();

	public abstract void setLow(String _Low);

	public abstract String getClose();

	public abstract void setClose(String _Close);

	public abstract String getVolume();

	public abstract void setVolume(String _Volume);

	public abstract Map<String, Object> getAdditionalProperties();

	public abstract void setAdditionalProperty(String name, Object value);

}