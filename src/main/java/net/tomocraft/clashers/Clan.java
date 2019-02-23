package net.tomocraft.clashers;

import com.google.gson.JsonObject;

public class Clan {

	private final String tag;
	private final String name;
	private final Type type;
	private final Location location;
	private final Badges badges;

	public Clan(JsonObject root) {
		tag = root.get("tag").getAsString();
		name = root.get("name").getAsString();
		type = Type.fromValue(root.get("type").getAsString());
		if (root.has("location")) {
			location = new Location(root.getAsJsonObject("location"));
		} else {
			location = null;
		}
		badges = new Badges(root.getAsJsonObject("badgeUrls"));
	}

	public String getTag() {
		return tag;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

	public Badges getBadges() {
		return badges;
	}

	public enum Type {
		OPEN("open"),
		INVITE_ONLY("inviteOnly"),
		CLOSED("closed");

		private final String value;

		Type(String value) {
			this.value = value;
		}

		public static Type fromValue(String value) {
			for (Type type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
