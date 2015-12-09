package com.azoomee.controller;

import com.azoomee.model.Department;
import com.azoomee.service.DepartmentService;
import com.azoomee.service.exception.DepartmentAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@RequestMapping(value = "/department", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Department createDepartment(@RequestBody @Valid final Department department){
		return departmentService.save(department);
	}
}
