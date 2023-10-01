package giri.apurba.apps.service.core;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

/**
 * Service class to invoke HTTP API services
 * 
 * @author AGIRI6
 *
 */
public final class APIService extends APIServiceBase {

	/**
	 * Invoke HTTP API with GET method
	 * 
	 * @param serviceUrl - Service URL for the API service
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(String serviceUrl) {
		return invoke(HttpMethod.GET, null, serviceUrl, null, null, null, 0, 0);
	}

	/**
	 * Invoke HTTP API with POST method
	 * 
	 * @param serviceUrl  - Service URL for the API service
	 * @param requestBody - Request body content
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(String serviceUrl, String requestBody) {
		return invoke(HttpMethod.POST, null, serviceUrl, null, null, requestBody, 0, 0);
	}

	/**
	 * Invoke HTTP API with GET method
	 * 
	 * @param serviceUrl  - Service URL for the API service
	 * @param authType    - Authentication type for the API service (e.g.
	 *                    {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral - Authentication value based on {@link AuthType}
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(String serviceUrl, AuthType authType, String authLiteral) {
		return invoke(HttpMethod.GET, null, serviceUrl, authType, authLiteral, null, 0, 0);
	}

	/**
	 * Invoke HTTP API with POST method
	 * 
	 * @param serviceUrl  - Service URL for the API service
	 * @param authType    - Authentication type for the API service (e.g.
	 *                    {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral - Authentication value based on {@link AuthType}
	 * @param requestBody - Request body content
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(String serviceUrl, AuthType authType, String authLiteral,
			String requestBody) {
		return invoke(HttpMethod.POST, null, serviceUrl, authType, authLiteral, requestBody, 0, 0);
	}

	/**
	 * Method to invoke HTTP API service
	 * 
	 * @param httpMethod  - HTTP method to invoke on the API service (e.g.
	 *                    {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                    {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param serviceUrl  - Service URL for the API service
	 * @param authType    - Authentication type for the API service (e.g.
	 *                    {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral - Authentication value based on {@link AuthType} (For
	 *                    BASIC, set to 'username:password' format)
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(HttpMethod httpMethod, String serviceUrl, AuthType authType,
			String authLiteral) {
		return invoke(httpMethod, null, serviceUrl, authType, authLiteral, null, 0, 0);
	}

	/**
	 * Method to invoke HTTP API service
	 * 
	 * @param httpMethod  - HTTP method to invoke on the API service (e.g.
	 *                    {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                    {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param contentType - Request body {@link ContentType} (e.g.
	 *                    {@link ContentType.JSON}, {@link ContentType.XML})
	 * @param serviceUrl  - Service URL for the API service
	 * @param authType    - Authentication type for the API service (e.g.
	 *                    {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral - Authentication value based on {@link AuthType} (For
	 *                    BASIC, set to 'username:password' format)
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(HttpMethod httpMethod, ContentType contentType, String serviceUrl,
			AuthType authType, String authLiteral) {
		return invoke(httpMethod, contentType, serviceUrl, authType, authLiteral, null, 0, 0);
	}

	/**
	 * Method to invoke HTTP API service
	 * 
	 * @param httpMethod  - HTTP method to invoke on the API service (e.g.
	 *                    {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                    {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param contentType - Request body {@link ContentType} (e.g.
	 *                    {@link ContentType.JSON}, {@link ContentType.XML})
	 * @param serviceUrl  - Service URL for the API service
	 * @param authType    - Authentication type for the API service (e.g.
	 *                    {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral - Authentication value based on {@link AuthType} (For
	 *                    BASIC, set to 'username:password' format)
	 * @param requestBody - Request body content
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(HttpMethod httpMethod, ContentType contentType, String serviceUrl,
			AuthType authType, String authLiteral, String requestBody) {
		return invoke(httpMethod, contentType, serviceUrl, authType, authLiteral, requestBody, 0, 0);
	}

	/**
	 * Method to invoke HTTP API service
	 * 
	 * @param httpMethod     - HTTP method to invoke on the API service (e.g.
	 *                       {@link HttpMethod.GET}, {@link HttpMethod.POST},
	 *                       {@link HttpMethod.PUT}, {@link HttpMethod.DELETE})
	 * @param contentType    - Request body {@link ContentType} (e.g.
	 *                       {@link ContentType.JSON}, {@link ContentType.XML})
	 * @param serviceUrl     - Service URL for the API service
	 * @param authType       - Authentication type for the API service (e.g.
	 *                       {@link AuthType.BEARER}, {@link AuthType.BASIC})
	 * @param authLiteral    - Authentication value based on {@link AuthType} (For
	 *                       BASIC, set to 'username:password' format)
	 * @param requestBody    - Request body content
	 * @param requestTimeout - Sets a timeout for this request.
	 * 
	 * @return Service response as {@link HttpResponse}
	 */
	public HttpResponse<String> invokeAPI(HttpMethod httpMethod, ContentType contentType, String serviceUrl,
			AuthType authType, String authLiteral, String requestBody, long requestTimeout) {
		return invoke(httpMethod, contentType, serviceUrl, authType, authLiteral, requestBody, requestTimeout, 0);
	}

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
	public HttpResponse<String> invokeAPI(HttpMethod httpMethod, ContentType contentType, String serviceUrl,
			AuthType authType, String authLiteral, String requestBody, long requestTimeout, long connectionTimeout) {
		return invoke(httpMethod, contentType, serviceUrl, authType, authLiteral, requestBody, requestTimeout,
				connectionTimeout);
	}

}