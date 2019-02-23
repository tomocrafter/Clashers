package net.tomocraft.clashers.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.tomocraft.clashers.Clashers;
import net.tomocraft.clashers.conf.Configuration;
import net.tomocraft.clashers.exception.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClient {

	private final Gson gson;
	private final Configuration conf;

	public HttpClient(Configuration conf, Gson gson) {
		this.conf = conf;
		this.gson = gson;
	}

	private JsonObject get(String path) throws
			BadRequestException,
			AuthenticationException,
			NotFoundException,
			RateLimitExceededException,
			UnknownErrorException {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(conf.getRestBaseURL() + path).openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("authorization", "Bearer " + conf.getToken());
			connection.setInstanceFollowRedirects(false);

			int statusCode = connection.getResponseCode();

			final InputStream input = (statusCode < 300) ? connection.getInputStream() : connection.getErrorStream();
			JsonObject json = this.gson.fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), JsonObject.class);

			switch (statusCode) {
				case 400:
					throw new BadRequestException(json.get("message").getAsString());
				case 403:
					throw new AuthenticationException(json.get("message").getAsString());
				case 404:
					throw new NotFoundException(json.get("message").getAsString());
				case 429:
					throw new RateLimitExceededException(json.get("message").getAsString());
				case 200:
					return json;
				default:
					throw new UnknownErrorException(statusCode + ": " + json);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public JsonObject get(String path, ParametersBuilder parametersBuilder) throws
			FilterNotFoundException,
			UnknownErrorException,
			BadRequestException,
			AuthenticationException,
			NotFoundException,
			RateLimitExceededException {
		return get(path + parametersBuilder.toParameters());
	}
}
