package pl.jedro.jsontask.utils.importers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.io.IOException;

public class CsvImporter implements Importer {
    @Override
    public EmployeeWrapper getEmployeesFromFile(File file) throws RuntimeException, IOException {
        EmployeeWrapper employees = new EmployeeWrapper();
        if (file.length() != 0) {
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.TRIM_SPACES);
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
            MappingIterator<Employee> it = mapper.readerFor(Employee.class).with(bootstrapSchema)
                    .readValues(file);
            employees.setEmployees(it.readAll());
            return employees;
        } else {
            throw new RuntimeException();
        }

    }
}
