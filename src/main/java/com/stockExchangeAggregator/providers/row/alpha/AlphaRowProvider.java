package com.stockExchangeAggregator.providers.row.alpha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.primefaces.model.chart.LineChartModel;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.model.wrapper.AlphaRow;
import com.stockExchangeAggregator.providers.row.RowProviderInterface;

public class AlphaRowProvider implements RowProviderInterface<AlphaRow>
{
	public List<AlphaRow> provideRows(POJOInterface model, LineChartModel lcm)
	{

		Alpha alpha = (Alpha) model;

		List<String> listTs = alpha.getTimeSeries().stream().map(serie ->
		{
			String name = serie.getName();
			if (name.length() > 11)
			{
				return name.substring(11);
			}
			return name;
		}).collect(Collectors.toList());
		List<Double> listOpen = alpha.getTimeSeries().stream().map(serie -> serie.get1Open()).map(Double::valueOf)
				.collect(Collectors.toList());
		List<Double> listClose = alpha.getTimeSeries().stream().map(serie -> serie.get4Close()).map(Double::valueOf)
				.collect(Collectors.toList());
		List<Double> listLow = alpha.getTimeSeries().stream().map(serie -> serie.get3Low()).map(Double::valueOf)
				.collect(Collectors.toList());
		List<Double> listHigh = alpha.getTimeSeries().stream().map(serie -> serie.get2High()).map(Double::valueOf)
				.collect(Collectors.toList());
		List<Long> listVolume = alpha.getTimeSeries().stream().map(serie -> serie.get5Volume()).map(Long::valueOf)
				.collect(Collectors.toList());

		// TODO Rework Null case
		if (listOpen.size() == 0)
		{
			return Collections.emptyList();
		}

		List<AlphaRow> rows = new ArrayList<AlphaRow>();
		IntStream.range(0, listTs.size()).boxed().forEachOrdered(i ->
		{
			AlphaRow yRow = new AlphaRow();
			yRow.setTimestamp(listTs.get(i));
			yRow.setLow(listLow.get(i));
			yRow.setHigh(listHigh.get(i));
			yRow.setOpen(listOpen.get(i));
			yRow.setClose(listClose.get(i));
			yRow.setVolume(listVolume.get(i));
			rows.add(yRow);
		});
		return rows;
	}
}
