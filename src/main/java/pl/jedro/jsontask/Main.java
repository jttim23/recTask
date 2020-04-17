package pl.jedro.jsontask;

import pl.jedro.jsontask.services.AbstractService;
import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String jsonPath = "..\\employees.json";
        String csvPath = "..\\employees.csv";
        if (args.length != 0) {
            jsonPath = args[0];
            csvPath = args[1];
        }
        JsonService jsonService = new JsonService();
        CsvService csvService = new CsvService();
        try {
            System.out.println("Printing Json results:\n");
            AbstractService.printResult(jsonService.calculate(new File(jsonPath)));
            System.out.println("Printing CSV results:\n");
            AbstractService.printResult(csvService.calculate(new File(csvPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

