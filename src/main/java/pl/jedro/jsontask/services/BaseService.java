package pl.jedro.jsontask.services;

import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BaseService {
    private Map<String, BigDecimal> results = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private List<String> errors = new ArrayList<>();

    private static BigDecimal unifyDecimalFormat(String decToFix) throws NumberFormatException {
        return new BigDecimal(decToFix.replace(',', '.'));
    }

    public Map<String, BigDecimal> calculate(EmployeeWrapper list) {
        //to clear error list for every method call
        errors = new ArrayList<>();
        list.getEmployees().forEach(employee -> {
            try {
                sumUp(employee, results);
            } catch (RuntimeException e) {
                errors.add(employee.toString());
            }
        });
        return results;
    }

    private void sumUp(Employee employee, Map<String, BigDecimal> map) throws RuntimeException {
        String parsedJob = employee.getJob().trim();
        BigDecimal unifiedSalary = unifyDecimalFormat(employee.getSalary().trim());
        if (parsedJob.length() != 0) {
            if (map.containsKey(parsedJob)) {
                BigDecimal existingSalary = map.get(parsedJob);
                map.put(parsedJob, existingSalary.add(unifiedSalary));
            } else
                map.put(parsedJob, unifiedSalary);
        } else {
            throw new RuntimeException();
        }
    }

    public List<String> getErrorList() {
        return errors;
    }

    public Map<String, BigDecimal> getResults() {
        return results;
    }


}
