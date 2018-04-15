package com.stockExchangeAggregator.model.alpha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockExchangeAggregator.model.acme.POJOInterface;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
{ "Meta Data", "Time Serie" })
public class Alpha implements POJOInterface
{

	private MetaData metaData;
	private String timeSeriesKey;
	private List<TimeSerie> timeSeries = new ArrayList<>();

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public MetaData getMetaData()
	{
		return metaData;
	}

	public void setMetaData(MetaData metaData)
	{
		this.metaData = metaData;
	}

	public String getTimeSeriesKey()
	{
		return timeSeriesKey;
	}

	public void setTimeSeriesKey(String timeSeriesKey)
	{
		this.timeSeriesKey = timeSeriesKey;
	}

	public List<TimeSerie> getTimeSeries()
	{
		return timeSeries;
	}

	public void addTimeSerie(TimeSerie timeSerie)
	{
		timeSeries.add(timeSerie);
	}

	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}
}
