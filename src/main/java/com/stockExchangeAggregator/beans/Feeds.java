package com.stockExchangeAggregator.beans;

public enum Feeds
{
	YAHOO("/query/yahoo"),
	ALPHA("/query/alpha");
	
	private String queryPageName;

	private Feeds(String queryPageName)
	{
		this.queryPageName = queryPageName;
	}
	
	public String getPageName()
	{
		return queryPageName;
	}
}
