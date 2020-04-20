package pl.jedro.jsontask.services;

import pl.jedro.jsontask.utils.Terminal;
import pl.jedro.jsontask.utils.importers.Importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


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
                calculate(importer.getEmployeesFromFile(new File(path)));
            } catch (FileNotFoundException e) {
                System.out.println("Could not find a " + fileFormat + " file!!!\n");
            } catch (IOException e) {
                System.out.println("Could not read a " + fileFormat + " file or empty!!!\n");
            }
        }
    }


    public String getFileFormat() {
        return fileFormat;
    }


}
