package pl.jedro.jsontask.utils.importers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.utils.importers.Importer;

import java.io.File;
import java.io.IOException;

public class JsonImporter implements Importer {
    @Override
    public EmployeeWrapper getEmployeesFromFile(File file) throws RuntimeException, IOException {
        EmployeeWrapper employees;

        if (file.length() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(file, EmployeeWrapper.class);
        } else {
            throw new RuntimeException();
        }
        return employees;
    }
}
