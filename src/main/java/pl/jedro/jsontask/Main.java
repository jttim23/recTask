package pl.jedro.jsontask;

import pl.jedro.jsontask.services.AbstractService;
import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> paths = getPathsFromTerminal();
        if (!paths.isEmpty()) {
            Optional<String> jsonPath = Optional.ofNullable(paths.get(0));
            Optional<String> csvPath = Optional.ofNullable(paths.get(1));
            JsonService jsonService = new JsonService();
            CsvService csvService = new CsvService();
            if (jsonPath.isPresent()) {
                try {
                    System.out.println("JSON Service:\n");
                    AbstractService.printResult(jsonService.calculate(new File(jsonPath.get())));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Could not find a JSON file or file is empty");
                }
            } else if (csvPath.isPresent()) {
                try {
                    System.out.println("CSV Service:\n");
                    AbstractService.printResult(csvService.calculate(new File(csvPath.get())));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Could not find a CSV file or file is empty");
                }
            }
        }
    }

    private static List<String> getPathsFromTerminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to input JSON file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();
        String jsonPath;
        String csvPath;
        List<String> paths = new ArrayList<>();

        while (!flag.equals("n") && !flag.equals("y")) {
            System.out.println("Press Y if yes, or N if no:\n" + "Y/N:");
            flag = scanner.nextLine();
        }
        switch (flag) {
            case "y":
                System.out.println("Please provide JSON file path:");
                jsonPath = scanner.nextLine();
                csvPath = getCSVPathFromTerminal();
                paths = Arrays.asList(jsonPath, csvPath);
                break;
            case "n":
                csvPath = getCSVPathFromTerminal();
                paths = Arrays.asList(null, csvPath);
                break;
        }
        return paths;
    }

    private static String getCSVPathFromTerminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to input CSV file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();
        String csvPath =null;

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

