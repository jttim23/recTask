package pl.jedro.jsontask.utils.importers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonImporter implements Importer {
    private String fileType = "JSON";

    public String getFileType() {
        return fileType;
    }

    @Override
    public EmployeeWrapper getEmployeesFromFile(File file) throws IOException {
        EmployeeWrapper employees;
        if (file.length() == 0) {
            throw new FileNotFoundException();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(file, EmployeeWrapper.class);
            return employees;
        }
    }
}
