package pl.jedro.jsontask.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;

public class Importer {
    public Importer() {
    }

    public EmployeeWrapper getPeopleFromCSVFile(File file) throws Exception {
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
            throw new Exception();
        }

    }

    public EmployeeWrapper getPeopleFromJSONFile(File file) throws Exception {
        EmployeeWrapper employees;

        if (file.length() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(file, EmployeeWrapper.class);
        } else {
            throw new Exception();
        }
        return employees;
    }
}
