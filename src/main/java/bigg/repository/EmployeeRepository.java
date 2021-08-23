package bigg.repository;

import bigg.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    @Query(nativeQuery = true ,value = "SELECT * FROM fptemployee.employee order by employee.age")
    Iterable<Employee> findAllByAge();
}
