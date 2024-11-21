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
            Emp emp1 = this.empService.savingEmp(emp);  // Save the employee using the service
            logger.info("Employee saved successfully with ID: {}", emp1.getId());  // Log the successful save
            return new ResponseEntity<>(emp1, HttpStatus.CREATED);
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


    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable int id)
    {
        logger.info("Received request to fetch employee by his employee Id");

        try{
            Emp empId = this.empService.getEmpId(id);
            logger.info("Recived {} employee details ",empId.getId());
            return  new ResponseEntity<>(empId,HttpStatus.FOUND);
        }catch (Exception e)
        {
            logger.error("Error - Emp with EmpId : "+id+" did not found");
            return new ResponseEntity<>("Error unable to find Employee with empId "+id,HttpStatus.NOT_FOUND);
        }
    }
}
