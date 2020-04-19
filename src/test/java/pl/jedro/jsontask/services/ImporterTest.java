package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Test;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.importers.Importer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

class ImporterTest {


    @Test
    void test() throws Exception {

        Path resourceDirectory = Paths.get("src", "test", "resources");
        String basePath = resourceDirectory.toFile().getAbsolutePath();
        File file = new File("C:\\Users\\jedro\\Downloads\\rec\\recTask\\src\\test\\resources\\employees.csv");
        Importer importer = new CsvImporter();
        EmployeeWrapper people = new EmployeeWrapper();
        people = importer.getEmployeesFromFile(file);
        for (Employee employee : people.getEmployees()) {
            System.out.println(employee.toString());
        }
    }
}