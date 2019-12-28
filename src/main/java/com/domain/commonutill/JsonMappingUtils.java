package com.domain.commonutill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class JsonMappingUtils {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static <T> T toObject(final String json, final Class<T> type) {
		try {
			return MAPPER.readValue(json, type);
		} catch (IOException e) {
			throw new IllegalStateException("object convert failed", e);
		}
	}

	public static String toJson(final Object object) {
		try {
			return MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("json convert failed", e);
		}
	}
}
