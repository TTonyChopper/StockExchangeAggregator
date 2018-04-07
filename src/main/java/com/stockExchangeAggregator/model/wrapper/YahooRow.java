package com.stockExchangeAggregator.model.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YahooRow {

	private Long timestamp;
	private float low;
	private double close;
	private float open;
	private float volume;
	private float high;
	private final static List<String> headers=Arrays.asList("Timestamp", "Low", "Close","Open","Volume","High");
	
	public YahooRow() {
		super();
	}

	public YahooRow(Long timestamp, float low, Double close, float open, float volume, float high) {
		super();
		this.timestamp = timestamp;
		this.low = low;
		this.close = close;
		this.open = open;
		this.volume = volume;
		this.high = high;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YahooRow [timestamp=");
		builder.append(timestamp);
		builder.append(", low=");
		builder.append(low);
		builder.append(", close=");
		builder.append(close);
		builder.append(", open=");
		builder.append(open);
		builder.append(", volume=");
		builder.append(volume);
		builder.append(", high=");
		builder.append(high);
		builder.append("]");
		return builder.toString();
	}

	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}	
	public static List<String> getHeaders() {
		return headers;
	}
	
	public Object get(String s) {
		Object ret=null;
		
		Method method;
		try {
			method = this.getClass().getMethod("get"+s);
			ret=method.invoke(this);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

}
