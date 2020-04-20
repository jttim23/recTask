package pl.jedro.jsontask;


import org.junit.jupiter.api.Test;
import pl.jedro.jsontask.services.BaseService;
import pl.jedro.jsontask.utils.importers.CsvImporter;
import pl.jedro.jsontask.utils.importers.Importer;
import pl.jedro.jsontask.utils.importers.JsonImporter;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseServiceTest {

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
    void corruptedJsonCalculationTest() throws Exception {
        //given
        Importer importer = new JsonImporter();
        File input = new File(basePath + "\\employeesCorrupted.json");
        BaseService service = new BaseService();
        //when
        Map<String, BigDecimal> result = service.calculate(importer.getEmployeesFromFile(input));
        //then
        assertEquals(3, result.size());
        assertEquals(new BigDecimal("23"), result.get("4"));
        assertEquals(new BigDecimal("100.123"), result.get("5"));
        assertEquals(service.getErrorList().size(), 2);
    }

    @Test
    void corruptedCSVCalculationTest() throws Exception {
        //given
        Importer importer = new CsvImporter();
        File input = new File(basePath + "\\employeesCorrupted.csv");
        BaseService service = new BaseService();
        //when
        Map<String, BigDecimal> result = service.calculate(importer.getEmployeesFromFile(input));
        //then
        assertEquals(2, result.size());
        assertEquals(new BigDecimal("13460.45"), result.get("Janitor"));
        assertEquals(new BigDecimal("2700"), result.get("Teacher"));
        assertEquals(service.getErrorList().size(), 3);
    }
}
