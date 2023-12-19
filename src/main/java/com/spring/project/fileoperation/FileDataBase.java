package com.spring.project.fileoperation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.entity.Employee;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Component
public class FileDataBase {

        ObjectMapper objectMapper = new ObjectMapper();

        public void saveListToFile(List<Employee> list, String filePath) {

            try {
                // Convert the list to a JSON string
//            String jsonString = objectMapper.writeValueAsString(list);

                // Save the JSON data to a file
                File file = new File(filePath);
                objectMapper.writeValue(file, list);

                System.out.println("Data saved to " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public List<Employee> getAllEmployee(String filePath) {
            try {
                // Read the JSON data from the file and convert it to a List<Employee>
                List<Employee> employeeList = objectMapper.readValue(new File(filePath), new TypeReference<List<Employee>>() {
                });

                System.out.println("Data loaded from " + filePath);
                return employeeList;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public Employee getById(String filePath, int employeeId) {
            List<Employee> employeeList = getAllEmployee(filePath);
            for(Employee employee :employeeList)
            {
                if(employee.getId()==employeeId)
                {
                    return employee;
                }
            }
            return null;
        }

        public void deleteById( int employeeId, String filepath) {
            List<Employee> employeeList =getAllEmployee(filepath);
            Employee employee =getById(filepath,employeeId);
            if (employee != null) {
                // Remove the employee from the list
                employeeList.remove(employee);

                // Save the updated list back to the file
                saveListToFile(employeeList, filepath);
                System.out.println("Employee with ID " + employeeId + " deleted successfully.");
            } else {
                System.out.println("Employee with ID " + employeeId + " not found.");
            }

        }

        public String updateEmployeeDetail(String filepath,Employee employee, int employeeId) {
            deleteById(employeeId,filepath);
            List<Employee> employeeList = getAllEmployee(filepath);
            employee.setId(employeeId);
            employeeList.add(employee);
            saveListToFile(employeeList,filepath);
            return "employee details updated";
        }


}
