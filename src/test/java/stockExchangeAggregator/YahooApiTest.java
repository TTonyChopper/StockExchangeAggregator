package stockExchangeAggregator;

import java.io.IOException;
import org.junit.Test;
import com.enums.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.yahoo.Yahoo;
import com.stockExchangeAggregator.providers.CurlProvider;

public class YahooApiTest {

	@Test
	public void test1() {

		String res = null;
		String strUrl = "https://query1.finance.yahoo.com/v8/finance/chart/GOOG?region=US&lang=en-US&range=1d&includePrePost=false&interval=2m&corsDomain=finance.yahoo.com&.tsrc=finance";
		res = CurlProvider.getInstance().getURI(strUrl, HttpMethod.GET, null);

		if (res != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Yahoo yahoo = null;
			try {
				yahoo = mapper.readValue(res, Yahoo.class);
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(yahoo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(yahoo.getChart().toString());

		} else {
			System.out.println(res);
		}

	}

}
