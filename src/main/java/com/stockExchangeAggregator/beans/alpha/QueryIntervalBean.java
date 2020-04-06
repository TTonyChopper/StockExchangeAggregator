package com.stockExchangeAggregator.beans.alpha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;

import com.stockExchangeAggregator.beans.APIBean;
import com.stockExchangeAggregator.providers.query.parameter.alpha.Interval;

@ManagedBean(name = "queryIntervalBean")
@SessionScoped
public class QueryIntervalBean
{
	private final APIBean apiBean;
	
	private Interval defaultInterval = Interval.MIN_60;
	private int intervalIndex = Interval.intervals.indexOf(defaultInterval);
	private int intervalsSizeIndexed = Interval.intervals.size() - 1;
	private String interval = defaultInterval.getParam();
	
	public QueryIntervalBean()
	{
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		apiBean = (APIBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{apiBean}", APIBean.class)
				.getValue(facesContext.getELContext());
	}

	public int getIntervalIndex()
	{
		return intervalIndex;
	}

	public void setIntervalIndex(int intervalIndex)
	{
		this.intervalIndex = intervalIndex;
	}

	public int getIntervalsSizeIndexed()
	{
		return intervalsSizeIndexed;
	}

	public void setIntervalsSizeIndexed(int intervalsSizeIndexed)
	{
		this.intervalsSizeIndexed = intervalsSizeIndexed;
	}

	public String getInterval()
	{
		return interval;
	}

	public void setInterval(String interval)
	{
		this.interval = interval;
	}

	public void onSlideEnd(SlideEndEvent event) {
		intervalIndex = event.getValue();
		Interval currentInterval = Interval.intervals.get(intervalIndex);
		interval = Interval.intervals.get(intervalIndex).getParam();
        
        apiBean.updateParam(currentInterval);
        //Alpha Vantage cannot handle so many request for the moment
        //apiBean.refresh();
    }
	
	
}
