package com.example.service;

import com.example.model.Emp;
import com.example.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {


    @Autowired
    private EmpRepository empRepository;

    public Emp savingEmp(Emp emp)
    {
        Emp save = this.empRepository.save(emp);
        return save;
    }
    public List<Emp> getAllEmp()
    {
        return  this.empRepository.findAll();
    }
}
