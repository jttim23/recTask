package pl.jedro.jsontask;

import pl.jedro.jsontask.services.AbstractService;
import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;

import java.io.File;
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
                AbstractService.printResult(jsonService.calculate(new File(path)));
            } catch (Exception e) {
                System.out.println("Could not find a JSON file.");
            }
            return "";
        });
        Optional.ofNullable(paths.get(1)).map(path -> {
            try {
                System.out.println("CSV Service:\n");
                AbstractService.printResult(csvService.calculate(new File(path)));

            } catch (Exception e) {

                System.out.println("Could not find a CSV file.");
            }
            return "";
        });
    }

    private  List<String> getPathsFromConsole() {
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
                paths = Arrays.asList("", csvPath);
                break;
        }
    return paths;
    }

    private  String getCSVPathFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to input CSV file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();
        String csvPath= "";

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
