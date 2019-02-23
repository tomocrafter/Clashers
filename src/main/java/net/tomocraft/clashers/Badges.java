package net.tomocraft.clashers;

import com.google.gson.JsonObject;

public class Badges {

	private final String tinyURL;
	private final String smallURL;
	private final String mediumURL;
	private final String largeURL;

	Badges(JsonObject root) {
		if (root.has("tiny"))
			tinyURL = root.get("tiny").getAsString();
		else tinyURL = null;
		if (root.has("small"))
			smallURL = root.get("small").getAsString();
		else smallURL = null;
		if (root.has("medium"))
			mediumURL = root.get("medium").getAsString();
		else mediumURL = null;
		if (root.has("large"))
			largeURL = root.get("large").getAsString();
		else largeURL = null;
	}

	public String getTinyURL() {
		return tinyURL;
	}

	public String getSmallURL() {
		return smallURL;
	}

	public String getMediumURL() {
		return mediumURL;
	}

	public String getLargeURL() {
		return largeURL;
	}
}
