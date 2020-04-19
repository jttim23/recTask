package pl.jedro.jsontask.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CsvService extends AbstractService {
    Map<String, Long> errors = new HashMap<>();

    public CsvService() {
    }

    public Map<String, Long> getErrors() {
        return errors;
    }

    public Map<String, BigDecimal> calculate(File file) throws Exception {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        if (file.length() != 0) {
            CSVParser csvParser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().withDelimiter(';').withQuote('"')
                    .withIgnoreHeaderCase().withFirstRecordAsHeader().withIgnoreSurroundingSpaces()
            );
            for (CSVRecord record : csvParser) {
                String parsedJob = record.get("job").trim();
                String parsedSalary = record.get("salary").trim();
                BigDecimal unifiedSalary;

                try {
                    unifiedSalary = unifyDecimalFormat(deleteDoubleQuotas(parsedSalary));
                } catch (NumberFormatException e) {

                    errors.put(e.toString(), record.getRecordNumber());
                    continue;
                }
                try {
                    handleMapAdding(deleteDoubleQuotas(parsedJob), unifiedSalary, map);
                } catch (RuntimeException e) {
                    errors.put(e.toString(), record.getRecordNumber());
                }
            }
        } else throw new Exception();
        return map;
    }

}
