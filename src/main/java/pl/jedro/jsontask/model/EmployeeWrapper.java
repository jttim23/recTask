package pl.jedro.jsontask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmployeeWrapper {
    @JsonProperty("employees")
    private List<Employee> employees;

    public EmployeeWrapper() {
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
