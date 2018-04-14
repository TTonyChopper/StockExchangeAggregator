package com.stockExchangeAggregator.providers.serializer.alpha;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;

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
import com.stockExchangeAggregator.model.alpha.MetaData;
import com.stockExchangeAggregator.model.alpha.TimeSerie;
import com.stockExchangeAggregator.providers.serializer.DeserializerInterface;

public class AlphaDeserializer extends JsonDeserializer<Alpha> implements DeserializerInterface {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public POJOInterface deserializeJSON(String json, Class pojoClass)
			throws JsonParseException, JsonMappingException, IOException {
		final SimpleModule deserializerModule = new SimpleModule();
		deserializerModule.addDeserializer(Alpha.class, this);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(deserializerModule);
		return (POJOInterface) mapper.readValue(json, pojoClass);
	}

	private void deserializeTimeSerie(Alpha alpha, String key, JsonNode node)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TimeSerie serie = (TimeSerie) mapper.readValue(node.traverse(), TimeSerie.class);
		serie.setName(key);

		alpha.addTimeSerie(serie);
	}

	private void deserializeTimeSeries(Alpha alpha, String key, JsonNode node)
			throws JsonParseException, JsonMappingException, IOException {
		alpha.setTimeSeriesKey(key);

		Iterator<Entry<String, JsonNode>> it = node.fields();
		while (it.hasNext()) {
			Entry<String, JsonNode> entry = it.next();
			deserializeTimeSerie(alpha, entry.getKey(), entry.getValue());
		}
		
		//reverse order
		Collections.reverse(alpha.getTimeSeries());
	}

	private void deserializeAlphaChild(Alpha alpha, String key, JsonNode node)
			throws JsonParseException, JsonMappingException, IOException {
		switch (key) {
		case "Meta Data":
			ObjectMapper mapper = new ObjectMapper();
			alpha.setMetaData((MetaData) mapper.readValue(node.traverse(), MetaData.class));
			break;
		default:
			if (key.startsWith("Time Series")) {
				deserializeTimeSeries(alpha, key, node);
			}
			break;
		}
	}

	@Override
	public Alpha deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		Alpha alpha = new Alpha();

		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		Iterator<Entry<String, JsonNode>> it = node.fields();
		while (it.hasNext()) {
			Entry<String, JsonNode> entry = it.next();
			deserializeAlphaChild(alpha, entry.getKey(), entry.getValue());
		}

		return alpha;
	}
}
