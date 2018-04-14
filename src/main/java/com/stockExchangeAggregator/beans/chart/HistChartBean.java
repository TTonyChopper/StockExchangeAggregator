package com.stockExchangeAggregator.beans.chart;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.LineChartModel;

import com.stockExchangeAggregator.beans.APIBean;
import com.stockExchangeAggregator.model.acme.APIWrapper;
import com.stockExchangeAggregator.model.acme.POJOInterface;

@ManagedBean(name = "histChartBean")
@SessionScoped
@SuppressWarnings("rawtypes")
public class HistChartBean {
	private APIBean apiBean;

	public HistChartBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		apiBean = (APIBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{apiBean}", APIBean.class)
				.getValue(facesContext.getELContext());
	}

	@SuppressWarnings("unchecked")
	public LineChartModel getLineChart() {
		LineChartModel lcm = new LineChartModel();

		APIWrapper apiWrapper = apiBean.getApiWrapper();
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
