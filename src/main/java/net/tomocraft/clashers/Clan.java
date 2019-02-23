package net.tomocraft.clashers;

import com.google.gson.JsonObject;
import net.tomocraft.clashers.http.WarFrequency;

public class Clan {

	private final String tag;
	private final String name;
	private final Type type;
	private final Location location;
	private final Badges badges;
	private final int clanLevel;
	private final int clanPoints;
	private final int clanVersusPoints;
	private final int requiredTrophies;
	private final WarFrequency warFrequency;
	private final int warWinStreak;
	private final int warWin;
	private final int warTies;
	private final int warLosses;
	private final boolean isWarLogPublic;
	private final int members;

	Clan(JsonObject root) {
		tag = root.get("tag").getAsString();
		name = root.get("name").getAsString();
		type = Type.fromValue(root.get("type").getAsString());
		location = root.has("location")
				? new Location(root.getAsJsonObject("location"))
				: null;
		badges = new Badges(root.getAsJsonObject("badgeUrls"));
		clanLevel = root.get("clanLevel").getAsInt();
		clanPoints = root.get("clanPoints").getAsInt();
		clanVersusPoints = root.get("clanVersusPoints").getAsInt();
		requiredTrophies = root.get("requiredTrophies").getAsInt();
		warFrequency = WarFrequency.valueOf(root.get("warFrequency").getAsString());
		warWinStreak = root.get("warWinStreak").getAsInt();
		warWin = root.get("warWin").getAsInt();
		warTies = root.get("warTies").getAsInt();
		warLosses = root.get("warLosses").getAsInt();
		isWarLogPublic = root.get("isWarLogPublic").getAsBoolean();
		members = root.get("members").getAsInt();
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

	public int getClanLevel() {
		return clanLevel;
	}

	public int getClanPoints() {
		return clanPoints;
	}

	public int getClanVersusPoints() {
		return clanVersusPoints;
	}

	public int getRequiredTrophies() {
		return requiredTrophies;
	}

	public WarFrequency getWarFrequency() {
		return warFrequency;
	}

	public int getWarWinStreak() {
		return warWinStreak;
	}

	public int getWarWin() {
		return warWin;
	}

	public int getWarTies() {
		return warTies;
	}

	public int getWarLosses() {
		return warLosses;
	}

	public boolean isWarLogPublic() {
		return isWarLogPublic;
	}

	public int getMembers() {
		return members;
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
