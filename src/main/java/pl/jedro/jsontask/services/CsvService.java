package pl.jedro.jsontask.services;

import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.Printer;
import pl.jedro.jsontask.utils.Terminal;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class CsvService extends BaseService {
    private CsvImporter importer;
    private Printer printer;
    private Terminal terminal;
    private String fileFormat;

    public CsvService(CsvImporter importer, Printer printer, Terminal terminal) {
        this.fileFormat = "CSV";
        this.importer = importer;
        this.printer = printer;
        this.terminal = terminal;
    }

    public void run() {
        String path = terminal.getPathFromConsole(fileFormat);
        try {
            Map<String, BigDecimal> map = calculate(importer.getEmployeesFromFile(new File(path)));
            printer.print(getErrorList(), map);
        } catch (RuntimeException | IOException e) {
            System.out.println("Could not find a " + fileFormat + " file.");
        }
    }
}
