package pl.jedro.jsontask.old;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.services.BaseService;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class CsvService extends BaseService {

    private List<String> errors=new ArrayList<>();
    public CsvService() {
    }
    public List<String> getErrorList(){
        return errors;
    }


    public Map<String, BigDecimal> calculate(EmployeeWrapper list) {
        Map<String, BigDecimal> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Employee employee : list.getEmployees()){
            try {
                sumUp(employee,map);
            } catch (RuntimeException e){
                errors.add(employee.toString());
            }
        }
        return map;
    }
}
