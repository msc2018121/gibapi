package com.msc.gwt.server;

import com.msc.gwt.client.GreetingService;
import com.msc.gwt.shared.FieldVerifier;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
//		if (!FieldVerifier.isValidName(input)) {
//			// If the input is not valid, throw an IllegalArgumentException back to
//			// the client.
//			throw new IllegalArgumentException("Name must be at least 4 characters long");
//		}

//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
//		input = escapeHtml(input);
//		userAgent = escapeHtml(userAgent);

		int proxyPort = 8080;


		Authenticator proxyAuthenticator = new Authenticator() {
		  @Override public Request authenticate(Route route, Response response) throws IOException {
		       String credential = Credentials.basic(username, password);
		       return response.request().newBuilder()
		           .header("Proxy-Authorization", credential)
		           .build();
		  }
		};

		OkHttpClient client = new OkHttpClient.Builder()
		    .connectTimeout(60, TimeUnit.SECONDS)
		    .writeTimeout(60, TimeUnit.SECONDS)
		    .readTimeout(60, TimeUnit.SECONDS)
		    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
		    .proxyAuthenticator(proxyAuthenticator)
		    .build();
		
//		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://developer.api.bk.mufg.jp/btmu/corporat
		  .get()
		  .addHeader("x-ibm-client-id", "c965122e-996e-4643-84ac-2ce2b
		  .addHeader("accept", "application/json")
		  .addHeader("x-btmu-seq-no", "20180123-1111111111111111")
		  .build();

		Response response = null;
		
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java
		String body = null;
		try {
			body = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		JsonParser jp = new JsonParser(); 

	    
		String json = body;
		return json;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
