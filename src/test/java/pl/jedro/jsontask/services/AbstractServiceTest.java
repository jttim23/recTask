package pl.jedro.jsontask.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractServiceTest {


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
    void unifyDecimalThrowsFormatException() {
        String strToUnify = "123.12asd";

        Assertions.assertThrows(NumberFormatException.class, () -> {
            AbstractService.unifyDecimalFormat(strToUnify);
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            AbstractService.unifyDecimalFormat("");
        });
    }

    @Test
    void handleMapThrowsExceptionWhenEmptyJob() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            AbstractService.handleMapAdding("", new BigDecimal("12"), new HashMap<String, BigDecimal>());
        });

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