package com.stockExchangeAggregator.providers;

import java.util.HashMap;
import java.util.Map;

import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.acme.POJORowInterface;
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.chart.ChartProviderInterface;
import com.stockExchangeAggregator.providers.chart.alpha.AlphaChartProvider;
import com.stockExchangeAggregator.providers.chart.yahoo.YahooChartProvider;
import com.stockExchangeAggregator.providers.row.RowProviderInterface;
import com.stockExchangeAggregator.providers.row.alpha.AlphaRowProvider;
import com.stockExchangeAggregator.providers.row.yahoo.YahooRowProvider;
import com.stockExchangeAggregator.providers.serializer.DeserializerInterface;
import com.stockExchangeAggregator.providers.serializer.alpha.AlphaDeserializer;
import com.stockExchangeAggregator.providers.serializer.yahoo.YahooDeserializer;

public class ProviderFactory
{
	private static Map<Class<? extends POJOInterface>, DeserializerInterface> deserializers = new HashMap<>();

	private static Map<Class<? extends POJOInterface>, ChartProviderInterface<? extends POJOInterface>> chartProviders = new HashMap<>();
	private static Map<Class<? extends POJOInterface>, RowProviderInterface<? extends POJORowInterface>> rowProviders = new HashMap<>();

	static
	{
		deserializers.put(Yahoo.class, new YahooDeserializer());
		deserializers.put(Alpha.class, new AlphaDeserializer());

		chartProviders.put(Yahoo.class, new YahooChartProvider());
		chartProviders.put(Alpha.class, new AlphaChartProvider());

		rowProviders.put(Yahoo.class, new YahooRowProvider());
		rowProviders.put(Alpha.class, new AlphaRowProvider());
	}

	public ProviderFactory()
	{
		super();
	}

	public DeserializerInterface getDeserializer(Class<? extends POJOInterface> clazz)
	{
		return deserializers.get(clazz);
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
