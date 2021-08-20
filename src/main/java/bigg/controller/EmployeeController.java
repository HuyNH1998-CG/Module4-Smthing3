package bigg.controller;

import bigg.model.Branch;
import bigg.model.Employee;
import bigg.service.IBranchService;
import bigg.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IBranchService branchService;

    @ModelAttribute("branches")
    public Iterable<Branch> branches() {
        return branchService.findAll();
    }

    @GetMapping("/home")
    public ModelAndView home(@PageableDefault(sort = "age",direction = Sort.Direction.ASC)Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/home");
        Page<Employee> employees = employeeService.findAll(pageable);
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
    public ModelAndView create(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
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
