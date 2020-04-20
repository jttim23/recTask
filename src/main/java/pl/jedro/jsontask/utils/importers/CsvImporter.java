package pl.jedro.jsontask.utils.importers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class CsvImporter implements Importer {
    private static final String FILE_TYPE = "CSV";

    public String getFileType() {
        return FILE_TYPE;
    }

    @Override
    public EmployeeWrapper getEmployeesFromFile(File file) throws IOException {
        if (Optional.ofNullable(file).isEmpty()) {
            throw new FileNotFoundException();
        } else {
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.TRIM_SPACES);
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
            MappingIterator<Employee> it = mapper.readerFor(Employee.class).with(bootstrapSchema)
                    .readValues(file);
            return new EmployeeWrapper(it.readAll());
        }

    }
}
