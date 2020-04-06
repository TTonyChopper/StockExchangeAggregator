package com.stockExchangeAggregator.beans.alpha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;

import com.stockExchangeAggregator.beans.APIBean;
import com.stockExchangeAggregator.providers.query.parameter.alpha.Function;

@ManagedBean(name = "queryFunctionBean")
@SessionScoped
public class QueryFunctionBean
{
	private final APIBean apiBean;
	
	private Function defaultFunction = Function.TIME_SERIES_DAILY;
	private int functionIndex = Function.functions.indexOf(defaultFunction);
	private int functionsSizeIndexed = Function.functions.size() - 1;
	private String function = defaultFunction.getParam();
	
	private boolean onSlideEnd;
	
	public QueryFunctionBean()
	{
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		apiBean = (APIBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{apiBean}", APIBean.class)
				.getValue(facesContext.getELContext());
	}

	public int getFunctionIndex()
	{
		return functionIndex;
	}

	public void setFunctionIndex(int functionIndex)
	{
		this.functionIndex = functionIndex;
	}

	public int getFunctionsSizeIndexed()
	{
		return functionsSizeIndexed;
	}

	public void setFunctionsSizeIndexed(int functionsSizeIndexed)
	{
		this.functionsSizeIndexed = functionsSizeIndexed;
	}

	public String getFunction()
	{
		return function;
	}

	public void setFunction(String function)
	{
		this.function = function;
	}
	
	public boolean getOnSlideEnd()
	{
		return onSlideEnd;
	}
	
	public void setOnSlideEnd(boolean onSlideEnd)
	{
		this.onSlideEnd = onSlideEnd;
	}

	public void onSlideEnd(SlideEndEvent event) {
		functionIndex = event.getValue();
		Function currentFunction = Function.functions.get(functionIndex);
        function = Function.functions.get(functionIndex).getParam();
        
        apiBean.updateParam(currentFunction);
        //Alpha Vantage cannot handle so many request for the moment
        //apiBean.refresh();
    }
}
