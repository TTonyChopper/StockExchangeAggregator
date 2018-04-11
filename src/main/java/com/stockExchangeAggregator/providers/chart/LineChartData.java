package com.stockExchangeAggregator.providers.chart;

import java.util.List;

public class LineChartData {
	public String ySetLabel;
	//[ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	public List<String> xLabels;
	//[ 84.5, 86, 84.8, 87, 83.3, 85.9, 87.4, 80.6, 82.0, 84.6, 88.6, 90.0 ];
	public List<String> ySet;
	public List<String> xSet;
}