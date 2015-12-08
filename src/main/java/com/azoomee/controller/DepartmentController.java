package com.azoomee.controller;

import com.azoomee.model.Department;
import com.azoomee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
public class DepartmentController {

	private final DepartmentService departmentService;

	@Inject
	public DepartmentController(final DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public Department createDepartment(@RequestBody @Valid final Department department){
		return departmentService.save(department);
	}
}
