package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("utility class");
    }

    public static ObjectNode createSuccessfulResponse(Object response) {
        ObjectNode result = Json.newObject();
        result.putPOJO("data", response);
        return result;
    }

    public static ObjectNode createErrorResponse(String errCode) {
        ObjectNode result = Json.newObject();
        result.put("errorMessage", errCode);
        return result;
    }
}
