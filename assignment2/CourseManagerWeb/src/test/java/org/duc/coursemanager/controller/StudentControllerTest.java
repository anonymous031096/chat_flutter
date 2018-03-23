package org.duc.coursemanager.controller;

import org.duc.coursemanager.entity.Student;
import org.duc.coursemanager.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	StudentService studentService;
	
	@Test
	public void getById() throws Exception{
		Student student = new Student();
		Mockito.when(studentService.getOne(Mockito.anyInt())).thenReturn(student);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/duc/student/2").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:2,firstName:dsfsdfdfs,lastName:sdfsdf,gender:0,address:dsfsdffsd}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
