package pl.jedro.jsontask.utils;

import java.util.Scanner;

public class Terminal {

    public String getPathFromConsole(String fileFormat) {
        String path = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to input " + fileFormat + " file? Press Y if yes, or N if no:\n" + "Y/N:");
        String flag = scanner.nextLine();

        while (!flag.equals("n") && !flag.equals("y")) {
            System.out.println("Press Y if yes, or N if no:\n" + "Y/N:");
            flag = scanner.nextLine();
        }
        switch (flag) {
            case "y":
                System.out.println("Please provide " + fileFormat + " file path:");
                path = scanner.nextLine();
                break;
            case "n":
                break;
        }
        return path;
    }
}
