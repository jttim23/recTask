package pl.jedro.jsontask.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Printer {

    public void print(Map<String, BigDecimal> results, List<String> errors, String serviceType) {
        System.out.println(serviceType + "Service:\n");
        printResult(results);
        if (!errors.isEmpty()) {
            System.out.println("During runtime following errors were found and calculations were omitted:\n");
            errors.forEach(System.out::println);
        }
    }

    public static void printResult(Map<String, BigDecimal> results) {
        if (results.isEmpty()) {
            System.out.println("Nothing to be calculated:");
        } else {
            System.out.println("Printing results:\n");
            results.forEach((k, v) -> System.out.println(k + " - " + v));
        }
    }
}
