package net.tomocraft.clashers.exception;

import com.google.gson.JsonObject;

public class RateLimitExceededException extends ClashersException {
	public RateLimitExceededException(String message) {
		super(message);
	}
}
