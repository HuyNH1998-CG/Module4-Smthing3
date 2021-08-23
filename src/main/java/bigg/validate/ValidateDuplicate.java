package bigg.validate;

import bigg.model.Employee;
import bigg.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValidateDuplicate implements Validator {
    @Autowired
    IEmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Iterable<Employee> list = employeeService.findAll();
        Employee employee = (Employee) target;
        for(Employee e : list){
            if(e.getEmployeeCode().equals(employee.getEmployeeCode())){
                errors.rejectValue("employeeCode","duplicated-empCode");
                return;
            }
        }
    }
}
