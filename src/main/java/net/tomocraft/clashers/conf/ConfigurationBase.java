package net.tomocraft.clashers.conf;

import java.io.Serializable;

public class ConfigurationBase implements Configuration, Serializable {

	private String restBaseURL = "https://api.clashofclans.com/v1/";

	private String token = null;

	public void setRestBaseURL(String restBaseURL) {
		this.restBaseURL = restBaseURL;
	}

	@Override
	public String getRestBaseURL() {
		return this.restBaseURL;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return this.token;
	}
}
