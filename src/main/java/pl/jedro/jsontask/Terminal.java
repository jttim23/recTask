package pl.jedro.jsontask;

import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class Terminal {

    public Terminal() {
    }

    public void run() {
        List<String> paths = getPathsFromConsole();
        JsonService jsonService = new JsonService();
        CsvService csvService = new CsvService();
        Optional.ofNullable(paths.get(0)).map(path -> {
            try {
                System.out.println("JSON Service:\n");
                printResult(jsonService.calculate(new File(path)));
                Map<String, String> errors = jsonService.getErrors();
                if (!errors.isEmpty()) {
                    System.out.println("During runtime following errors were found and calculations were omitted:\n");
                    for (Map.Entry<String, String> entry : errors.entrySet()) {
                        System.out.println(entry.getKey() + " - near : " + entry.getValue());
                    }
                }
            } catch (Exception e) {
                System.out.println("Could not find a JSON file.");
            }
            return "";
        });
        Optional.ofNullable(paths.get(1)).map(path -> {
            try {
                System.out.println("CSV Service:\n");
                printResult(csvService.calculate(new File(path)));
                Map<String, Long> errors = csvService.getErrors();
                if (!errors.isEmpty()) {
                    System.out.println("During runtime following errors were found and calculations were omitted:\n");
                    for (Map.Entry<String, Long> entry : errors.entrySet()) {
                        System.out.println(entry.getKey() + " - on record number: " + entry.getValue());
                    }
                }
            } catch (Exception e) {
                System.out.println("Could not find a CSV file.");
            }
            return "";
        });
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

    private List<String> getPathsFromConsole() {
        List<String> paths = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to input JSON file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();
        String jsonPath;
        String csvPath;

        while (!flag.equals("n") && !flag.equals("y")) {
            System.out.println("Press Y if yes, or N if no:\n" + "Y/N:");
            flag = scanner.nextLine();
        }
        switch (flag) {
            case "y":
                System.out.println("Please provide JSON file path:");
                jsonPath = scanner.nextLine();
                csvPath = getCSVPathFromConsole();
                paths = Arrays.asList(jsonPath, csvPath);
                break;
            case "n":
                csvPath = getCSVPathFromConsole();
                paths = Arrays.asList(null, csvPath);
                break;
        }
        return paths;
    }

    private String getCSVPathFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to input CSV file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();
        String csvPath = null;

        while (!flag.equals("n") && !flag.equals("y")) {
            System.out.println(" Press Y if yes, or N if no:\n" + "Y/N:");
            flag = scanner.nextLine();
        }
        switch (flag) {
            case "y":
                System.out.println("Please provide CSV file path:");
                csvPath = scanner.nextLine();
                break;
            case "n":
                break;

        }
        return csvPath;
    }
}
