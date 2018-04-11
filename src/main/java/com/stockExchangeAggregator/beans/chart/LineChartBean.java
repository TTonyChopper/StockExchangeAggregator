package com.stockExchangeAggregator.beans.chart;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;

import com.google.gson.Gson;
import com.stockExchangeAggregator.beans.APIBean;
import com.stockExchangeAggregator.providers.chart.LineChartData;

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
	private LineChartData getLineChartData() {
		return apiBean.getApiWrapper().getLineChartData(apiBean.getApiWrapper().getPojo());
	}
	
	public void retrieveLineChartData() {
		LineChartData lineChartData = getLineChartData();
	    Ajax ajax = PrimeFaces.current().ajax();
	    ajax.addCallbackParam("ysetlabel", new Gson().toJson(lineChartData.ySetLabel));
	    ajax.addCallbackParam("xlabels", new Gson().toJson(lineChartData.xLabels));
	    ajax.addCallbackParam("yset", new Gson().toJson(lineChartData.ySet));
	    ajax.addCallbackParam("xset", new Gson().toJson(lineChartData.xSet));
	}
}
