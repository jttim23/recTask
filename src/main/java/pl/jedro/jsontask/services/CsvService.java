package pl.jedro.jsontask.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class CsvService extends AbstractService {

    public CsvService() {
    }

    public Map<String, BigDecimal> calculate(File file) throws Exception {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if (file.length() != 0) {
            CSVParser csvParser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().withDelimiter(';')
                    .withIgnoreHeaderCase().withFirstRecordAsHeader().withTrim(true)
            );
            for (CSVRecord record : csvParser) {
                String parsedJob = record.get("job");
                String parsedSalary = record.get("salary");
                BigDecimal unifiedSalary = unifyDecimalFormat(deleteDoubleQuotas(parsedSalary));
                handleMap(deleteDoubleQuotas(parsedJob), unifiedSalary, map);
            }
        } else throw new Exception();
        return map;
    }

}
