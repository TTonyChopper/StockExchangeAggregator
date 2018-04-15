package com.stockExchangeAggregator.providers.chart.yahoo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.yahoo.Quote;
import com.stockExchangeAggregator.model.yahoo.Result;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.chart.ChartProviderInterface;
import com.stockExchangeAggregator.providers.chart.LineChartData;

public class YahooChartProvider implements ChartProviderInterface<Yahoo>
{
	@Override
	public void drawLineChart(POJOInterface model, LineChartModel lcm)
	{
		Result r = ((Yahoo) model).getChart().getResult().get(0);
		List<Long> listTs = r.getTimestamp();
		Quote q = r.getIndicators().getQuote().get(0);
		List<Long> listOpen = q.getOpen();
		List<Double> listClose = q.getClose();

		LineChartSeries myLine = new LineChartSeries();
		myLine.setFill(true);
		myLine.setLabel("Close");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		IntStream.range(0, listTs.size()).boxed().forEachOrdered(i ->
		{
			String vTs = sdf.format(new Date(listTs.get(i) * 1000));
			Double val = listClose.get(i);
			myLine.set(vTs, val);
		});

		lcm.addSeries(myLine);
		lcm.setZoom(true);
		DateAxis axisX = new DateAxis("Dates");
		axisX.setTickAngle(-50);
		axisX.setMax(sdf.format(new Date(listTs.get(listTs.size() - 1) * 1000)));
		axisX.setTickFormat("%b %#d, %y %H:%#M:%S");
		// axis.getTickInterval();
		axisX.setMin(sdf.format(new Date(listTs.get(0) * 1000)));
		// axis.setTickInterval("1");
		// axis.setTickFormat("%M");

		lcm.getAxes().put(AxisType.X, axisX);

		try
		{
			Axis axisY = new LinearAxis("");
			axisY.setMin(Collections.min(listOpen));
			axisY.setMax(Collections.max(listOpen));

			lcm.getAxes().put(AxisType.Y, axisY);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public LineChartData getLineChartData(POJOInterface model)
	{
		LineChartData lineChartData = new LineChartData();

		List<Result> myList = new ArrayList<Result>();
		try
		{
			myList = ((Yahoo) model).getChart().getResult();
		} catch (Exception e)
		{
			return lineChartData;
		}

		Result r = myList.get(0);

		lineChartData.ySetLabel = r.getMeta().getSymbol();

		List<Long> listTs = r.getTimestamp();
		lineChartData.xLabels = listTs.stream().map(String::valueOf).collect(Collectors.toList());

		Quote q = r.getIndicators().getQuote().get(0);
		List<Double> listClose = q.getClose();
		lineChartData.ySet = listClose.stream().map(String::valueOf).collect(Collectors.toList());

		lineChartData.xSet = Collections.emptyList();

		return lineChartData;
	}
}
