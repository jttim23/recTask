package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jedro.jsontask.utils.importers.Importer;
import pl.jedro.jsontask.utils.importers.JsonImporter;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {

    Path resourceDirectory = Paths.get("src", "test", "resources");
    String basePath = resourceDirectory.toFile().getAbsolutePath();


    @Test
    void fileCalculateTest() throws Exception {
        //given
        Importer importer = new JsonImporter();
        File input = new File(basePath + "\\employees.json");
        BaseService service = new BaseService();
        //when
        Map<String, BigDecimal> result = service.calculate(importer.getEmployeesFromFile(input));
        //then
        assertEquals(3, result.size());

    }

    @Test
    void throwsExceptionWhenFileNotFound() {
        //given
        Importer importer = new JsonImporter();
        File input = new File("wrongPath");
        BaseService service = new BaseService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(importer.getEmployeesFromFile(input));
        });

    }

    @Test
    void throwsExceptionWhenFileEmpty() {
        //given
        Importer importer = new JsonImporter();
        File input = new File(basePath + "\\employeesEmpty.json");
        BaseService service = new BaseService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(importer.getEmployeesFromFile(input));
        });
    }

    @Test
    void corruptedFileCalculationTest() throws Exception {
        //given
        Importer importer = new JsonImporter();
        File input = new File(basePath + "\\employeesCorrupted.json");
        BaseService service = new BaseService();
        //when
        Map<String, BigDecimal> result = service.calculate(importer.getEmployeesFromFile(input));
        //then
        assertEquals(3, result.size());
        System.out.println(result.toString());
        assertEquals(new BigDecimal("23"), result.get("4"));
        assertEquals(new BigDecimal("100.123"), result.get("5"));

    }
}
