package giri.apurba.apps.model;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import javax.net.ssl.SSLSession;

public class HttpErrorResponse implements HttpResponse<String> {

	private int statusCode = 500;
	private String errorMessage = "Something went wrong while processing the request";

	public HttpErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpErrorResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}
	
	public HttpErrorResponse() {}

	@Override
	public int statusCode() {
		return statusCode;
	}

	@Override
	public HttpRequest request() {
		return null;
	}

	@Override
	public Optional<HttpResponse<String>> previousResponse() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public HttpHeaders headers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String body() {
		return "{\"error\": \"" + errorMessage + "\"}";
	}

	@Override
	public Optional<SSLSession> sslSession() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public URI uri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version version() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
