package com.stockExchangeAggregator.providers.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stockExchangeAggregator.model.acme.POJOInterface;

public interface DeserializerInterface {
	POJOInterface deserializeJSON(String json, @SuppressWarnings("rawtypes") Class pojoClass)
			throws JsonParseException, JsonMappingException, IOException;
}
