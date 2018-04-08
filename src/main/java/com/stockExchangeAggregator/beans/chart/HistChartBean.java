package com.stockExchangeAggregator.beans.chart;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.LineChartModel;

import com.stockExchangeAggregator.beans.YahooAPIWrapperBean;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.wrapper.YahooRow;
import com.stockExchangeAggregator.model.yahoo.Yahoo;

@ManagedBean(name = "histChartBean")
public class HistChartBean {
	private YahooAPIWrapperBean apiWrapperBean;

	public HistChartBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		apiWrapperBean = (YahooAPIWrapperBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{yahooAPIWrapperBean}", YahooAPIWrapperBean.class)
				.getValue(facesContext.getELContext());
	}

	@SuppressWarnings("unchecked")
	public LineChartModel getLineChart() {
		LineChartModel lcm = new LineChartModel();
		APIWrapper<Yahoo, YahooRow> apiWrapper = apiWrapperBean.getApiWrapper();
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

}
