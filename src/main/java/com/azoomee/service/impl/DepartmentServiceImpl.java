package com.azoomee.service.impl;

import com.azoomee.model.Department;
import com.azoomee.repository.DepartmentRepository;
import com.azoomee.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService
{

	private final DepartmentRepository departmentRepository;

	@Inject
	public DepartmentServiceImpl(final DepartmentRepository departmentRepository){
		this.departmentRepository = departmentRepository;
	}

	@Override
	@Transactional
	public Department save(final Department department) {
		return departmentRepository.save(department);
	}
}
