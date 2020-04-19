package pl.jedro.jsontask.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Printer {


    public void print(List<String> errors, Map<String, BigDecimal> map) {
        printResult(map);
        if (!errors.isEmpty()) {
            System.out.println("During runtime following errors were found and calculations were omitted:\n");
            errors.forEach(System.out::println);
        }
    }


    public static void printResult(Map<String, BigDecimal> map) {
        if (map.isEmpty()) {
            System.out.println("Nothing to be calculated in file");
        } else {
            System.out.println("Printing results:\n");
            map.forEach((k, v) -> System.out.println(k + " - " + v));
        }
    }

}
