
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
@JsonPropertyOrder({
    "pre",
    "regular",
    "post"
})
public class CurrentTradingPeriod {

    @JsonProperty("pre")
    private Pre pre;
    @JsonProperty("regular")
    private Regular regular;
    @JsonProperty("post")
    private Post post;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pre")
    public Pre getPre() {
        return pre;
    }

    @JsonProperty("pre")
    public void setPre(Pre pre) {
        this.pre = pre;
    }

    public CurrentTradingPeriod withPre(Pre pre) {
        this.pre = pre;
        return this;
    }

    @JsonProperty("regular")
    public Regular getRegular() {
        return regular;
    }

    @JsonProperty("regular")
    public void setRegular(Regular regular) {
        this.regular = regular;
    }

    public CurrentTradingPeriod withRegular(Regular regular) {
        this.regular = regular;
        return this;
    }

    @JsonProperty("post")
    public Post getPost() {
        return post;
    }

    @JsonProperty("post")
    public void setPost(Post post) {
        this.post = post;
    }

    public CurrentTradingPeriod withPost(Post post) {
        this.post = post;
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

    public CurrentTradingPeriod withAFdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("pre", pre).append("regular", regular).append("post", post).append("additionalProperties", additionalProperties).toString();
    }

}
