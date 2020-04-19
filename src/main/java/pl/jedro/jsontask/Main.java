package pl.jedro.jsontask;

import pl.jedro.jsontask.services.CsvService;
import pl.jedro.jsontask.services.JsonService;
import pl.jedro.jsontask.utils.*;
import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.importers.JsonImporter;

public class Main {

    public static void main(String[] args) {

        JsonService jsonService = new JsonService(new JsonImporter(), new Printer(), new Terminal());
        jsonService.run();
        CsvService csvService = new CsvService(new CsvImporter(), new Printer(), new Terminal());
        csvService.run();

    }

}

