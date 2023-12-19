package com.spring.project.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Employee {
    private int id;
    private String name;
    private String department;
    private String salary;
}
