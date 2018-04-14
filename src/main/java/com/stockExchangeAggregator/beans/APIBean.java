package com.stockExchangeAggregator.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.http.client.HttpResponseException;
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
import com.stockExchangeAggregator.providers.FeedManager;

@ManagedBean(name = "apiBean")
@SessionScoped
public class APIBean {
	static final Logger LOG = LoggerFactory.getLogger(APIBean.class);
	
	private FeedManager feedMgr = new FeedManager();
	
	private APIWrapper currentApiWrapper;
	
	private Boolean bDoUpdate=false;
	private int refreshInterval=30;

	public APIBean() {
		super();
		refresh();
	}

	public APIWrapper getApiWrapper() {
		return currentApiWrapper = feedMgr.getAlphaApiWrapper();
	}

	public void refresh() {
		getApiWrapper();
		String res=null;
		try {
			res = CurlProvider.getInstance().getURI(currentApiWrapper.getUrl(), HttpMethod.GET, null);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getMessage()) );
		}
		//LOG.debug("res="+res);
		//System.out.println("res="+res);
		//System.out.println("refreshInterval="+refreshInterval);
		if (res != null && !res.equals("")) {
			currentApiWrapper.setRawString(res);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			try {
				POJOInterface obj = (POJOInterface) mapper.readValue(res, currentApiWrapper.getPojoClass());
				currentApiWrapper.setRawString(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				currentApiWrapper.setPojo(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			currentApiWrapper.setPojo(null);
		}
	}

	public String getStrUrl() {
		return currentApiWrapper.getUrl();
	}

	public void setStrUrl(String strUrl) {
		this.currentApiWrapper.setUrl(strUrl);
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
