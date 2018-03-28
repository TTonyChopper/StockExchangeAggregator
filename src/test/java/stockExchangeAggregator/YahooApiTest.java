package stockExchangeAggregator;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.stream.Collectors;


import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;

import javax.net.ssl.X509ExtendedTrustManager;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockExchangeAggregator.model.Yahoo;

public class YahooApiTest{

	@Test
	public void test1(){
		 //
		 TrustManager[] trustAllCerts = new TrustManager[]{
	                new X509ExtendedTrustManager() {
	                    @Override
	                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }

	                    @Override
	                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                    }

	                    @Override
	                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                    }

	                    @Override
	                    public void checkClientTrusted(X509Certificate[] xcs, String string, Socket socket) throws CertificateException {

	                    }

	                    @Override
	                    public void checkServerTrusted(X509Certificate[] xcs, String string, Socket socket) throws CertificateException {

	                    }

	                    @Override
	                    public void checkClientTrusted(X509Certificate[] xcs, String string, SSLEngine ssle) throws CertificateException {

	                    }

	                    @Override
	                    public void checkServerTrusted(X509Certificate[] xcs, String string, SSLEngine ssle) throws CertificateException {

	                    }

	                }
	            };

	            SSLContext sc=null;
				try {
					sc = SSLContext.getInstance("SSL");
					  sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	            // Create all-trusting host name verifier
	            HostnameVerifier allHostsValid = new HostnameVerifier() {
	                @Override
	                public boolean verify(String hostname, SSLSession session) {
	                    return true;
	                }
	            };
	            // Install the all-trusting host verifier
	            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	            
	            URL url;
	            String res=null;
				try {
					url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/GOOG?region=US&lang=en-US&range=1d&includePrePost=false&interval=2m&corsDomain=finance.yahoo.com&.tsrc=finance");
					 URLConnection con = url.openConnection();
					 InputStream is = new URL(url.toString()).openStream();
					 res=new BufferedReader(new InputStreamReader(is))
							  .lines().collect(Collectors.joining("\n"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
		if(res!=null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Yahoo yahoo=null;
			try {
				yahoo=mapper.readValue(res, Yahoo.class);
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(yahoo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//System.out.println(yahoo.getChart().toString());
			
			
		}else {
			System.out.println(res);
		}
		 
	}
	
	
}
