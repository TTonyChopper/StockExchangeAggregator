
package com.stockExchangeAggregator.model.yahoo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
{ "result", "error" })
public class Chart
{

	@JsonProperty("result")
	private List<Result> result;
	@JsonProperty("error")
	private Object error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("result")
	public List<Result> getResult()
	{
		return result;
	}

	@JsonProperty("result")
	public void setResult(List<Result> result)
	{
		this.result = result;
	}

	public Chart withResult(List<Result> result)
	{
		this.result = result;
		return this;
	}

	@JsonProperty("error")
	public Object getError()
	{
		return error;
	}

	@JsonProperty("error")
	public void setError(Object error)
	{
		this.error = error;
	}

	public Chart withError(Object error)
	{
		this.error = error;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}

	public Chart withAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this).append("result", result).append("error", error)
				.append("additionalProperties", additionalProperties).toString();
	}

}
