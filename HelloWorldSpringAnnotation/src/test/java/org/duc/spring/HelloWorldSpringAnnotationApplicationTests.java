package org.duc.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.duc.spring.lang.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldSpringAnnotationApplicationTests {

	@Autowired
	private Language language;
	@Test
	public void testGreeting() {
		assertThat(language.getGreeting()).contains("Hello");
	}
	@Test
	public void testBye() {
		assertThat(language.getBye()).contains("Bye bye");
	}
}
