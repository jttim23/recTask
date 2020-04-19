package pl.jedro.jsontask.utils;

import java.util.*;

public class Terminal {

    public Terminal() {
    }

    public List<String> getPathsFromConsole() {
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
        String csvPath = "";

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
