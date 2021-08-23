package bigg.service;

import bigg.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService extends IGeneralService<Employee> {
    Iterable<Employee> findAllByAge();
}
