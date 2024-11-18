package com.example.controller;

import com.example.model.Emp;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/save-emp")
    public ResponseEntity<?> create(@RequestBody Emp emp) {
        System.out.println("Received Emp: " + emp);  // Log the received object
        Emp emp1 = this.empService.savingEmp(emp);
        return new ResponseEntity<>(emp1, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> getAllEmp() {
        List<Emp> allEmp = this.empService.getAllEmp();
        return new ResponseEntity<>(allEmp, HttpStatus.FOUND);

    }
}
