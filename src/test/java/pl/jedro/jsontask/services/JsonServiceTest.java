package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonServiceTest {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String basePath = resourceDirectory.toFile().getAbsolutePath();


    @Test
    void fileCalculateTest() throws Exception {
        //given
        File input = new File(basePath + "\\employees.json");
        JsonService service = new JsonService();
        //when
        Map<String, BigDecimal> map = service.calculate(input);
        //then
        assertEquals(3, map.size());

    }

    @Test
    void throwsExceptionWhenFileNotFound() {
        //given
        File input = new File("wrongPath");
        JsonService service = new JsonService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(input);
        });

    }

    @Test
    void throwsExceptionWhenFileEmpty() {
        //given
        File input = new File(basePath + "\\employeesEmpty.json");
        JsonService service = new JsonService();
        //then
        Assertions.assertThrows(Exception.class, () -> {
            service.calculate(input);
        });
    }

    @Test
    void corruptedFileCalculationTest() throws Exception {
        //given
        File input = new File(basePath + "\\employeesCorrupted.json");
        JsonService service = new JsonService();
        //when
        Map<String, BigDecimal> map = service.calculate(input);
        //then
        assertEquals(3, map.size());
        System.out.println(map.toString());
        assertEquals(new BigDecimal("23"), map.get("4"));
        assertEquals(new BigDecimal("100.123"), map.get("5"));

    }
}