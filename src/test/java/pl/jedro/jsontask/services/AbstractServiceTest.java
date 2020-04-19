package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AbstractServiceTest {

    @Test
    void printEmptyMapResultsTest() {
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        AbstractService.printResult(new HashMap<>());
        verify(out).println(startsWith("Nothing to be calculated in file"));
    }

    @Test
    void printMapResultsTest() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("Test", new BigDecimal("12.12"));
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        AbstractService.printResult(map);
        verify(out).println(startsWith("Printing results:"));
    }

    @Test
    void deleteQuotasTest() {
        String wrongStr = "\"test.1234,1235:/\"";
        assertEquals("test.1234,1235:/", AbstractService.deleteDoubleQuotas(wrongStr));
    }

    @Test
    void unifyDecimalTest() {
        String wrongDec = "123,12";
        assertEquals(new BigDecimal("123.12"), AbstractService.unifyDecimalFormat(wrongDec));
    }

    @Test
    void handleMapTest() {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        BigDecimal decimal = new BigDecimal("11.11");
        map.put("Test", new BigDecimal("12.12"));
        AbstractService.handleMapAdding("test", decimal, map);
        assertEquals(decimal.add(new BigDecimal("12.12")), map.get("Test"));
    }

    @Test
    void handleEmptyMapTest() {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        BigDecimal decimal = new BigDecimal("11.11");
        AbstractService.handleMapAdding("test", decimal, map);
        assertEquals(decimal, map.get("Test"));
    }
}