package pl.jedro.jsontask.utils.importers;

import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.io.IOException;

public interface Importer {
    EmployeeWrapper getEmployeesFromFile(File file) throws IOException;

    String getFileType();
}
