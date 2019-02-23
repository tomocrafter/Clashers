package net.tomocraft.clashers;

import com.google.gson.JsonObject;

public class Location {
	private int id;
	private String name;
	private boolean isCountry;

	Location(JsonObject root) {
		id = root.get("id").getAsInt();
		name = root.get("name").getAsString();
		isCountry = root.get("isCountry").getAsBoolean();
	}
}
