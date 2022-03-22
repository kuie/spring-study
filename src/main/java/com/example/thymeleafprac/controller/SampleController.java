package com.example.thymeleafprac.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.thymeleafprac.dto.SampleDTO;

@Controller
@RequestMapping("/sample")
public class SampleController {
	
	@GetMapping("/ex1")
	public String ex1() {
		return "sample/ex1";
		
	}
	@GetMapping({"/ex2,","/exLink"}) //원래 ex2만 있었음 4번 하다보니 이름 바꿈 
	//("/{}") //한꺼번에 여러개 패스 설정 할 수 있다는 뜻
	public void exModel(Model m) {
		List<SampleDTO> list = IntStream.rangeClosed(1, 20)
				.mapToObj(i-> {
					SampleDTO dto = 
						new SampleDTO(i,"First..."+i,"Last..."+i, LocalDateTime.now() );
				return dto;
				}).collect(Collectors.toList());
		m.addAttribute("list",list);
		// return "sample/ex2"; void 타입이라 string이면 하나만 불러와서 리턴값 필요 
	}
	

	@GetMapping("/exInline")
	public String exInline(RedirectAttributes redirectAttributes) {
		//화면 재호출
		SampleDTO dto = new SampleDTO (100L, "First"+100,"Last"+100,LocalDateTime.now());
		redirectAttributes.addFlashAttribute("result","success");
		redirectAttributes.addFlashAttribute("dto",dto);	
	
		return "redirect:/sample/ex3";	
	}
	@GetMapping("/ex3")
	public void ex3(){
		System.out.println("ex3");}

	
	@GetMapping({"/exLayout1","/exLayout2","/exTemplate","/exSidebar"})
	public void exLayout1() {
		System.out.println("exLayout....호출 완");
	}
	
}//main
