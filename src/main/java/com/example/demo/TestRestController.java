package com.example.demo;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //무조건 변수 값만 내놓음, responsebody 생략가능 
@RequestMapping("/api") //api 모드 
public class TestRestController {
	@GetMapping("/hello")
	public String hello() {
		return "hello";} //html 코드 이름 반환 
		//서블릿 객체가 만들어지는거임 


	@GetMapping("/hello2")
	public String hello2(Model model) {
		model.addAttribute("name","홍길동");
		return "hello2";
		} 
	
	@GetMapping("/hello3")
	//정보자체를 어플리케이션에 보내는거임 
	public String hello3(@RequestParam(value="msg") String msg) {
		return msg;
		} //변수를 리턴 
	/*http://localhost:8080/test/hello3?msg="내용 삽입"*/
	
	@GetMapping("/hello4")
	public Student hello4(Model m) {
		Student student = new Student();
		student.setEng(90);student.setKor(93); student.setGrade("A-");
		student.setMath(80); student.setName("김한국");
		return student;
		
		} //변수를 리턴 
	static class Student {
		private String name;
		private int kor;
		private int math;
		private int eng;
		private String grade;
		
		public String getName() {			return name;		}
		public void setName(String name) {		this.name = name;		}
		public int getKor() {		return kor;		}
		public void setKor(int kor) {		this.kor = kor;		}
		public int getMath() {		return math;		}
		public void setMath(int math) {		this.math = math;	}
		public int getEng() {		return eng;		}
		public void setEng(int eng) {	this.eng = eng;		}
		public String getGrade() {	return grade;		}
		public void setGrade(String grade) {this.grade = grade;	}
		
	}
	
	@GetMapping("/hello5/{name}")
	public String hello5(@PathVariable String name, Model m) {
		// path가 변수임 
		return "hello2";
		} 
	
	@GetMapping("/hello6")
	public HashMap<String, String> hello6(){
		HashMap<String, String> map = new HashMap<>(){{
			put("이름","박자바");
			put("나이","31");
			put("거주지","서울");
		}};
		return map;
		} 
	
} //TestWebController