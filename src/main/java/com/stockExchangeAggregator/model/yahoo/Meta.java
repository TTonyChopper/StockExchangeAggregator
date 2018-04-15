
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
{ "currency", "symbol", "exchangeName", "instrumentType", "firstTradeDate", "gmtoffset", "timezone",
		"exchangeTimezoneName", "chartPreviousClose", "previousClose", "scale", "currentTradingPeriod",
		"tradingPeriods", "dataGranularity", "validRanges" })
public class Meta
{

	@JsonProperty("currency")
	private String currency;
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("exchangeName")
	private String exchangeName;
	@JsonProperty("instrumentType")
	private String instrumentType;
	@JsonProperty("firstTradeDate")
	private Long firstTradeDate;
	@JsonProperty("gmtoffset")
	private Long gmtoffset;
	@JsonProperty("timezone")
	private String timezone;
	@JsonProperty("exchangeTimezoneName")
	private String exchangeTimezoneName;
	@JsonProperty("chartPreviousClose")
	private Double chartPreviousClose;
	@JsonProperty("previousClose")
	private Double previousClose;
	@JsonProperty("scale")
	private Long scale;
	@JsonProperty("currentTradingPeriod")
	private CurrentTradingPeriod currentTradingPeriod;
	@JsonProperty("tradingPeriods")
	private List<List<TradingPeriod>> tradingPeriods = null;
	@JsonProperty("dataGranularity")
	private String dataGranularity;
	@JsonProperty("validRanges")
	private List<String> validRanges = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("currency")
	public String getCurrency()
	{
		return currency;
	}

	@JsonProperty("currency")
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public Meta withCurrency(String currency)
	{
		this.currency = currency;
		return this;
	}

	@JsonProperty("symbol")
	public String getSymbol()
	{
		return symbol;
	}

	@JsonProperty("symbol")
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public Meta withSymbol(String symbol)
	{
		this.symbol = symbol;
		return this;
	}

	@JsonProperty("exchangeName")
	public String getExchangeName()
	{
		return exchangeName;
	}

	@JsonProperty("exchangeName")
	public void setExchangeName(String exchangeName)
	{
		this.exchangeName = exchangeName;
	}

	public Meta withExchangeName(String exchangeName)
	{
		this.exchangeName = exchangeName;
		return this;
	}

	@JsonProperty("instrumentType")
	public String getInstrumentType()
	{
		return instrumentType;
	}

	@JsonProperty("instrumentType")
	public void setInstrumentType(String instrumentType)
	{
		this.instrumentType = instrumentType;
	}

	public Meta withInstrumentType(String instrumentType)
	{
		this.instrumentType = instrumentType;
		return this;
	}

	@JsonProperty("firstTradeDate")
	public Long getFirstTradeDate()
	{
		return firstTradeDate;
	}

	@JsonProperty("firstTradeDate")
	public void setFirstTradeDate(Long firstTradeDate)
	{
		this.firstTradeDate = firstTradeDate;
	}

	public Meta withFirstTradeDate(Long firstTradeDate)
	{
		this.firstTradeDate = firstTradeDate;
		return this;
	}

	@JsonProperty("gmtoffset")
	public Long getGmtoffset()
	{
		return gmtoffset;
	}

	@JsonProperty("gmtoffset")
	public void setGmtoffset(Long gmtoffset)
	{
		this.gmtoffset = gmtoffset;
	}

	public Meta withGmtoffset(Long gmtoffset)
	{
		this.gmtoffset = gmtoffset;
		return this;
	}

