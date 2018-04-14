package com.stockExchangeAggregator.providers.row.alpha;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.LineChartModel;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.wrapper.AlphaRow;
import com.stockExchangeAggregator.providers.row.RowProviderInterface;

public class AlphaRowProvider implements RowProviderInterface<AlphaRow> {
	public List<AlphaRow> provideRows(POJOInterface model, LineChartModel lcm) {
		/*
		 * Result r = ((Yahoo) model).getChart().getResult().get(0); List<Long> listTs =
		 * r.getTimestamp(); Quote q = r.getIndicators().getQuote().get(0); List<Long>
		 * listOpen = q.getOpen(); List<Double> listClose = q.getClose(); List<Long>
		 * listLow = q.getLow(); List<Long> listHigh = q.getHigh(); List<Long>
		 * listVolume = q.getVolume();
		 * 
		 * // TODO Rework Null case if (listOpen.size() == 0) { return
		 * Collections.emptyList(); }
		 * 
		 * List<AlphaRow> rows = new ArrayList<AlphaRow>(); IntStream.range(0,
		 * listTs.size()).boxed().forEachOrdered(i -> { AlphaRow yRow = new AlphaRow();
		 * yRow.setTimestamp(listTs.get(i) * 1000); yRow.setLow(listLow.get(i));
		 * yRow.setHigh(listHigh.get(i)); yRow.setOpen(listOpen.get(i));
		 * yRow.setClose(listClose.get(i)); yRow.setVolume(listVolume.get(i));
		 * rows.add(yRow); }); return rows;
		 */

		return new ArrayList<AlphaRow>();
	}
}
