package com.stockExchangeAggregator.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SlideEndEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enums.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;

@ManagedBean(name = "apiBean")
@SessionScoped
public class APIBean {
	static final Logger LOG = LoggerFactory.getLogger(APIBean.class);
	public final static String FEED_URL = "https://query1.finance.yahoo.com/v8/finance/chart/BTC-EUR?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";

	private String strUrl;
	private APIWrapper<Yahoo, YahooRow> apiWrapper;
	private Boolean bDoUpdate=true;
	private int refreshInterval=10;

	public APIBean() {
		super();

		strUrl = FEED_URL;
		apiWrapper = new APIWrapper<Yahoo, YahooRow>(Yahoo.class, YahooRow.class, strUrl);
		refresh();
	}

	public APIWrapper<Yahoo, YahooRow> getApiWrapper() {
		return apiWrapper;
	}

	public void refresh() {
		String res = CurlProvider.getInstance().getURI(strUrl, HttpMethod.GET, null);
		LOG.debug("res="+res);
		System.out.println("res="+res);
		System.out.println("refreshInterval="+refreshInterval);
		if (res != null && !res.equals("")) {
			apiWrapper.setRawString(res);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			try {
				POJOInterface obj = mapper.readValue(res, apiWrapper.getPojoClass());
				// System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				apiWrapper.setRawString(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				apiWrapper.setPojo(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			apiWrapper.setPojo(null);
		}
	}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	public Boolean getbDoUpdate() {
		return bDoUpdate;
	}

	public void setbDoUpdate(Boolean bDoUpdate) {
		this.bDoUpdate = bDoUpdate;
	}

	public int getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(int refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
	
	public void onSlideEnd_RefreshInterval(SlideEndEvent event) {
		this.refreshInterval =event.getValue();
    } 

}
