package com.azoomee.controller;

import com.azoomee.controller.DepartmentController;
import com.azoomee.model.Department;
import com.azoomee.service.DepartmentService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DepartmentControllerTest{

	@Mock
	private DepartmentService departmentService;

	private DepartmentController departmentController;

	@BeforeTest
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		departmentController = new DepartmentController(departmentService);
	}

	@Test
	public void givenACorrectDepartmentRequest_whenDoingAPost_thenItShouldCreateTheResourceAndReturnIt(){
		final Department department = new Department();
		givenDepartmentServiceStub(department);

		final Department returnedDepartment = departmentController.createDepartment(department);

		verifyResults(department, returnedDepartment);
	}

	private void verifyResults(final Department department, final Department returnedDepartment)
	{
		verify(departmentService, times(1)).save(department);
		assertEquals(department, returnedDepartment);
	}

	private void givenDepartmentServiceStub(final Department department)
	{
		when(departmentService.save(department)).thenReturn(department);
	}

}
