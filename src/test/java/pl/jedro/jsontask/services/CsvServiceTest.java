package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CsvServiceTest {

    Path resourceDirectory = Paths.get("src", "test", "resources");
    String basePath = resourceDirectory.toFile().getAbsolutePath();


    @Test
    void fileCalculateTest() throws Exception {
        //given
        File input = new File(basePath + "\\employees.csv");
        CsvService service = new CsvService();
        //when
        Map<String, BigDecimal> map = service.calculate(input);
        //then
        assertEquals(3, map.size());

    }

    @Test
    void throwsExceptionWhenFileNotFound() {
        //given
        File input = new File("wrongPath");
        CsvService service = new CsvService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(input);
        });

    }

    @Test
    void throwsExceptionWhenFileEmpty() {
        //given
        File input = new File(basePath + "\\employeesEmpty.csv");
        CsvService service = new CsvService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(input);
        });
    }

    @Test
    void corruptedFileCalculationTest() throws Exception {
        //given
        File input = new File(basePath + "\\employeesCorrupted.csv");
        CsvService service = new CsvService();
        //when
        Map<String, BigDecimal> map = service.calculate(input);
        //then
        System.out.println(map.toString());
        assertEquals(2, map.size());
        assertEquals(new BigDecimal("2700"), map.get("Teacher"));
        assertEquals(new BigDecimal("13460.45"), map.get("Janitor"));

    }
}

