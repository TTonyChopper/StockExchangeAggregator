package com.stockExchangeAggregator.model.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.stockExchangeAggregator.model.acme.POJORowInterface;

public class AlphaRow implements POJORowInterface
{
	private String timestamp;
	private Double low;
	private Double high;
	private Double open;
	private Double close;
	private Long volume;

	private final static List<String> headers = Arrays.asList("Timestamp", "Low", "High", "Open", "Close", "Volume");

	public AlphaRow()
	{
		super();
	}

	public AlphaRow(String timestamp, Double low, Double close, Double open, Long volume, Double high)
	{
		super();
		this.timestamp = timestamp;
		this.low = low;
		this.high = high;
		this.open = open;
		this.close = close;
		this.volume = volume;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AlphaRow [timestamp=");
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

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

	public Double getLow()
	{
		return low;
	}

	public void setLow(Double low)
	{
		this.low = low;
	}

	public Double getHigh()
	{
		return high;
	}

	public void setHigh(Double high)
	{
		this.high = high;
	}

	public Double getOpen()
	{
		return open;
	}

	public void setOpen(Double open)
	{
		this.open = open;
	}

	public Double getClose()
	{
		return close;
	}

	public void setClose(Double close)
	{
		this.close = close;
	}

	public Long getVolume()
	{
		return volume;
	}

	public void setVolume(Long volume)
	{
		this.volume = volume;
	}

	public static List<String> getHeaders()
	{
		return headers;
	}

	public Object get(String s)
	{
		Object ret = null;
		Method method;
		try
		{
			method = this.getClass().getMethod("get" + s);
			ret = method.invoke(this);
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}

		return ret;
	}
}
