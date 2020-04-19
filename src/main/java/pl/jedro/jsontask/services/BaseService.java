package pl.jedro.jsontask.services;

import pl.jedro.jsontask.model.Employee;
import pl.jedro.jsontask.model.EmployeeWrapper;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public  class BaseService {
    private List<String> errors=new ArrayList<>();
    public BaseService() {
    }
    public List<String> getErrorList(){
        return errors;
    }
    public Map<String, BigDecimal> calculate(EmployeeWrapper list) {
        //to clear error list for every method call
        errors=new ArrayList<>();
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
//    public static String deleteDoubleQuotas(String strToClean) {
//        return strToClean.replace("\"", "");
//    }
    private static BigDecimal unifyDecimalFormat(String decToFix) throws NumberFormatException {
        return new BigDecimal(decToFix.replace(',', '.'));
    }

//    public static void sumUp(String parsedJob, BigDecimal parsedSalary, Map<String, BigDecimal> map) {
//
//        if (parsedJob.length() != 0) {
//            if (map.containsKey(parsedJob)) {
//                BigDecimal existingSalary = map.get(parsedJob);
//                map.put(parsedJob, existingSalary.add(parsedSalary));
//            } else
//                map.put(parsedJob, parsedSalary);
//        } else throw new RuntimeException();
//    }
    public  void sumUp(Employee employee, Map<String, BigDecimal> map) throws RuntimeException {

        String parsedJob = employee.getJob().trim();
        BigDecimal unifiedSalary = unifyDecimalFormat(employee.getSalary().trim());
        if (parsedJob.length() != 0) {
            if (map.containsKey(parsedJob)) {
                BigDecimal existingSalary = map.get(parsedJob);
                map.put(parsedJob, existingSalary.add(unifiedSalary));
            } else
                map.put(parsedJob, unifiedSalary);
        } else {throw new RuntimeException();}
    }


}
