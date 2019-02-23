package net.tomocraft.clashers.exception;

import com.google.gson.JsonObject;

public class NotFoundException extends ClashersException {
	public NotFoundException(String message) {
		super(message);
	}
}
