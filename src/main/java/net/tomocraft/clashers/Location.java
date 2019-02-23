package net.tomocraft.clashers;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

public class Location {

	private final int id;
	private final String name;
	private final boolean isCountry;
	private final CountryCode countryCode;

	Location(JsonObject root) {
		id = root.get("id").getAsInt();
		name = root.get("name").getAsString();
		isCountry = root.get("isCountry").getAsBoolean();
		countryCode = root.has("countryCode")
				? CountryCode.getByCode(root.get("countryCode").getAsString())
				: null;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isCountry() {
		return isCountry;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}
}
