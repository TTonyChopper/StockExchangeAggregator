package com.stockExchangeAggregator.providers.serializer.yahoo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.providers.serializer.DeserializerInterface;

public class YahooDeserializer implements DeserializerInterface
{

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	@Override
	public POJOInterface deserializeJSON(String json, Class pojoClass)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return (POJOInterface) mapper.readValue(json, pojoClass);
	}

}
