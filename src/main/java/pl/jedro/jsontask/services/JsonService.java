package pl.jedro.jsontask.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class JsonService extends AbstractService {


    public JsonService() {
    }

    @Override
    public Map<String, BigDecimal> calculate(File file) throws Exception {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        String parsedJob ="";
        BigDecimal parsedSalary;
        if (file.length() != 0) {
            JsonFactory factory = new JsonFactory();
            JsonParser jParser = factory.createParser(file);
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                String fieldName = jParser.getCurrentName();
                if ("job".equals(fieldName)) {
                    jParser.nextToken();
                    parsedJob = jParser.getText();
                }
                if ("salary".equals(fieldName)) {
                    jParser.nextToken();
                    try {
                        parsedSalary = jParser.getDecimalValue();
                        handleMap(parsedJob, parsedSalary, map);
                    } catch (JsonParseException e) {
                        parsedSalary = unifyDecimalFormat(jParser.getText());
                        handleMap(parsedJob, parsedSalary, map);
                    }
                }
            }
            jParser.close();
        } else throw new Exception();
        return map;
    }


}
