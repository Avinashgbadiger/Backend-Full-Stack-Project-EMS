package com.example.service;

import com.example.model.Emp;
import com.example.repository.EmpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    // Create a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpRepository empRepository;

    /**
     * Save an employee entity.
     *
     * @param emp the employee to save
     * @return saved employee entity
     */

    // Example of getting employee by ID in the service class
    public Optional<Emp> gettingEmpById(int id) {
        return empRepository.findById(id); // Assuming you're using Spring Data JPA
    }

    public Emp savingEmp(Emp emp) {
        logger.info("Attempting to save employee with ID: {}", emp.getId()); // Log the ID of the employee being saved
        try {
            Emp savedEmp = this.empRepository.save(emp);  // Save employee to the repository
            logger.info("Employee saved successfully with ID: {}", savedEmp.getId()); // Log success
            return savedEmp;
        } catch (Exception e) {
            logger.error("Error occurred while saving employee with ID: {}", emp.getId(), e);  // Log error if something goes wrong
            throw e;  // Re-throw the exception or handle accordingly
        }
    }

    /**
     * Get all employees.
     *
     * @return a list of all employees
     */
    public List<Emp> getAllEmp() {
        logger.info("Fetching all employees from the database.");  // Log that we are fetching all employees
        try {
            List<Emp> employees = this.empRepository.findAll();  // Retrieve all employees
            logger.info("Retrieved {} employees.", employees.size());  // Log the number of employees retrieved
            return employees;
        } catch (Exception e) {
            logger.error("Error occurred while fetching employees.", e);  // Log error if something goes wrong
            throw e;  // Re-throw the exception or handle accordingly
        }


    }

    public Emp updateEmpById(int id, Emp emp) {
        Optional<Emp> byId = this.empRepository.findById(id);
        if (byId.isPresent()) {
            emp.setId(id);
            return this.empRepository.save(emp);
        } else return this.empRepository.findById(id).get();
    }

    public Emp getEmpId(int id) {
        return this.empRepository.findById(id).get();
    }

    public void deleteEmpId(int id)
    {
        this.empRepository.deleteById(id);
    }
}
