package com.azoomee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class DepartmentResourceTest {

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new DepartmentResource()).build();
    }

    @Test
    public void givenAListOfDepartments_whenDoingGetUsingDepartmentId_itShouldReturnIt() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/department/1")
//                            .accept(MediaType.APPLICATION_JSON)
 )
                            .andExpect(status().isOk())
                            .andExpect(content().string(equalTo("{\"departmentID\":\"1\", \"name\":\"Sales\"}")));

    }
}
