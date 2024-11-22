package com.example.controller;

import com.example.model.Emp;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/emp")
public class EmpController {

    // Create a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    /**
     * Endpoint to create a new employee.
     *
     * @param emp the employee to save
     * @return ResponseEntity with saved employee data
     */
    @PostMapping("/save-emp")
    public ResponseEntity<?> create(@RequestBody Emp emp) {
        logger.info("Received request to save employee: {}", emp); // Log the received employee object
        try {
            Emp savedEmp = this.empService.savingEmp(emp);  // Save the employee using the service
            logger.info("Employee saved successfully with ID: {}", savedEmp.getId());  // Log the successful save
            return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while saving employee: {}", emp, e);  // Log any error that occurs
            return new ResponseEntity<>("Error saving employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to get all employees.
     *
     * @return ResponseEntity with the list of all employees
     */
    @GetMapping
    public ResponseEntity<?> getAllEmp() {
        logger.info("Received request to fetch all employees.");  // Log the request for fetching all employees
        try {
            List<Emp> allEmp = this.empService.getAllEmp();  // Get all employees from the service
            logger.info("Retrieved {} employees.", allEmp.size());  // Log the number of employees retrieved
            return new ResponseEntity<>(allEmp, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all employees.", e);  // Log any error that occurs
            return new ResponseEntity<>("Error fetching employees", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to get employee by ID.
     *
     * @param id the ID of the employee to fetch
     * @return ResponseEntity with employee data or an error message if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        logger.info("Received request to fetch employee with id: {}", id);  // Log the request for fetching by ID
        try {
            Optional<Emp> emp = this.empService.gettingEmpById(id);  // Fetch employee from the service
            if (emp.isPresent()) {
                logger.info("Employee with id {} found.", id);  // Log if employee is found
                return new ResponseEntity<>(emp.get(), HttpStatus.FOUND); // Return employee data
            } else {
                logger.warn("Employee with id {} not found.", id);  // Log if employee is not found
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND); // Return 404 if not found
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching employee with id {}", id, e);  // Log any error that occurs
            return new ResponseEntity<>("Error fetching employee", HttpStatus.INTERNAL_SERVER_ERROR); // Return error message
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatingEmp(@PathVariable int id, @RequestBody Emp emp) {
        Emp emp1 = this.empService.updateEmpById(id, emp);
        return new ResponseEntity<>(emp1, HttpStatus.ACCEPTED);
    }
}
