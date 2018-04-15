package com.stockExchangeAggregator.providers.chart;

import org.primefaces.model.chart.LineChartModel;

import com.stockExchangeAggregator.model.acme.POJOInterface;

public interface ChartProviderInterface<T extends POJOInterface>
{
	void drawLineChart(POJOInterface model, LineChartModel lcm);

	LineChartData getLineChartData(POJOInterface model);
}
