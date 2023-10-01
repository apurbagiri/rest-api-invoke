package giri.apurba.apps.service.core;

import java.util.Map;

import giri.apurba.apps.model.HttpErrorResponse;

import static java.util.Map.entry;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class APIServiceBase {

	private final static boolean IS_SSL_VERIFY_DISABLED = true;

	public static enum HttpMethod {
		GET, POST, PUT, DELETE
	};

	public static enum AuthType {
		BEARER, BASIC
	};

	public static enum ContentType {
		JSON, XML, URL_ENCODED, FORM_DATA
	};

	private static Map<String, String> contentTypes = Map.ofEntries(entry(ContentType.JSON.name(), "application/json"),
			entry(ContentType.XML.name(), "application/xml"),
			entry(ContentType.URL_ENCODED.name(), "application/x-www-form-urlencoded"),
			entry(ContentType.FORM_DATA.name(), "multipart/form-data"));

	/**
	 * Method to invoke HTTP API service
	 * 
	 * @param httpMethod        - HTTP method to invoke on the API service (e.g.
	 *                          {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                          {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param contentType       - Request body {@link ContentType} (e.g.
	 *                          {@link ContentType.JSON}, {@link ContentType.XML})
	 * @param serviceUrl        - Service URL for the API service
	 * @param authType          - Authentication type for the API service (e.g.
	 *                          {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral       - Authentication value based on {@link AuthType}
	 *                          (For BASIC, set to 'username:password' format)
	 * @param requestBody       - Request body content
	 * @param requestTimeout    - Sets a timeout for this request.
	 * @param connectionTimeout - Sets the connect timeout duration for
	 *                          {@link HttpClient}
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	protected HttpResponse<String> invoke(HttpMethod httpMethod, ContentType contentType, String serviceUrl,
			AuthType authType, String authLiteral, String requestBody, long requestTimeout, long connectionTimeout) {
		HttpResponse<String> response;
		try {
			HttpClient httpClient = getClientBuilder(connectionTimeout).build();
			HttpRequest httpRequest = getRequestBuilder(httpMethod, contentType, serviceUrl,
					getAuthHeaderValue(authType, authLiteral), requestBody, requestTimeout).build();
			response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException | KeyManagementException | NoSuchAlgorithmException e) {
			response = new HttpErrorResponse(e.getMessage());
		}
		return response;
	}

	/**
	 * Sets the authentication header value for a given {@link AuthType} &
	 * authLiteral
	 * 
	 * @param authType
	 * @param authLiteral
	 * 
	 * @return Authentication header value
	 */
	private String getAuthHeaderValue(AuthType authType, String authLiteral) {

		if (authType == AuthType.BASIC) {
			return "Basic " + Base64.getEncoder().encodeToString(authLiteral.getBytes());
		}

		if (authType == AuthType.BEARER) {
			return "Bearer " + authLiteral;
		}

		return null;
	}

	/**
	 * Returns HttpClient builder with given parameter
	 * 
	 * @param connectionTimeout
	 * 
	 * @return HttpClient builder
	 * 
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private java.net.http.HttpClient.Builder getClientBuilder(long connectionTimeout)
			throws KeyManagementException, NoSuchAlgorithmException {
		java.net.http.HttpClient.Builder clientBuilder = HttpClient.newBuilder();
		if (IS_SSL_VERIFY_DISABLED) {
			clientBuilder.sslContext(getInsecureSSLContext());
		}
		if (connectionTimeout > 0) {
			clientBuilder.connectTimeout(Duration.ofSeconds(connectionTimeout));
		}
		return clientBuilder;
	}

	/**
	 * Returns HttpRequest builder with given parameters
	 * 
	 * @param httpMethod      - HTTP method to invoke on the API service (e.g.
	 *                        {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                        {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param contentType     - Request body {@link ContentType} (e.g.
	 *                        {@link ContentType.JSON}, {@link ContentType.XML}). If
	 *                        null, 'Content-Type' header is not set.
	 * @param serviceUrl      - Service URL for the API service
	 * @param authHeaderValue - Value of Authentication header. If null/empty,
	 *                        'Authorization' header is not set.
	 * @param requestBody     - Request body content. If null, no request body is
	 *                        sent.
	 * @param requestTimeout  - Sets a timeout for this request. Must be greater
	 *                        than 0 to be effective.
	 * 
	 * @return HttpRequest builder
	 */
	private java.net.http.HttpRequest.Builder getRequestBuilder(HttpMethod httpMethod, ContentType contentType,
			String serviceUrl, String authHeaderValue, String requestBody, long requestTimeout) {
		java.net.http.HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(URI.create(serviceUrl));
		requestBuilder.method(httpMethod.name(), requestBody != null ? HttpRequest.BodyPublishers.ofString(requestBody)
				: HttpRequest.BodyPublishers.noBody());

		if (contentType != null) {
			requestBuilder.header("Content-Type", contentTypes.get(contentType.name()));
		}

		if (authHeaderValue != null && !authHeaderValue.trim().isEmpty()) {
			requestBuilder.header("Authorization", authHeaderValue);
		}

		if (requestTimeout > 0) {
			requestBuilder.timeout(Duration.ofSeconds(requestTimeout));
		}
		return requestBuilder;
	}

	/**
	 * Returns SSL Context that doesn't validated SSL Certificates. This is only for
	 * testing purposes and shouldn't be used in real environment.
	 * 
	 * @return SSLContext
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private SSLContext getInsecureSSLContext() throws NoSuchAlgorithmException, KeyManagementException {

		TrustManager[] trustAllTm = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, trustAllTm, new SecureRandom());

		return sslContext;
	}

}
