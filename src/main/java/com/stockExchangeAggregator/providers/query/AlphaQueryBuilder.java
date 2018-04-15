package com.stockExchangeAggregator.providers.query;

import java.util.HashSet;
import java.util.Set;

import com.stockExchangeAggregator.providers.query.parameter.QueryParameter;

public class AlphaQueryBuilder
{
	public Set<QueryParameter> params = new HashSet<>();

	public void addParam(QueryParameter param)
	{
		params.add(param);
	}

	public Query build(String base)
	{
		return new Query(base, params);
	}

	class Query
	{
		public String base;
		public Set<QueryParameter> params = new HashSet<>();

		public Query(String base, Set<QueryParameter> params)
		{
			super();
			this.base = base;
			this.params = params;
		}

		public String toString()
		{
			StringBuilder queryString = new StringBuilder(base);
			params.stream().forEach(p -> queryString.append(p.getParam()));
			return queryString.toString();
		}
	}
}
