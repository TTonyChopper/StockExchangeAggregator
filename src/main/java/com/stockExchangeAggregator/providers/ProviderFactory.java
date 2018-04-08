package com.stockExchangeAggregator.providers;

import java.util.HashMap;
import java.util.Map;

import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.acme.POJORowInterface;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.chart.ChartProviderInterface;
import com.stockExchangeAggregator.providers.chart.yahoo.YahooChartProvider;
import com.stockExchangeAggregator.providers.row.RowProviderInterface;
import com.stockExchangeAggregator.providers.row.yahoo.YahooRowProvider;

public class ProviderFactory {
	private static Map<Class<? extends POJOInterface>, ChartProviderInterface<? extends POJOInterface>> chartProviders = new HashMap<>();
	private static Map<Class<? extends POJOInterface>, RowProviderInterface<? extends POJORowInterface>> rowProviders = new HashMap<>();
	
	static
	{
		chartProviders.put(Yahoo.class, new YahooChartProvider());
		rowProviders.put(Yahoo.class, new YahooRowProvider());
	}
	
	public ProviderFactory() {
		super();
	}

	public ChartProviderInterface<? extends POJOInterface> getChartProvider(Class<? extends POJOInterface> clazz)
	{
		return chartProviders.get(clazz);
	}
	
	public RowProviderInterface<? extends POJORowInterface> getRowProvider(Class<? extends POJOInterface> clazz)
	{
		return rowProviders.get(clazz);
	}
}
