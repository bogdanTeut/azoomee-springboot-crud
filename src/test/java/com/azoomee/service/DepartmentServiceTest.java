package com.azoomee.service;

import com.azoomee.model.Department;
import com.azoomee.repository.DepartmentRepository;
import com.azoomee.service.exception.DepartmentAlreadyExistsException;
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
	public void givenAValidDepartment_whenSavingIt_ItShouldSaveAndReturnIt(){
		final Department department = new Department();
		givenDepartmentRepositoryStub(department);

		final Department returnedDepartment = departmentService.save(department);

		verifyResults(department, returnedDepartment);
	}

	@Test(expectedExceptions = DepartmentAlreadyExistsException.class, expectedExceptionsMessageRegExp = "There already exists a user with \\[id=1\\]")
	public void givenExistingDepartmentWithTheSameId_whenSavingIt_itShouldThrowDepartmentAlreadyExistsException(){
		final Department department = givenDepartment();

		givenDepartmentRepositoryStubToReturnExistingUser(department);

		departmentService.save(department);
	}

	private Department givenDepartment()
	{
		final Department department = new Department();
		department.setDepartmentID("1");
		return department;
	}

	private void verifyResults(final Department department, final Department returnedDepartment)
	{
		verify(departmentRepository, times(1)).save(department);
		assertEquals(returnedDepartment, department);
	}

	private void givenDepartmentRepositoryStubToReturnExistingUser(final Department department)
	{
		when(departmentRepository.findOne(department.getDepartmentID())).thenReturn(department);
	}

	private void givenDepartmentRepositoryStub(final Department department)
	{
		when(departmentRepository.save(department)).thenReturn(department);
	}
}
