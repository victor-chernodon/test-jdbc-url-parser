package example.jdbc.utils;

import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {
    public static Map<String, String> getParametersMap(String queryParams, String parameterSeparator) {
        Map<String, String> queryMap = new HashMap<>();
        for (String param : queryParams.split(parameterSeparator)) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                queryMap.put(pair[0], pair[1]);
            }
        }
        return queryMap;
    }
}