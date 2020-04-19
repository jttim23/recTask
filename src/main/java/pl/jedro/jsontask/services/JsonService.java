package pl.jedro.jsontask.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JsonService extends AbstractService {
    Map<String,String> errors = new HashMap<>();
    public Map<String,String> getErrors(){
        return errors;
    }


    public JsonService() {
    }

    @Override
    public Map<String, BigDecimal> calculate(File file) throws Exception {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        String parsedJob = "";
        String parsedSalary;
        if (file.length() != 0) {
            JsonFactory factory = new JsonFactory();
            JsonParser jParser = factory.createParser(file);

            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                String fieldName = jParser.getCurrentName();
                BigDecimal unifiedSalary;
                if ("job".equals(fieldName)) {
                    jParser.nextToken();
                    parsedJob = jParser.getText().trim();
                }
                if ("salary".equals(fieldName)) {
                    jParser.nextToken();
                    parsedSalary = jParser.getText().trim();
                    try {
                        unifiedSalary = unifyDecimalFormat(deleteDoubleQuotas(parsedSalary));
                    } catch (NumberFormatException e) {
                        errors.put(e.toString(),jParser.getCurrentName());
                        continue;
                    }
                    try {
                        handleMapAdding(deleteDoubleQuotas(parsedJob), unifiedSalary, map);
                    } catch (RuntimeException e){
                        errors.put(e.toString(),"job");
                    }
                }
            }
            jParser.close();
        } else throw new Exception();
        return map;
    }


}
