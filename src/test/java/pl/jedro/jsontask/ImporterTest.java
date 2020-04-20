package pl.jedro.jsontask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.importers.Importer;
import pl.jedro.jsontask.utils.importers.JsonImporter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;

class ImporterTest {


    @Test
    void testCSVImporter() throws Exception {

        Path resourceDirectory = Paths.get("src", "test", "resources");
        String basePath = resourceDirectory.toFile().getAbsolutePath();
        File file = new File(basePath+"\\employees.csv");
        Importer importer = new CsvImporter();
        EmployeeWrapper employees = importer.getEmployeesFromFile(file);
        assertEquals(5,employees.getEmployees().size());
    }
    @Test
    void testJsonImporter() throws Exception {

        Path resourceDirectory = Paths.get("src", "test", "resources");
        String basePath = resourceDirectory.toFile().getAbsolutePath();
        File file = new File(basePath+"\\employees.json");
        Importer importer = new JsonImporter();
        EmployeeWrapper employees = importer.getEmployeesFromFile(file);
        assertEquals(3,employees.getEmployees().size());
    }
    @Test
    void throwsExceptionWhenJsonNotFound() {
        //given
        Importer importer = new JsonImporter();
        File input = new File("wrongPath");
        //then
        Assertions.assertThrows(FileNotFoundException.class, () -> {
           importer.getEmployeesFromFile(input);
        });

    }
    @Test
    void throwsExceptionWhenJsonEmpty() {
        //given
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String basePath = resourceDirectory.toFile().getAbsolutePath();
        Importer importer = new JsonImporter();
        File input = new File(basePath+"\\employeesEmpty.json");
        //then
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            importer.getEmployeesFromFile(input);
        });

    }
    @Test
    void throwsExceptionWhenCSVEmpty() {
        //given
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String basePath = resourceDirectory.toFile().getAbsolutePath();
        Importer importer = new CsvImporter();
        File input = new File(basePath+"\\employeesEmpty.csv");
        //then
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            importer.getEmployeesFromFile(input);
        });

    }


    @Test
    void throwsExceptionWhenCSVNotFound() {
        //given
        Importer importer = new CsvImporter();
        File input = new File("wrongPath");
        //then
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            importer.getEmployeesFromFile(input);
        });

    }
}