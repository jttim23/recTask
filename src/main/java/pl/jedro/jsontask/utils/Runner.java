package pl.jedro.jsontask.utils;

import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.old.CsvService;
import pl.jedro.jsontask.old.JsonService;
import pl.jedro.jsontask.services.BaseService;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Runner {

    public void run() {
        BaseService service = new BaseService();
        Importer importer = new Importer();
        Printer printer = new Printer();
       // JsonService jsonService = new JsonService();
       // CsvService csvService = new CsvService();
        Terminal terminal = new Terminal();
        List<String> paths = terminal.getPathsFromConsole();

        Optional.ofNullable(paths.get(0)).map(path -> {
            try {
                System.out.println("JSON Service:\n");
                EmployeeWrapper employees = importer.getPeopleFromJSONFile(new File(paths.get(0)));
                Map<String, BigDecimal> sum = service.calculate(employees);
                printer.print(service.getErrorList(), sum);
            } catch (Exception e) {
                System.out.println("Could not find a JSON file.");
            }
            return path;
        });
        Optional.ofNullable(paths.get(1)).map(path -> {
            try {
                System.out.println("CSV Service:\n");
                EmployeeWrapper employees = importer.getPeopleFromCSVFile(new File(paths.get(1)));
                Map<String, BigDecimal> sum = service.calculate(employees);
                printer.print(service.getErrorList(), sum);
            } catch (Exception e) {
                System.out.println("Could not find a CSV file.");
            }
            return path;
        });

    }
}
