package pl.jedro.jsontask.services;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

public abstract class AbstractService {


    public static String deleteDoubleQuotas(String strToTrim) {
        return strToTrim.replace("\"", "");
    }

    public static BigDecimal unifyDecimalFormat(String decToFix) throws NumberFormatException {
        return new BigDecimal(decToFix.replace(',', '.'));
    }


    public static void handleMapAdding(String parsedJob, BigDecimal parsedSalary, Map<String, BigDecimal> map) {
        if (parsedJob.length() != 0) {
            if (map.containsKey(parsedJob)) {
                BigDecimal existingSalary = map.get(parsedJob);
                map.put(parsedJob, existingSalary.add(parsedSalary));
            } else
                map.put(parsedJob, parsedSalary);
        } else throw new RuntimeException();
    }

    public abstract Map<String, BigDecimal> calculate(File file) throws Exception;


}
