package com.rogue.search.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  private JsonUtils() {
  }

  public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {
    return objectMapper.readValue(content, valueType);
  }
}
