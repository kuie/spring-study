package com.example.spring_study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ViewTestController {
	@GetMapping("/")
	public String viewTest() {
		return "news/newsList";
	}
}
