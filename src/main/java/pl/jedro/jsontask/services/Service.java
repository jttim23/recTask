package pl.jedro.jsontask.services;

import pl.jedro.jsontask.utils.Terminal;
import pl.jedro.jsontask.utils.importers.Importer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class Service extends BaseService {
    private Importer importer;
    private Terminal terminal;
    private String fileFormat;

    public Service(Importer importer, Terminal terminal) {
        this.importer = importer;
        this.terminal = terminal;
    }

    public void run() {
        fileFormat = importer.getFileType();
        String path = terminal.getPathFromConsole(fileFormat);
        if (path.length() != 0) {
            try {
                Map<String, BigDecimal> results = calculate(importer.getEmployeesFromFile(new File(path)));
            } catch (RuntimeException | IOException e) {
                System.out.println("Could not find a " + fileFormat + " file.");
            }
        }
    }


    public String getFileFormat() {
        return fileFormat;
    }


}
