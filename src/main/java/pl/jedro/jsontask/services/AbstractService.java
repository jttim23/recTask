package pl.jedro.jsontask.services;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService {
    public static void printResult(Map<String, BigDecimal> map) {
        if (Optional.ofNullable(map).isEmpty()) {
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

    public static BigDecimal unifyDecimalFormat(String decToFix) {
        return new BigDecimal(decToFix.replace(',', '.'));
    }

    public abstract Map<String, BigDecimal> calculate(File file) throws Exception;


}
