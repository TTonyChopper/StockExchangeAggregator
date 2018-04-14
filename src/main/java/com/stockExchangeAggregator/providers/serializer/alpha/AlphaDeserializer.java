package com.stockExchangeAggregator.providers.serializer.alpha;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.stockExchangeAggregator.model.acme.POJOInterface;
import com.stockExchangeAggregator.model.alpha.Alpha;
import com.stockExchangeAggregator.providers.serializer.DeserializerInterface;

public class AlphaDeserializer extends JsonDeserializer<Alpha> implements DeserializerInterface {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public POJOInterface deserializeJSON(String json, Class pojoClass)
			throws JsonParseException, JsonMappingException, IOException {
		final AlphaDeserializer myClassDeserializer = new AlphaDeserializer();
		final SimpleModule deserializerModule = new SimpleModule();
		deserializerModule.addDeserializer(Alpha.class, myClassDeserializer);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(deserializerModule);
		return (POJOInterface) mapper.readValue(json, pojoClass);
	}

	@Override
	public Alpha deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		return null;
	}
}
