package com.stockExchangeAggregator.model.alpha;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "1. open", "2. high", "3. low", "4. close", "5. volume" })
public class TimeSerie {

	private String name;
	
	@JsonProperty("1. open")
	private String _1Open;
	@JsonProperty("2. high")
	private String _2High;
	@JsonProperty("3. low")
	private String _3Low;
	@JsonProperty("4. close")
	private String _4Close;
	@JsonProperty("5. volume")
	private String _5Volume;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("1. open")
	public String get1Open() {
		return _1Open;
	}

	@JsonProperty("1. open")
	public void set1Open(String _1Open) {
		this._1Open = _1Open;
	}

	@JsonProperty("2. high")
	public String get2High() {
		return _2High;
	}

	@JsonProperty("2. high")
	public void set2High(String _2High) {
		this._2High = _2High;
	}

	@JsonProperty("3. low")
	public String get3Low() {
		return _3Low;
	}

	@JsonProperty("3. low")
	public void set3Low(String _3Low) {
		this._3Low = _3Low;
	}

	@JsonProperty("4. close")
	public String get4Close() {
		return _4Close;
	}

	@JsonProperty("4. close")
	public void set4Close(String _4Close) {
		this._4Close = _4Close;
	}

	@JsonProperty("5. volume")
	public String get5Volume() {
		return _5Volume;
	}

	@JsonProperty("5. volume")
	public void set5Volume(String _5Volume) {
		this._5Volume = _5Volume;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}