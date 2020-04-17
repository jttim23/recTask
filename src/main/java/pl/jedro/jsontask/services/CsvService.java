package pl.jedro.jsontask.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CsvService extends AbstractService {

    public CsvService() {
    }

    public Map<String, BigDecimal> calculate(File file) throws Exception {
        Map<String, BigDecimal> map = new HashMap<>();
        if (file.length() != 0) {
            CSVParser csvParser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().withDelimiter(';')
                    .withIgnoreHeaderCase().withFirstRecordAsHeader().withTrim(true)
            );
            for (CSVRecord record : csvParser) {
                String parsedJob = record.get("job");
                String parsedSalary = record.get("salary");
                handleMap(AbstractService.deleteDoubleQuotas(parsedJob), AbstractService.deleteDoubleQuotas(parsedSalary), map);
            }
        } else throw new Exception();
        return map;
    }

    private static void handleMap(String parsedJob, String parsedSalary, Map<String, BigDecimal> map) {
        BigDecimal unifiedSalary = AbstractService.unifyDecimalFormat(parsedSalary);
        if (map.containsKey(parsedJob)) {
            BigDecimal existingSalary = map.get(parsedJob);
            map.put(parsedJob, existingSalary.add(unifiedSalary));
        } else
            map.put(parsedJob, unifiedSalary);
    }


}
