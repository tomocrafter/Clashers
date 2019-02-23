package net.tomocraft.clashers.conf;

public class ConfigurationBuilder {

	private ConfigurationBase baseConfig = new ConfigurationBase();

	public ConfigurationBuilder setRestBaseURL(String restBaseURL) {
		baseConfig.setRestBaseURL(restBaseURL);
		return this;
	}

	public ConfigurationBuilder setToken(String token) {
		baseConfig.setToken(token);
		return this;
	}

	public Configuration build() {
		checkNotBuilt();
		try {
			return this.baseConfig;
		} finally {
			baseConfig = null;
		}
	}

	private void checkNotBuilt() {
		if (baseConfig == null) {
			throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
		}
	}
}
