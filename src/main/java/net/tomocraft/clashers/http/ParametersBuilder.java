package net.tomocraft.clashers.http;

import net.tomocraft.clashers.exception.FilterNotFoundException;

public interface ParametersBuilder {
	String toParameters() throws FilterNotFoundException;
}
