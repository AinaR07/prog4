package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().toList();
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setDateOfBirth(employeeModel.getDateOfBirth());
        employee.setFirstName(employeeModel.getFirstName());
        employee.setLastName(employeeModel.getLastName());

        if (employeeModel.getImageFile() != null && !employeeModel.getImageFile().isEmpty()) {
            try {
                byte[] imageBytes = employeeModel.getImageFile().getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                employee.setImage(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return employeeRepository.save(employee);
    }
}
