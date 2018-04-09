package com.stockExchangeAggregator.providers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import com.enums.HttpMethod;

public class CurlProvider {
	/** Singleton **/
	private static CurlProvider INSTANCE;

	public static CurlProvider getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CurlProvider();
		}
		return INSTANCE;
	}

	public HttpResponse getResponse(String url, HttpMethod method, String payload) {
		return getResponse(url, method, payload, null);
	}

	public HttpResponse getResponse(String url, HttpMethod method, String payload, Map<String, String> headers) {
		String msg = payload;

		SSLContext sslContext;
		SSLConnectionSocketFactory sslConnectionSocketFactory = null;
		try {
			sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
			sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
					new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
		} catch (KeyManagementException e2) {
			e2.printStackTrace();
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		} catch (KeyStoreException e2) {
			e2.printStackTrace();
		}

		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

		HttpContext localContext = new BasicHttpContext();

		HttpRequestBase httpget;
		if (method == HttpMethod.GET) {
			httpget = new HttpGet(url);
		} else if (method == HttpMethod.POST) {
			httpget = new HttpPut(url);
			try {
				((HttpPut) httpget).setEntity(new StringEntity(msg));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} else {
			httpget = new HttpPost(url);
			try {
				((HttpPost) httpget).setEntity(new StringEntity(msg));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}

		if (headers != null && headers.size() > 0)
			headers.keySet().forEach(k -> {
				httpget.addHeader(k, headers.get(k));
			});

		HttpResponse response = null;

		try {
			response = httpclient.execute(httpget, localContext);
		} catch (ClientProtocolException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return response;
	}

	public String getURI(String url, HttpMethod method, String payload) throws HttpResponseException, IOException {
		return getURI(url, method, payload, null);
	}

	public String getURI(String url, HttpMethod method, String payload, Map<String, String> headers) throws HttpResponseException, IOException {
		HttpResponse response = getResponse(url, method, payload, headers);

		String responseString = "";
		//try {
			responseString = new BasicResponseHandler().handleResponse(response);
		/*} catch (HttpResponseException e) {
			System.err.println("Err:" + url);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return responseString;
	}

}
