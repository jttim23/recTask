package pl.jedro.jsontask;


import pl.jedro.jsontask.services.Service;
import pl.jedro.jsontask.utils.Printer;
import pl.jedro.jsontask.utils.Terminal;
import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.importers.JsonImporter;


public class Main {
    public static void main(String[] args) {
        Service jsonService = new Service(new JsonImporter(), new Terminal());
        Service csvService = new Service(new CsvImporter(), new Terminal());
        jsonService.run();
        csvService.run();
        new Printer().print(jsonService.getResults(), jsonService.getErrorList(), jsonService.getFileFormat());
        new Printer().print(csvService.getResults(), csvService.getErrorList(), csvService.getFileFormat());

    }

}

