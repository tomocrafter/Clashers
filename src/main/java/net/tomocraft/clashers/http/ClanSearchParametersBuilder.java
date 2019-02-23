package net.tomocraft.clashers.http;

import net.tomocraft.clashers.exception.FilterNotFoundException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ClanSearchParametersBuilder implements ParametersBuilder {
	@FilterParameter
	private String name;
	@FilterParameter
	private WarFrequency warFrequency;
	@FilterParameter
	private Integer locationId;
	@FilterParameter
	private Integer minMembers;
	@FilterParameter
	private Integer maxMembers;
	@FilterParameter
	private Integer minClanPoints;
	@FilterParameter
	private Integer minClanLevel;
	private Integer limit;
	private String after;// The paging key
	private String before;// The paging key

	public ClanSearchParametersBuilder setName(String name) {
		if (name.length() < 3) {
			throw new IllegalArgumentException("name has to be at least 3 characters long");
		}
		this.name = name;
		return this;
	}

	public ClanSearchParametersBuilder setWarFrequency(WarFrequency warFrequency) {
		this.warFrequency = warFrequency;
		return this;
	}

	public ClanSearchParametersBuilder setLocationId(Integer locationId) {
		this.locationId = locationId;
		return this;
	}

	public ClanSearchParametersBuilder setMinMembers(Integer minMembers) {
		this.minMembers = minMembers;
		return this;
	}

	public ClanSearchParametersBuilder setMaxMembers(Integer maxMembers) {
		this.maxMembers = maxMembers;
		return this;
	}

	public ClanSearchParametersBuilder setMinClanPoints(Integer minClanPoints) {
		this.minClanPoints = minClanPoints;
		return this;
	}

	public ClanSearchParametersBuilder setMinClanLevel(Integer minClanLevel) {
		this.minClanLevel = minClanLevel;
		return this;
	}

	public ClanSearchParametersBuilder setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public ClanSearchParametersBuilder setAfter(String after) {
		this.after = after;
		return this;
	}

	public ClanSearchParametersBuilder setBefore(String before) {
		this.before = before;
		return this;
	}

	@Override
	public String toParameters() throws FilterNotFoundException {
		final StringBuilder builder = new StringBuilder();

		boolean hasFilter = false;
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				Object value = field.get(this);
				if (value == null) {
					continue;
				}

				final boolean isFilter = field.getAnnotation(FilterParameter.class) != null;
				if (!hasFilter && isFilter) {
					hasFilter = true;
				}

				ParameterName annotation = field.getAnnotation(ParameterName.class);
				final String parameterName = annotation != null ? annotation.value() : field.getName();

				try {
					builder
							.append(builder.length() == 0 ? '?' : '&')
							.append(parameterName).append('=').append(URLEncoder.encode(value.toString(), "UTF-8"));
				} catch (UnsupportedEncodingException ignored) {
					// UTF-8 is always supported
				}
			}
		} catch (IllegalAccessException ignored) {

		}

		if (builder.length() == 0 || !hasFilter) {
			throw new FilterNotFoundException("At least one filtering parameter must exist");
		}
		return builder.toString();
	}
}
