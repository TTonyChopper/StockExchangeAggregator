package com.stockExchangeAggregator.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;

import com.stockExchangeAggregator.providers.query.parameter.alpha.Function;

@ManagedBean(name = "queryBean")
@SessionScoped
public class QueryBean
{
	private final APIBean apiBean;
	
	private int functionIndex;
	private int functionsSizeIndexed = Function.functions.size() - 1;
	private String function = Function.TIME_SERIES_DAILY.getParam();
	
	public QueryBean()
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

	public void onSlideEnd(SlideEndEvent event) {
		functionIndex = event.getValue();
		Function currentFunction = Function.functions.get(functionIndex);
        function = Function.functions.get(functionIndex).getParam();
        
        apiBean.updateParam(currentFunction);
        //Alpha Vantage cannot handle so many request for the moment
        //apiBean.refresh();
    } 
}
