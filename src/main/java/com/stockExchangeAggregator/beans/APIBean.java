package com.stockExchangeAggregator.beans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import com.stockExchangeAggregator.model.yahoo.Indicators;
import com.stockExchangeAggregator.model.yahoo.Quote;
import com.stockExchangeAggregator.model.yahoo.Result;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;

@ManagedBean(name="apiBean")
public class APIBean {
	
	private APIWrapper apiWrapper;	
	private LineChartModel lineChart;
	
	public APIBean() {
		super();
		String strUrl="https://query1.finance.yahoo.com/v8/finance/chart/BTC-USD?region=US&lang=en-US&range=6mo&includePrePost=false&interval=1d&corsDomain=finance.yahoo.com&.tsrc=finance";
		apiWrapper=new APIWrapper(Yahoo.class,strUrl);
		refresh();
	}

	public void refresh() {
		String res=null;
		res=CurlProvider.getInstance().getURI(apiWrapper.getUrl(), HttpMethod.GET, null);
		
		apiWrapper.setRawString(res);
		if(res!=null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			Object obj=apiWrapper.getPojoClass();
			try {
				obj=mapper.readValue(res, Yahoo.class);
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
				apiWrapper.setPojo(obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
		}else {
			apiWrapper.setPojo(null);
		}
		
	}

	public APIWrapper getApiWrapper() {
		return apiWrapper;
	}

	public LineChartModel getLineChart() {
		
		LineChartModel lcm=new LineChartModel();
		Object obj=apiWrapper.getPojo();
		
		if(obj!=null) {
			Class<?> clazz=null;
			try {
				clazz = Class.forName(apiWrapper.getPojoClass().getName());
				obj = clazz.cast(obj);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(obj instanceof Yahoo) {
				Result r=((Yahoo) obj).getChart().getResult().get(0);
				List<Long> listTs=r.getTimestamp();
				Quote q=r.getIndicators().getQuote().get(0);
				List<Long> listOpen=q.getOpen();
				
				LineChartSeries lineOpen = new LineChartSeries();
				lineOpen.setFill(true);
				lineOpen.setLabel("Open");
				lineOpen.set("2004", 120);
		        
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				IntStream.range(0, listTs.size())
			            .boxed()
			            .forEachOrdered(i->{
			            	
			            	String a=sdf.format(new Date(listTs.get(i)*1000));
			            	Long b=listOpen.get(i);
			            	//System.out.println(a +"-"+b);
			            	
			            	lineOpen.set(a, b);
			            });
			            //.collect(Collectors.toMap(i -> listTs.get(i), i -> listOpen.get(i)));
				
				lcm.addSeries(lineOpen);
				lcm.setZoom(true);
				DateAxis axisX = new DateAxis("Dates");
				axisX.setTickAngle(-50);
				axisX.setMax(sdf.format(new Date(listTs.get(listTs.size()-1)*1000)));
				axisX.setTickFormat("%b %#d, %y %H:%#M:%S");
		        //axis.getTickInterval();
				axisX.setMin(sdf.format(new Date(listTs.get(0)*1000)));
		        //axis.setTickInterval("1");
		        //axis.setTickFormat("%M"); 
		        
		        lcm.getAxes().put(AxisType.X, axisX);
		        
		        
		        Axis axisY= new LinearAxis("");
		        axisY.setMin(Collections.min(listOpen));
		        axisY.setMax(Collections.max(listOpen));
		        
		        lcm.getAxes().put(AxisType.Y, axisY);
				//lineOpen.set
				
			}
			
		}
		
		
		
		
		return lcm;
	}
	
	
	
	
	
}
