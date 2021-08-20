package bigg.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-z0-9\\S]+$")
    private String employeeCode;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-z0-9\\S]+$")
    private String name;
    @Min(18)
    private int age;
    private int salary;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Employee() {
    }

    public Employee(String employeeCode, String name, int age, int salary, Branch branch) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
