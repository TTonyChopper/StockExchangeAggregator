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
@JsonPropertyOrder({
"symbol",
"name",
"date",
"isEnabled",
"type",
"iexId"
})
public class Symbol {

@JsonProperty("symbol")
private String symbol;
@JsonProperty("name")
private String name;
@JsonProperty("date")
private String date;
@JsonProperty("isEnabled")
private Boolean isEnabled;
@JsonProperty("type")
private String type;
@JsonProperty("iexId")
private String iexId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("symbol")
public String getSymbol() {
return symbol;
}

@JsonProperty("symbol")
public void setSymbol(String symbol) {
this.symbol = symbol;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("date")
public String getDate() {
return date;
}

@JsonProperty("date")
public void setDate(String date) {
this.date = date;
}

@JsonProperty("isEnabled")
public Boolean getIsEnabled() {
return isEnabled;
}

@JsonProperty("isEnabled")
public void setIsEnabled(Boolean isEnabled) {
this.isEnabled = isEnabled;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("iexId")
public String getIexId() {
return iexId;
}

@JsonProperty("iexId")
public void setIexId(String iexId) {
this.iexId = iexId;
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