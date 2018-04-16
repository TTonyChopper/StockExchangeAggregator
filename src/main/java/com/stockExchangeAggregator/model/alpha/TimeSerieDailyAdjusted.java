package com.stockExchangeAggregator.model.alpha;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
{ "1. open", "2. high", "3. low", "4. close", "5. adjusted close", "6. volume", "7. dividend amount", "8. split coefficient" })
public class TimeSerieDailyAdjusted extends TimeSerieAdjusted
{
	@JsonProperty("8. split coefficient")
	private String _8SplitCoefficient;

	@JsonProperty("8. split coefficient")
	public void setSplitCoefficient(String _8SplitCoefficient)
	{
		this._8SplitCoefficient = _8SplitCoefficient;
	}

	@JsonProperty("8. split coefficient")
	public String getSplitCoefficient()
	{
		return _8SplitCoefficient;
	}
}