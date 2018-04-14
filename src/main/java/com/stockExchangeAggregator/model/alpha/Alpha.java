package com.stockExchangeAggregator.model.alpha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.yahoo.Chart;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Meta Data", "Time Serie" })
public class Alpha implements POJOInterface{

	@JsonProperty("Meta Data")
	private MetaData metaData;
	@JsonProperty("Time Series")
	private List<TimeSerie> timeSeries;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Meta Data")
	public MetaData getMetaData() {
		return metaData;
	}

	@JsonProperty("Meta Data")
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	@JsonProperty("Time Series")
	public List<TimeSerie> getTimeSeries() {
		return timeSeries;
	}

	@JsonProperty("Time Series")
	public void addTimeSerie(TimeSerie timeSerie) {
		timeSeries.add(timeSerie);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public Chart getChart() {
		return null;
	}

}
