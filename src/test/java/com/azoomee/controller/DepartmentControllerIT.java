package com.azoomee.controller;

import com.azoomee.Application;
import com.azoomee.TestsUtil;
import com.azoomee.model.Department;
import com.azoomee.repository.DepartmentRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DepartmentControllerIT{

	@Inject
	private DepartmentRepository departmentRepository;

	@Value("${local.server.port}")
	private int port;

	@Before
	public void setUp(){
		departmentRepository.deleteAll();
		RestAssured.port = port;
	}

	@Test
	public void givenAValidJsonDepartmentRequest_whenAPostIsBeingPerformed_thenItShouldBeStoredAndReturnA200(){
		final Department department = TestsUtil.givenDepartment();
		given()
				.contentType(ContentType.JSON)
				.body(department)
		.when()
				.post("/department")
		.then()
				.statusCode(HttpStatus.OK.value())
				.contentType(ContentType.JSON)
				.body("departmentID", Matchers.is("1"))
				.body("name", Matchers.is("Sales"));

		verifyDB(1, department);

	}

	@Test
	public void givenAnNonJsonDepartmentRequest_whenAPostIsBeingPerformed_thenItShouldReturnA415(){
		given()
				.contentType(ContentType.XML)
				.body("{\"departmentID\":\"1\", \"name\":\"Sales\"}")
		.when()
				.post("/department")
		.then()
				.statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.body(Matchers.is("Request was not in a json format"));
		verifyDB(0);
	}

	//more negative scenarios to be added. For the time being only checking if "name" field is missing.
	@Test
	public void givenAValidInvalidJsonDepartmentRequest_whenAPostIsBeingPerformed_thenItShouldReturnA400(){
		given()
				.contentType(ContentType.JSON)
				.body("{\"departmentID\":\"1\"}")
		.when()
				.post("/department")
		.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body(Matchers.is("[\"Field [name] validation failed due to reason: [may not be null]\"]"));
		verifyDB(0);
	}

	@Test
	public void givenAValidJsonDepartmentRequestWithAnIdAlreadyExisting_whenAPostIsBeingPerformed_thenItShouldReturnA409(){
		final Department department = TestsUtil.givenDepartment();
		departmentRepository.save(department);

		given()
				.contentType(ContentType.JSON)
				.body(department)
		.when()
				.post("/department")
		.then()
				.statusCode(HttpStatus.CONFLICT.value())
				.body(Matchers.is("There already exists a user with [id=1]"));
		verifyDB(1);
	}

	private void verifyDB(final int size, final Department... departments){
		final List<Department> returnedDepartments = departmentRepository.findAll();
		assertEquals(returnedDepartments.size(), size);
		Arrays.stream(departments)
				.forEach(department -> assertTrue(returnedDepartments.contains(department)));
	}

}
