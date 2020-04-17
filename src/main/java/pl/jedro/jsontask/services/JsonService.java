package pl.jedro.jsontask.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JsonService extends AbstractService {


    public JsonService() {
    }

    @Override
    public Map<String, BigDecimal> calculate(File file) throws IOException {
        Map<String, BigDecimal> map = new HashMap<>();
        String parsedJob = null;
        BigDecimal parsedSalary;
        if (file.length() != 0) {
            JsonFactory factory = new JsonFactory();
            JsonParser jParser = factory.createParser(file);
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                Optional<String> fieldName = Optional.ofNullable(jParser.getCurrentName());

                if (fieldName.isPresent()) {
                    if ("job".equals(fieldName.get())) {
                        jParser.nextToken();
                        parsedJob = jParser.getText();
                    }
                    if ("salary".equals(fieldName.get())) {
                        jParser.nextToken();
                        try {
                            parsedSalary = jParser.getDecimalValue();
                            handleMap(parsedJob, parsedSalary, map);
                        } catch (JsonParseException e) {
                            parsedSalary = AbstractService.unifyDecimalFormat(jParser.getText());
                            handleMap(parsedJob, parsedSalary, map);
                        }
                    }
                }
            }
            jParser.close();
        }
        return map;
    }

    private static void handleMap(String parsedJob, BigDecimal parsedSalary, Map<String, BigDecimal> map) {
        if (map.containsKey(parsedJob)) {
            BigDecimal existingSalary = map.get(parsedJob);
            map.put(parsedJob, existingSalary.add(parsedSalary));
        } else {
            map.put(parsedJob, parsedSalary);
        }
    }
}
