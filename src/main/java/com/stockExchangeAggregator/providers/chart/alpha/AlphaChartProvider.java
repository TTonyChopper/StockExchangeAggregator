package com.stockExchangeAggregator.providers.chart.alpha;

import java.util.Collections;
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
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.providers.chart.ChartProviderInterface;
import com.stockExchangeAggregator.providers.chart.LineChartData;

public class AlphaChartProvider implements ChartProviderInterface<Alpha> {
	@Override
	public void drawLineChart(POJOInterface model, LineChartModel lcm) {
		Alpha alpha = (Alpha) model;
		
		List<String> listTs = alpha.getTimeSeries().stream().map(serie -> serie.getName())
				.collect(Collectors.toList());
		List<Double> listOpen = alpha.getTimeSeries().stream().map(serie -> serie.get1Open())
				.map(Double::valueOf).collect(Collectors.toList());
		List<String> listClose = alpha.getTimeSeries().stream().map(serie -> serie.get4Close())
				.collect(Collectors.toList());

		LineChartSeries myLine = new LineChartSeries();
		myLine.setFill(true);
		myLine.setLabel("Close");

		IntStream.range(0, listTs.size()).boxed().forEachOrdered(i -> {
			Double val = Double.parseDouble(listClose.get(i));
			myLine.set(listTs.get(i), val);
		});

		lcm.addSeries(myLine);
		lcm.setZoom(true);
		DateAxis axisX = new DateAxis("Dates");
		axisX.setTickAngle(-50);
		axisX.setMax(listTs.get(listTs.size() -1 ));
		axisX.setTickFormat("%b %#d, %y %H:%#M:%S");
		// axis.getTickInterval();
		axisX.setMin(listTs.get(0));
		// axis.setTickInterval("1");
		// axis.setTickFormat("%M");

		lcm.getAxes().put(AxisType.X, axisX);

		try {
			Axis axisY = new LinearAxis("");
			axisY.setMin(Collections.min(listOpen));
			axisY.setMax(Collections.max(listOpen));

			lcm.getAxes().put(AxisType.Y, axisY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LineChartData getLineChartData(POJOInterface model) {
		LineChartData lineChartData = new LineChartData();

		Alpha alpha = (Alpha) model;

		lineChartData.ySetLabel = alpha.getMetaData().get2Symbol();

		lineChartData.ySet = alpha.getTimeSeries().stream().map(serie -> serie.get4Close())
				.collect(Collectors.toList());

		lineChartData.xLabels = alpha.getTimeSeries().stream().map(serie -> serie.getName())
				.collect(Collectors.toList());

		lineChartData.xSet = Collections.emptyList();

		return lineChartData;
	}
}
