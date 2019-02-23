package net.tomocraft.clashers;

import com.google.gson.JsonObject;

public class Badges {

	private String tinyURL;
	private String smallURL;
	private String mediumURL;
	private String largeURL;

	public Badges(JsonObject root) {
		if (root.has("tiny"))
			tinyURL = root.get("tiny").getAsString();
		if (root.has("small"))
			smallURL = root.get("small").getAsString();
		if (root.has("medium"))
			mediumURL = root.get("medium").getAsString();
		if (root.has("large"))
			largeURL = root.get("large").getAsString();
	}
}
