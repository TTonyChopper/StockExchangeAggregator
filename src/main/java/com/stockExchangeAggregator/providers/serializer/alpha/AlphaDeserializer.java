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
import com.stockExchangeAggregator.model.alpha.TimeSerieAdjusted;
import com.stockExchangeAggregator.model.alpha.TimeSerieDailyAdjusted;
import com.stockExchangeAggregator.model.alpha.TimeSerieIntraday;
import com.stockExchangeAggregator.model.alpha.TimeSerieRegular;
import com.stockExchangeAggregator.providers.serializer.DeserializerInterface;

public class AlphaDeserializer extends JsonDeserializer<Alpha> implements DeserializerInterface
{

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	@Override
	public POJOInterface deserializeJSON(String json, Class pojoClass)
			throws JsonParseException, JsonMappingException, IOException
	{
		final SimpleModule deserializerModule = new SimpleModule();
		deserializerModule.addDeserializer(Alpha.class, this);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(deserializerModule);
		return (POJOInterface) mapper.readValue(json, pojoClass);
	}

	private void deserializeTimeSerie(Alpha alpha, String key, JsonNode node, Class<? extends TimeSerie> clazz)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		TimeSerie serie = (TimeSerie) mapper.readValue(node.traverse(), clazz);
		serie.setName(key);

		alpha.addTimeSerie(serie);
	}

	private void deserializeTimeSeries(Alpha alpha, String key, JsonNode node, Class<? extends TimeSerie> clazz)
			throws JsonParseException, JsonMappingException, IOException
	{
		alpha.setTimeSeriesKey(key);

		Iterator<Entry<String, JsonNode>> it = node.fields();
		while (it.hasNext())
		{
			Entry<String, JsonNode> entry = it.next();
			deserializeTimeSerie(alpha, entry.getKey(), entry.getValue(), clazz);
		}

		// reverse order
		Collections.reverse(alpha.getTimeSeries());
	}

	private void deserializeAlphaChild(Alpha alpha, String key, JsonNode node)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		switch (key)
		{
			case "Meta Data":
				alpha.setMetaData((MetaData) mapper.readValue(node.traverse(), MetaData.class));
				break;
			case "Weekly Time Series":
			case "Monthly Time Series":
				deserializeTimeSeries(alpha, key, node, TimeSerieRegular.class);
				break;
			case "Weekly Adjusted Time Series":
			case "Monthly Adjusted Time Series":
				deserializeTimeSeries(alpha, key, node, TimeSerieAdjusted.class);
				break;
			case "Time Series (Daily)":
				if (alpha.getMetaData() != null)
				{
					if (key.contains("Intraday")) {
						deserializeTimeSeries(alpha, key, node, TimeSerieIntraday.class);
					}
					else if ("Daily Time Series with Splits and Dividend Events".equals(key))
					{
						deserializeTimeSeries(alpha, key, node, TimeSerieDailyAdjusted.class);
					}
				}
				break;
			default:
				if (key.contains("Time Series"))
				{
					//Daily
					deserializeTimeSeries(alpha, key, node, TimeSerieRegular.class);
				}
				break;
		}
	}

	@Override
	public Alpha deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException
	{

		Alpha alpha = new Alpha();

		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		Iterator<Entry<String, JsonNode>> it = node.fields();
		while (it.hasNext())
		{
			Entry<String, JsonNode> entry = it.next();
			deserializeAlphaChild(alpha, entry.getKey(), entry.getValue());
		}

		return alpha;
	}
}
