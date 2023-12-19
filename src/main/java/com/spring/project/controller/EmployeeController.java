package com.spring.project.controller;

import com.spring.project.entity.Employee;
import com.spring.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployeeDetail(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveData(employee), HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getEmployeeList() {
        List<Employee> employee = employeeService.getEmployeeList();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<String> updateEmployeeDetail(@RequestBody Employee employee,@PathVariable int employeeId)
    {
        employeeService.updateEmployeeDetail(employee,employeeId);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployeeDetail(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return new ResponseEntity<>("deleted data successfully",HttpStatus.OK);
    }
}
