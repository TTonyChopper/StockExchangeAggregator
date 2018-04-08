package com.stockExchangeAggregator.model.acme;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.LineChartModel;

import com.stockExchangeAggregator.providers.ProviderFactory;

public class APIWrapper<T extends POJOInterface, U extends POJORowInterface> {
	private ProviderFactory providerFactory;
	private Class<T> pojoClass;
	private Class<U> pojoRow;
	private String rawString;
	private POJOInterface pojo;
	private String url;
	private List<? extends POJORowInterface> rows = new ArrayList<>();
	private List<String> headers = new ArrayList<String>();

	public APIWrapper(Class<T> pojoClass, Class<U> pojoRow, String url) {
		super();
		
		this.providerFactory = new ProviderFactory();
		this.pojoClass = pojoClass;
		this.pojoRow = pojoRow;
		this.url = url;

		Method method;
		try {
			method = pojoRow.getMethod("getHeaders");
			this.headers = (List<String>) method.invoke(null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (headers == null)
			headers = new ArrayList<String>();
	}

	public void drawLineChart(POJOInterface obj, LineChartModel lcm) {
		providerFactory.getChartProvider(pojoClass).drawLineChart(obj, lcm);
	}

	public void provideRows(POJOInterface obj, LineChartModel lcm) {
		rows = providerFactory.getRowProvider(pojoClass).provideRows(obj, lcm);
	}

	public Class<T> getPojoClass() {
		return pojoClass;
	}

	public String getUrl() {
		return url;
	}

	public POJOInterface getPojo() {
		return pojo;
	}

	public void setPojo(POJOInterface pojo) {
		this.pojo = pojo;
	}

	public String getRawString() {
		return rawString;
	}

	public void setRawString(String rawString) {
		this.rawString = rawString;
	}

	public List<? extends POJORowInterface> getRows() {
		return rows;
	}

	public void setRows(List<? extends POJORowInterface> rows) {
		this.rows = rows;
	}

	public List<String> getHeaders() {
		return headers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIWrapper [pojoClass=");
		builder.append(pojoClass);
		builder.append(", rawString=");
		builder.append(rawString);
		builder.append(", pojo=");
		builder.append(pojo);
		builder.append(", url=");
		builder.append(url);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}

	public Class<U> getPojoRow() {
		return pojoRow;
	}

	public void setPojoRow(Class<U> pojoRow) {
		this.pojoRow = pojoRow;
	}

}
