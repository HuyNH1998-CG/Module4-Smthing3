package bigg.controller;

import bigg.model.Branch;
import bigg.model.Employee;
import bigg.service.IBranchService;
import bigg.service.IEmployeeService;
import bigg.validate.ValidateDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IBranchService branchService;

    @Autowired
    private ValidateDuplicate validateDuplicate;

    @ModelAttribute("branches")
    public Iterable<Branch> branches() {
        return branchService.findAll();
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/home");
        Iterable<Employee> employees = employeeService.findAllByAge();
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, @RequestParam("branch") int id) {
        validateDuplicate.validate(employee,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/create");
        } else {
            employeeService.save(employee);
            ModelAndView modelAndView = new ModelAndView("redirect:/home");
            modelAndView.addObject("message", "Create Employee Success");
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        Employee employee = employeeService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/edit");
        } else {
            employeeService.save(employee);
            ModelAndView modelAndView = new ModelAndView("redirect:/home");
            modelAndView.addObject("message", "Edited An Employee");
            return modelAndView;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable int id) {
        Employee employee = employeeService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute Employee employee) {
        employeeService.delete(employee.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("message", "Employee Has Gone :(");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id){
        Employee employee = employeeService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }
}