	@JsonProperty("timezone")
	public String getTimezone()
	{
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	public Meta withTimezone(String timezone)
	{
		this.timezone = timezone;
		return this;
	}

	@JsonProperty("exchangeTimezoneName")
	public String getExchangeTimezoneName()
	{
		return exchangeTimezoneName;
	}

	@JsonProperty("exchangeTimezoneName")
	public void setExchangeTimezoneName(String exchangeTimezoneName)
	{
		this.exchangeTimezoneName = exchangeTimezoneName;
	}

	public Meta withExchangeTimezoneName(String exchangeTimezoneName)
	{
		this.exchangeTimezoneName = exchangeTimezoneName;
		return this;
	}

	@JsonProperty("chartPreviousClose")
	public Double getChartPreviousClose()
	{
		return chartPreviousClose;
	}

	@JsonProperty("chartPreviousClose")
	public void setChartPreviousClose(Double chartPreviousClose)
	{
		this.chartPreviousClose = chartPreviousClose;
	}

	public Meta withChartPreviousClose(Double chartPreviousClose)
	{
		this.chartPreviousClose = chartPreviousClose;
		return this;
	}

	@JsonProperty("previousClose")
	public Double getPreviousClose()
	{
		return previousClose;
	}

	@JsonProperty("previousClose")
	public void setPreviousClose(Double previousClose)
	{
		this.previousClose = previousClose;
	}

	public Meta withPreviousClose(Double previousClose)
	{
		this.previousClose = previousClose;
		return this;
	}

	@JsonProperty("scale")
	public Long getScale()
	{
		return scale;
	}

	@JsonProperty("scale")
	public void setScale(Long scale)
	{
		this.scale = scale;
	}

	public Meta withScale(Long scale)
	{
		this.scale = scale;
		return this;
	}

	@JsonProperty("currentTradingPeriod")
	public CurrentTradingPeriod getCurrentTradingPeriod()
	{
		return currentTradingPeriod;
	}

	@JsonProperty("currentTradingPeriod")
	public void setCurrentTradingPeriod(CurrentTradingPeriod currentTradingPeriod)
	{
		this.currentTradingPeriod = currentTradingPeriod;
	}

	public Meta withCurrentTradingPeriod(CurrentTradingPeriod currentTradingPeriod)
	{
		this.currentTradingPeriod = currentTradingPeriod;
		return this;
	}

	@JsonProperty("tradingPeriods")
	public List<List<TradingPeriod>> getTradingPeriods()
	{
		return tradingPeriods;
	}

	@JsonProperty("tradingPeriods")
	public void setTradingPeriods(List<List<TradingPeriod>> tradingPeriods)
	{
		this.tradingPeriods = tradingPeriods;
	}

	public Meta withTradingPeriods(List<List<TradingPeriod>> tradingPeriods)
	{
		this.tradingPeriods = tradingPeriods;
		return this;
	}

	@JsonProperty("dataGranularity")
	public String getDataGranularity()
	{
		return dataGranularity;
	}

	@JsonProperty("dataGranularity")
	public void setDataGranularity(String dataGranularity)
	{
		this.dataGranularity = dataGranularity;
	}

	public Meta withDataGranularity(String dataGranularity)
	{
		this.dataGranularity = dataGranularity;
		return this;
	}

	@JsonProperty("validRanges")
	public List<String> getValidRanges()
	{
		return validRanges;
	}

	@JsonProperty("validRanges")
	public void setValidRanges(List<String> validRanges)
	{
		this.validRanges = validRanges;
	}

	public Meta withValidRanges(List<String> validRanges)
	{
		this.validRanges = validRanges;
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

	public Meta withAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this).append("currency", currency).append("symbol", symbol)
				.append("exchangeName", exchangeName).append("instrumentType", instrumentType)
				.append("firstTradeDate", firstTradeDate).append("gmtoffset", gmtoffset).append("timezone", timezone)
				.append("exchangeTimezoneName", exchangeTimezoneName).append("chartPreviousClose", chartPreviousClose)
				.append("previousClose", previousClose).append("scale", scale)
				.append("currentTradingPeriod", currentTradingPeriod).append("tradingPeriods", tradingPeriods)
				.append("dataGranularity", dataGranularity).append("validRanges", validRanges)
				.append("additionalProperties", additionalProperties).toString();
	}

}
