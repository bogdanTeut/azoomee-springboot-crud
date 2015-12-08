package com.azoomee.service;

import com.azoomee.model.Department;
import com.azoomee.repository.DepartmentRepository;
import com.azoomee.service.impl.DepartmentServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class DepartmentServiceTest{

	@Mock
	private DepartmentRepository departmentRepository;

	private DepartmentService departmentService;

	@BeforeTest
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		departmentService = new DepartmentServiceImpl(departmentRepository);
	}

	@Test
	public void givenAValidUser_whenSavingIt_ItShouldSaveAndReturnIt(){
		final Department department = new Department();
		givenDepartmentRepositoryStub(department);

		final Department returnedDepartment = departmentService.save(department);

		verifyResults(department, returnedDepartment);
	}

	private void verifyResults(final Department department, final Department returnedDepartment)
	{
		verify(departmentRepository, times(1)).save(department);
		assertEquals(returnedDepartment, department);
	}

	private void givenDepartmentRepositoryStub(final Department department)
	{
		when(departmentRepository.save(department)).thenReturn(department);
	}
}
