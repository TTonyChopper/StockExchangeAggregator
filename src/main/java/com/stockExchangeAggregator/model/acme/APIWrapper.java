package com.stockExchangeAggregator.model.acme;

public class APIWrapper {

	private Class<?> pojoClass;
	String rawString=null;
	Object pojo;
	private String url;
		
	public APIWrapper(Class<?> pojoClass, String url) {
		super();
		this.pojoClass = pojoClass;
		this.url = url;
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
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIWrapper [pojoClass=");
		builder.append(pojoClass);
		builder.append(", pojo=");
		builder.append(pojo);
		builder.append(", rawString=");
		builder.append(rawString);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
