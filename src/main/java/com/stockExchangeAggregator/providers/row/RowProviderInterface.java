package com.stockExchangeAggregator.providers.row;

import java.util.List;

import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.acme.POJORowInterface;

public interface RowProviderInterface<T extends POJORowInterface>
{
	List<T> provideRows(POJOInterface model);
}
