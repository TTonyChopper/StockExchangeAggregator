package com.stockExchangeAggregator.beans.alpha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.stockExchangeAggregator.beans.APIBean;
import com.stockExchangeAggregator.providers.query.parameter.alpha.OutputSize;

@ManagedBean(name = "queryParamBean")
@SessionScoped
public class QueryParamBean
{
	private final APIBean apiBean;
	
	private final static String COMPACT_MSG = "Compact: ";
	private final static String FULL_MSG = "Full: ";
	
	private boolean outputSizeFull;
	private String msg = COMPACT_MSG;
	
	private boolean onCheckBox;
	
	public QueryParamBean()
	{
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		apiBean = (APIBean) facesContext.getApplication().getExpressionFactory()
				.createValueExpression(facesContext.getELContext(), "#{apiBean}", APIBean.class)
				.getValue(facesContext.getELContext());
	}

	public boolean getOutputSizeFull()
	{
		return outputSizeFull;
	}

	public void setOutputSizeFull(boolean outputSizeFull)
	{
		this.outputSizeFull = outputSizeFull;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public boolean getOnCheckBox()
	{
		return onCheckBox;
	}
	
	public void setOnCheckBox(boolean onCheckBox)
	{
		this.onCheckBox = onCheckBox;
	}
	
	public void onCheckBox()
	{
		outputSizeFull = !outputSizeFull;
		OutputSize currentOutputsize = outputSizeFull ? OutputSize.FULL : OutputSize.COMPACT;
        setMsg(outputSizeFull ? FULL_MSG : COMPACT_MSG);
        
        apiBean.updateParam(currentOutputsize);
        //Alpha Vantage cannot handle so many request for the moment
        //apiBean.refresh();
	}
}
