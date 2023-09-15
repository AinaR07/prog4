package com.example.demo.controller;


import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employee", employees);
        return "employee";
    }


    @GetMapping("/add-employee")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeModel());
        return "add-employee";
    }
    @GetMapping("/fiche/{id}")
    public String getEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-details";
    }


    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute("employee") EmployeeModel employeeModel) throws IOException {
        if (!employeeModel.getImageFile().isEmpty()) {
            byte[] imageBytes = employeeModel.getImageFile().getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            employeeModel.setImage(Optional.of(base64Image)); // Utilisation d'un Optional pour l'image
        } else {
            employeeModel.setImage(Optional.empty()); // Image vide si aucun fichier n'est sélectionné
        }

        employeeService.addEmployee(employeeModel);
        return "redirect:/employee";
    }

}

