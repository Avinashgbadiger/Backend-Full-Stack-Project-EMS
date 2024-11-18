package com.example.repository;

import com.example.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp,Integer> {
}
