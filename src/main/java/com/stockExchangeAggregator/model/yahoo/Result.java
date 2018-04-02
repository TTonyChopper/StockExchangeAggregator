
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
    "meta",
    "timestamp",
    "indicators"
})
public class Result {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("timestamp")
    private List<Long> timestamp = null;
    @JsonProperty("indicators")
    private Indicators indicators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Result withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    @JsonProperty("timestamp")
    public List<Long> getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(List<Long> timestamp) {
        this.timestamp = timestamp;
    }

    public Result withTimestamp(List<Long> timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("indicators")
    public Indicators getIndicators() {
        return indicators;
    }

    @JsonProperty("indicators")
    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    public Result withIndicators(Indicators indicators) {
        this.indicators = indicators;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("meta", meta).append("timestamp", timestamp).append("indicators", indicators).append("additionalProperties", additionalProperties).toString();
    }

}
