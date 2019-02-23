package net.tomocraft.clashers.http;

public enum WarFrequency {
	ALWAYS("always"),
	MORE_THAN_ONCE_PER_WEEK("moreThanOncePerWeek"),
	ONCE_PER_WEEK("oncePerWeek"),
	LESS_THAN_ONCE_PER_WEEK("lessThanOncePerWeek"),
	NEVER("never"),
	UNKNOWN("unknown");

	private final String value;

	WarFrequency(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
