package com.azoomee.repository;

import com.azoomee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String>{
}
