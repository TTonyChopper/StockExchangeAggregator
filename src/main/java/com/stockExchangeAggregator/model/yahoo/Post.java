
package com.stockExchangeAggregator.model.yahoo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timezone", "end", "start", "gmtoffset" })
public class Post {

	@JsonProperty("timezone")
	private String timezone;
	@JsonProperty("end")
	private Long end;
	@JsonProperty("start")
	private Long start;
	@JsonProperty("gmtoffset")
	private Long gmtoffset;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("timezone")
	public String getTimezone() {
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Post withTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	@JsonProperty("end")
	public Long getEnd() {
		return end;
	}

	@JsonProperty("end")
	public void setEnd(Long end) {
		this.end = end;
	}

	public Post withEnd(Long end) {
		this.end = end;
		return this;
	}

	@JsonProperty("start")
	public Long getStart() {
		return start;
	}

	@JsonProperty("start")
	public void setStart(Long start) {
		this.start = start;
	}

	public Post withStart(Long start) {
		this.start = start;
		return this;
	}

	@JsonProperty("gmtoffset")
	public Long getGmtoffset() {
		return gmtoffset;
	}

	@JsonProperty("gmtoffset")
	public void setGmtoffset(Long gmtoffset) {
		this.gmtoffset = gmtoffset;
	}

	public Post withGmtoffset(Long gmtoffset) {
		this.gmtoffset = gmtoffset;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Post withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("timezone", timezone).append("end", end).append("start", start)
				.append("gmtoffset", gmtoffset).append("additionalProperties", additionalProperties).toString();
	}

}
