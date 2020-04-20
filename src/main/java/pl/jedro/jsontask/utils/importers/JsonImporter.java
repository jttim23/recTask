package pl.jedro.jsontask.utils.importers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class JsonImporter implements Importer {
    private static final String FILE_TYPE = "JSON";

    public String getFileType() {
        return FILE_TYPE;
    }

    @Override
    public EmployeeWrapper getEmployeesFromFile(File file) throws IOException {

        if (Optional.ofNullable(file).isEmpty()) {
            throw new FileNotFoundException();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, EmployeeWrapper.class);
    }
}
