package com.stockExchangeAggregator.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.providers.CurlProvider;
import com.stockExchangeAggregator.providers.CurlProvider.HttpMethod;
import com.stockExchangeAggregator.providers.FeedManager;
import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

@ManagedBean(name = "apiBean")
@SessionScoped
@SuppressWarnings("rawtypes")
public class APIBean
{
	static final Logger LOG = LoggerFactory.getLogger(APIBean.class);

	private FeedManager feedMgr = new FeedManager();

	private APIWrapper currentApiWrapper;

	private Boolean booleanUpdate = false;
	private int refreshInterval = 30;

	public APIBean()
	{
		super();
		refresh();
	}

	public APIWrapper getApiWrapper()
	{
		return currentApiWrapper = feedMgr.getAlphaApiWrapper();
	}
	
	public void updateParam(QueryParameter param)
	{
		currentApiWrapper.setUrl(feedMgr.updateParam(param));
	}
	
	public void refreshIfPoll()
	{
		if (booleanUpdate)
		{
			refresh();
		}
	}

	public void refresh()
	{
		getApiWrapper();
		String response = null;
		try
		{
			response = CurlProvider.getInstance().getURI(currentApiWrapper.getUrl(), HttpMethod.GET, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		
		// LOG.debug("response="+response);
		// System.out.println("response="+response);
		// System.out.println("refreshInterval="+refreshInterval);
		
		if (response != null && !response.equals(""))
		{
			currentApiWrapper.setRawString(response);
			try
			{
				POJOInterface pojo = currentApiWrapper.deserializeJSON(response);
				currentApiWrapper
						.setRawString(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pojo));
				currentApiWrapper.setPojo(pojo);
				currentApiWrapper.provideRows(pojo);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			currentApiWrapper.setPojo(null);
		}
	}

	public String getStrUrl()
	{
		return currentApiWrapper.getUrl();
	}

	public void setStrUrl(String strUrl)
	{
		this.currentApiWrapper.setUrl(strUrl);
	}

	public Boolean getBooleanUpdate()
	{
		return booleanUpdate;
	}

	public void setBooleanUpdate(Boolean booleanUpdate)
	{
		this.booleanUpdate = booleanUpdate;
	}

	public int getRefreshInterval()
	{
		return refreshInterval;
	}

	public void setRefreshInterval(int refreshInterval)
	{
		this.refreshInterval = refreshInterval;
	}

	public void onSlideEnd_RefreshInterval(SlideEndEvent event)
	{
		this.refreshInterval = event.getValue();
	}

}
