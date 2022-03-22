package com.example.spring_study;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news") //jsp 뷰가 나오도록 만드는 컨트롤러 
public class NewsWebController {
	final NewsDAO dao;
	
	/*@Value("${news.imgdir}")
	String fdir = "C:/KOSTA/workspaceappche/workspace/spring_study/src/main/resources/static/img";
	*/
	
	@Autowired //연결하다
	public NewsWebController(NewsDAO dao) {
		this.dao = dao;
	}
	@GetMapping("/list")
	public String listNews(Model m) {
		List<News> list;
		try {
			list = dao.getAll();
			m.addAttribute("newslist",list);
		} catch (SQLException e) {	
			e.printStackTrace();
			m.addAttribute("error","뉴스목록이 정상처리 되지 않았음.");}
		return "news/newsList";
		
	}
	@PostMapping("/add")
	public String addNews(@ModelAttribute News news, Model m, @RequestParam("file") MultipartFile file) {
	 try {
		 File dest = new File("C:/KOSTA/workspaceappche/workspace/spring_study/src/main/resources/static/img", file.getOriginalFilename());
		 //"C:/KOSTA/servercom/backend/img 직접 지정해도되고 함수 넣어도 되고 
		 file.transferTo(dest);
		 news.setImg("/img/"+dest.getName());
		 dao.addNews(news);
	} catch (Exception e) {
		e.printStackTrace();
		m.addAttribute("error", "뉴스가 정상 등록 되지 않았습니다.");
	}	
	 return "redirect:/news/list"; //redirect 페이지를 다시 부르는거임 
	}	
	
	@GetMapping ("/{aid}")
	public String getNews(@PathVariable int aid, Model m) {
		try {
			News n = dao.getNews(aid);
			m.addAttribute("news",n);
		} catch (SQLException e) {
			e.printStackTrace();
			m.addAttribute("error","뉴스를 정상적으로 가져오지 못함.");
		}
		
		return "news/newsView";
	
	}
	@GetMapping ("/delete/{aid}")
	public String delNews(@PathVariable int aid, Model m) {
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			m.addAttribute("error","뉴스를 정삭적으로 삭제하지 못했습니다.");
		}
		
		return "redirect:/news/list";
	//redirect:/news/newsList";
	}
}//main
