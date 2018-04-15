package stockExchangeAggregator;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;
import com.stockExchangeAggregator.providers.CurlProvider.HttpMethod;

public class YahooApiTest {

	@Test
	public void test1() {

		String res = null;
		String strUrl = "https://query1.finance.yahoo.com/v8/finance/chart/GOOG?region=US&lang=en-US&range=1d&includePrePost=false&interval=2m&corsDomain=finance.yahoo.com&.tsrc=finance";
		try {
			res = CurlProvider.getInstance().getURI(strUrl, HttpMethod.GET, null);
		} catch (HttpResponseException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (res != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Yahoo yahoo = null;
			try {
				yahoo = mapper.readValue(res, Yahoo.class);
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(yahoo));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println(yahoo.getChart().toString());

		} else {
			System.out.println(res);
		}

	}

}
