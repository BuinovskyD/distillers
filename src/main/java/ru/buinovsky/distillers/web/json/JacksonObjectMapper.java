package ru.buinovsky.distillers.web.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.buinovsky.distillers.model.AbstractBaseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonObjectMapper extends ObjectMapper {

    private static final JacksonObjectMapper MAPPER = new JacksonObjectMapper();

    private JacksonObjectMapper() {
        registerModule(new Hibernate5Module());
        registerModule(new JavaTimeModule());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static JacksonObjectMapper getMapper() {
        return MAPPER;
    }

    public static <T extends AbstractBaseEntity> String convertObjectToJson(T t) {
        String result = null;

        try {
            result = MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T extends AbstractBaseEntity> String convertObjectListToJson(List<T> list) {
        StringBuilder builder = new StringBuilder("[");

        list.stream()
                .map(JacksonObjectMapper::convertObjectToJson)
                .forEach(str -> {
                    if (builder.length() > 1) {
                        builder.append(", ");
                    }
                    builder.append(str);
                });
        builder.append("]");

        return builder.toString();
    }

    public static <T> String convertIgnoreProperty(String json, String... props) {
        String result = null;
        try {
            Map<String, String> map = MAPPER.readValue(json, Map.class);
            for (String prop : props) {
                map.remove(prop);
            }
            result = MAPPER.writeValueAsString(map);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
