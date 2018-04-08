package com.stockExchangeAggregator.beans;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.LineChartModel;

import com.enums.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;

@ManagedBean(name = "apiBean")
public class APIBean {

	private APIWrapper<Yahoo, YahooRow> apiWrapper;
	private LineChartModel lineChart;
	private String strUrl;

	public final static String FEED_URL = "https://query1.finance.yahoo.com/v8/finance/chart/BTC-EUR?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";

	public APIBean() {
		super();

		strUrl = FEED_URL;
		apiWrapper = new APIWrapper<Yahoo, YahooRow>(Yahoo.class, YahooRow.class, FEED_URL);
		refresh();
	}

	public void refresh() {
		String res = CurlProvider.getInstance().getURI(strUrl, HttpMethod.GET, null);

		if (res != null && !res.equals("")) {
			apiWrapper.setRawString(res);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			try {
				POJOInterface obj = mapper.readValue(res, apiWrapper.getPojoClass());
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

	public APIWrapper<Yahoo, YahooRow> getApiWrapper() {
		return apiWrapper;
	}

	public LineChartModel getLineChart() {
		LineChartModel lcm = new LineChartModel();
		POJOInterface obj = apiWrapper.getPojo();

		if (obj != null) {
			Class<? extends POJOInterface> clazz = null;
			try {
				clazz = (Class<POJOInterface>) Class.forName(apiWrapper.getPojoClass().getName());
				obj = clazz.cast(obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			apiWrapper.drawLineChart(obj, lcm);
			apiWrapper.provideRows(obj, lcm);
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
