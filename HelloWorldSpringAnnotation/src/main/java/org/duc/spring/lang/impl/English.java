package org.duc.spring.lang.impl;

import org.duc.spring.lang.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("english")
public class English implements Language {

	@Override
	public String getGreeting() {
		return "Hello";
	}

	@Override
	public String getBye() {
		return "Bye bye";
	}
}
