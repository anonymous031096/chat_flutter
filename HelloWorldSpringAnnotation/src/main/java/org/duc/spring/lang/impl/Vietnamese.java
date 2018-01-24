package org.duc.spring.lang.impl;

import org.duc.spring.lang.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("vietnam")
public class Vietnamese implements Language {
	@Override
	public String getGreeting() {
		return "Xin chao";
	}

	@Override
	public String getBye() {
		return "Tam biet";
	}
}
