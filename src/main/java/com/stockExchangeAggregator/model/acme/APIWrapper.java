package com.stockExchangeAggregator.model.acme;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIWrapper {

	private Class<?> pojoClass;
	private Class<?> pojoRow;
	private String rawString=null;
	private Object pojo;
	private String url;
	private List<Object> rows=new ArrayList<Object>();
	private List<String> headers=new ArrayList<String>();
	

	public APIWrapper(Class<?> pojoClass, String url) {
		super();
		this.pojoClass = pojoClass;
		this.url = url;
	}
	
	public APIWrapper(Class<?> pojoClass, Class<?> pojoRow, String url) {
		super();
		this.pojoClass = pojoClass;
		this.pojoRow = pojoRow;
		this.url = url;
		
		Method method;
		try {
			method = pojoRow.getMethod("getHeaders");
			this.headers=(List<String>) method.invoke(null);
		} catch (NoSuchMethodException | SecurityException e) {
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
		if(headers==null)headers=new ArrayList<String>();
		
	}

	public Class<?> getPojoClass() {
		return pojoClass;
	}

	public String getUrl() {
		return url;
	}

	public Object getPojo() {
		return pojo;
	}
	public void setPojo(Object pojo) {
		this.pojo = pojo;
	}
	public String getRawString() {
		return rawString;
	}
	public void setRawString(String rawString) {
		this.rawString = rawString;
	}
	
	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
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

	public Class<?> getPojoRow() {
		return pojoRow;
	}

	public void setPojoRow(Class<?> pojoRow) {
		this.pojoRow = pojoRow;
	}
	
}
