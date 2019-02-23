package net.tomocraft.clashers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.tomocraft.clashers.conf.Configuration;
import net.tomocraft.clashers.exception.*;
import net.tomocraft.clashers.http.ClanSearchParametersBuilder;
import net.tomocraft.clashers.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class Clashers {

	private static final Gson GSON = new GsonBuilder().create();

	private final Configuration conf;
	private final HttpClient client;

	public Clashers(Configuration conf) {
		if (conf == null) {
			throw new NullPointerException("Configuration cannot be null");
		}
		this.conf = conf;
		this.client = new HttpClient(conf, GSON);
	}

	public List<Clan> searchClans(ClanSearchParametersBuilder parametersBuilder) throws FilterNotFoundException, NotFoundException, BadRequestException, AuthenticationException, UnknownErrorException, RateLimitExceededException {
		JsonObject response = client.get("clans", parametersBuilder);
		JsonArray items = response.getAsJsonArray("items");
		final List<Clan> clans = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			clans.add(new Clan(items.get(i).getAsJsonObject()));
		}
		return clans;
	}
}
