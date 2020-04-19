package pl.jedro.jsontask.old;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;
import pl.jedro.jsontask.services.BaseService;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class JsonService extends BaseService {

  private   List<String> errors = new ArrayList<>();


    public List<String> getErrorList() {
        return errors;
    }

    public JsonService() {
    }

    public Map<String, BigDecimal> calculate(EmployeeWrapper list){
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
