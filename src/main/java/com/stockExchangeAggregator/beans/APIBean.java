package com.stockExchangeAggregator.beans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

import com.enums.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.yahoo.Quote;
import com.stockExchangeAggregator.model.yahoo.Result;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;

@ManagedBean(name = "apiBean")
public class APIBean {

	private APIWrapper apiWrapper;
	private LineChartModel lineChart;
	private String strUrl;

	public final static String FEED_URL = "https://query1.finance.yahoo.com/v8/finance/chart/BTC-EUR?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";

	public APIBean() {
		super();

		strUrl = FEED_URL;
		apiWrapper = new APIWrapper(Yahoo.class, YahooRow.class, strUrl);
		refresh();
	}

	public void refresh() {
		String res = CurlProvider.getInstance().getURI(strUrl, HttpMethod.GET, null);

		if (res != null && !res.equals("")) {
			apiWrapper.setRawString(res);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Object obj = apiWrapper.getPojoClass();
			try {
				obj = mapper.readValue(res, Yahoo.class);
				// System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				apiWrapper.setRawString(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				apiWrapper.setPojo(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			apiWrapper.setPojo(null);
		}
	}

	public APIWrapper getApiWrapper() {
		return apiWrapper;
	}

	public LineChartModel getLineChart() {
		LineChartModel lcm = new LineChartModel();
		Object obj = apiWrapper.getPojo();

		if (obj != null) {
			Class<?> clazz = null;
			try {
				clazz = Class.forName(apiWrapper.getPojoClass().getName());
				obj = clazz.cast(obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (obj instanceof Yahoo) {
				Result r = ((Yahoo) obj).getChart().getResult().get(0);
				List<Long> listTs = r.getTimestamp();
				Quote q = r.getIndicators().getQuote().get(0);
				List<Long> listOpen = q.getOpen();
				List<Double> listClose = q.getClose();
				List<Long> listLow = q.getLow();
				List<Long> listHigh = q.getHigh();
				List<Long> listVolume = q.getVolume();

				// TODO Rework Null case
				if (listOpen.size() == 0)
					return lcm;

				LineChartSeries myLine = new LineChartSeries();
				myLine.setFill(true);
				myLine.setLabel("Close");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				List<Object> rows = new ArrayList<Object>();
				IntStream.range(0, listTs.size()).boxed().forEachOrdered(i -> {

					String vTs = sdf.format(new Date(listTs.get(i) * 1000));
					Double val = listClose.get(i);
					myLine.set(vTs, val);

					YahooRow yRow = new YahooRow();
					yRow.setTimestamp(listTs.get(i) * 1000);
					yRow.setLow(listLow.get(i));
					yRow.setHigh(listHigh.get(i));
					yRow.setOpen(listOpen.get(i));
					yRow.setClose(listClose.get(i));
					yRow.setVolume(listVolume.get(i));
					rows.add(yRow);
				});
				apiWrapper.setRows(rows);

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

				try {
					Axis axisY = new LinearAxis("");
					axisY.setMin(Collections.min(listOpen));
					axisY.setMax(Collections.max(listOpen));

					lcm.getAxes().put(AxisType.Y, axisY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return lcm;
	}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}
}
