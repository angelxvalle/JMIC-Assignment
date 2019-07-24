package com.jmic.portal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloWorld {
	@RequestMapping("/")
	public String index() {
		return "You are communicating to the back!";
	}

}
