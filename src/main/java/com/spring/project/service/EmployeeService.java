package com.spring.project.service;

import com.spring.project.entity.Employee;
import com.spring.project.fileoperation.FileDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final String FILE_PATH = "src/main/resources/data/employee.data.json";
    int id = 1;
    @Autowired
    FileDataBase fileDataManager;
    @Autowired
    Employee employee;
    ArrayList<Employee> list = new ArrayList<>();

    public String saveData(Employee employee) {
        employee.setId(id++);
        list.add(employee);
        fileDataManager.saveListToFile(list, FILE_PATH);
        return "data saved successfully";
    }

    public List<Employee> getEmployeeList() {
        return fileDataManager.getAllEmployee(FILE_PATH);
    }


    public Employee getEmployeeById( int employeeId) {
        return fileDataManager.getById(FILE_PATH,employeeId);
    }
    public void deleteById(int employeeId)
    {
        fileDataManager.deleteById( employeeId, FILE_PATH);
    }


    public String updateEmployeeDetail(Employee employee,int employeeId) {
        return fileDataManager.updateEmployeeDetail(FILE_PATH,employee,employeeId);

    }
}
