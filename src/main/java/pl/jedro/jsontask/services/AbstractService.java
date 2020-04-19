package pl.jedro.jsontask.services;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService {
    public static void printResult(Map<String, BigDecimal> map) {
        if (map.isEmpty()) {
            System.out.println("Nothing to be calculated in file");
        } else {
            System.out.println("Printing results:\n");
            for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public static String deleteDoubleQuotas(String strToTrim) {
        return strToTrim.replace("\"", "");
    }

    public static BigDecimal unifyDecimalFormat(String decToFix) throws NumberFormatException{
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
