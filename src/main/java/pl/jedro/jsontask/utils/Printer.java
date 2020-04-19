package pl.jedro.jsontask.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Printer {

    public Printer() {
    }

    public void print(List<String> errors, Map<String, BigDecimal> map) {
        printResult(map);
        if (!errors.isEmpty()) {
            System.out.println("During runtime following errors were found and calculations were omitted:\n");
            for (String error : errors) {
                {
                    System.out.println(error);
                }
            }
        }
    }


    private static void printResult(Map<String, BigDecimal> map) {
        if (map.isEmpty()) {
            System.out.println("Nothing to be calculated in file");
        } else {
            System.out.println("Printing results:\n");
            for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

}
