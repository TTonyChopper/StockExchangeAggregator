
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
@JsonPropertyOrder({
    "low",
    "close",
    "open",
    "volume",
    "high"
})
public class Quote {

    @JsonProperty("low")
    private List<Long> low = null;
    @JsonProperty("close")
    private List<Double> close = null;
    @JsonProperty("open")
    private List<Long> open = null;
    @JsonProperty("volume")
    private List<Long> volume = null;
    @JsonProperty("high")
    private List<Long> high = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("low")
    public List<Long> getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(List<Long> low) {
        this.low = low;
    }

    public Quote withLow(List<Long> low) {
        this.low = low;
        return this;
    }

    @JsonProperty("close")
    public List<Double> getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(List<Double> close) {
        this.close = close;
    }

    public Quote withClose(List<Double> close) {
        this.close = close;
        return this;
    }

    @JsonProperty("open")
    public List<Long> getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(List<Long> open) {
        this.open = open;
    }

    public Quote withOpen(List<Long> open) {
        this.open = open;
        return this;
    }

    @JsonProperty("volume")
    public List<Long> getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(List<Long> volume) {
        this.volume = volume;
    }

    public Quote withVolume(List<Long> volume) {
        this.volume = volume;
        return this;
    }

    @JsonProperty("high")
    public List<Long> getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(List<Long> high) {
        this.high = high;
    }

    public Quote withHigh(List<Long> high) {
        this.high = high;
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

    public Quote withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("low", low).append("close", close).append("open", open).append("volume", volume).append("high", high).append("additionalProperties", additionalProperties).toString();
    }

}
