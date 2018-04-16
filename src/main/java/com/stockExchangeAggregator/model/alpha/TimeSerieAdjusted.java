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
@JsonPropertyOrder(
{ "1. open", "2. high", "3. low", "4. close", "5. adjusted close", "6. volume", "7. dividend amount" })
public class TimeSerieAdjusted extends TimeSerie
{
	@JsonProperty("1. open")
	private String _1Open;
	@JsonProperty("2. high")
	private String _2High;
	@JsonProperty("3. low")
	private String _3Low;
	@JsonProperty("4. close")
	private String _4Close;
	@JsonProperty("5. adjusted close")
	private String _5AdjustedClose;
	@JsonProperty("6. volume")
	private String _6Volume;
	@JsonProperty("7. dividend amount")
	private String _7DividendAmount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("1. open")
	@Override
	public String getOpen()
	{
		return _1Open;
	}

	@JsonProperty("1. open")
	@Override
	public void setOpen(String _1Open)
	{
		this._1Open = _1Open;
	}

	@JsonProperty("2. high")
	@Override
	public String getHigh()
	{
		return _2High;
	}

	@JsonProperty("2. high")
	@Override
	public void setHigh(String _2High)
	{
		this._2High = _2High;
	}

	@JsonProperty("3. low")
	@Override
	public String getLow()
	{
		return _3Low;
	}

	@JsonProperty("3. low")
	@Override
	public void setLow(String _3Low)
	{
		this._3Low = _3Low;
	}

	@JsonProperty("4. close")
	@Override
	public String getClose()
	{
		return _4Close;
	}

	@JsonProperty("4. close")
	@Override
	public void setClose(String _4Close)
	{
		this._4Close = _4Close;
	}

	@JsonProperty("5. adjusted close")
	public void setAdjustedClose(String _5AdjustedClose)
	{
		this._5AdjustedClose = _5AdjustedClose;
	}
	
	@JsonProperty("5. adjusted close")
	public String getAdjustedClose()
	{
		return _5AdjustedClose;
	}

	@JsonProperty("6. volume")
	@Override
	public void setVolume(String _6Volume)
	{
		this._6Volume = _6Volume;
	}
	
	@JsonProperty("6. volume")
	@Override
	public String getVolume()
	{
		return _6Volume;
	}

	@JsonProperty("7. dividend amount")
	public void setDividendAmount(String _7DividendAmount)
	{
		this._7DividendAmount = _7DividendAmount;
	}
	
	@JsonProperty("7. dividend amount")
	public String getDividendAmount()
	{
		return _7DividendAmount;
	}

	@JsonAnyGetter
	@Override
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	@Override
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}

}