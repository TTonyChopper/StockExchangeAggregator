package com.stockExchangeAggregator.beans.chart;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;
import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.stockExchangeAggregator.beans.APIBean;

@ManagedBean(name = "lineChartBean")
@SessionScoped
public class LineChartBean {
	private APIBean apiBean;

	public LineChartBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		apiBean = (APIBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{apiBean}", APIBean.class)
				.getValue(facesContext.getELContext());
	}
	
	//"BTC-EUR";
	private String generateYSetLabel() {
		return "BTC-EURNOOB";
	}
	
	//[ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	private List<String> generateXLabels() {
		return Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
	}
	
	//[ 84.5, 86, 84.8, 87, 83.3, 85.9, 87.4, 80.6, 82.0, 84.6, 88.6, 90.0 ];
	private List<String> generateYSet() {
		return Arrays.asList("84.5", "86", "84.8", "87", "83.3", "85.9", "87.4", "80.6", "82.0", "84.6", "88.6", "90.0");
	}
	
	private List<String> generateXSet() {
		return Collections.emptyList();
	}
	
	public void retrieveLineChartData() {
	    String ySetLabel = generateYSetLabel();
	    List<String> xLabels = generateXLabels();
	    List<String> ySet = generateYSet();
	    List<String> xSet = generateXSet();
	    Ajax ajax = PrimeFaces.current().ajax();
	    ajax.addCallbackParam("ysetlabel", new Gson().toJson(ySetLabel));
	    ajax.addCallbackParam("xlabels", new Gson().toJson(xLabels));
	    ajax.addCallbackParam("yset", new Gson().toJson(ySet));
	    ajax.addCallbackParam("xset", new Gson().toJson(xSet));
	}
}
