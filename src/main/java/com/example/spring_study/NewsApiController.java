package com.example.spring_study;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//리턴값을 문자열로 받는 api

@RestController
@RequestMapping("/api/news")
public class NewsApiController {
		final NewsDAO dao;
		
		@Autowired
		public NewsApiController(NewsDAO dao) {
			this.dao = dao;
		}
		
		@GetMapping
		public List<News> getNewsList(){
			List<News> newsList = null;
			try {
				newsList = dao.getAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return newsList;
		}
		
		@GetMapping("{aid}")
		public News getNews(@PathVariable("aid") int aid){
			News news = null;
			try {
				news = dao.getNews(aid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return news;
		}	
		
		@DeleteMapping("{aid}")
		public String deltNews(@PathVariable("aid") int aid){
		 try {
				dao.delNews(aid);
			} catch (SQLException e) {
				e.printStackTrace();
				return  "News API: 삭제 실패";
			}	
			 return "News API: 뉴스 삭제완료";
			}
			
		@PostMapping
		public String addNews(@RequestBody News news ) {
		 try {
			dao.addNews(news);
		} catch (SQLException e) {
			e.printStackTrace();
			return  "News API: 등록 실패";
		}	
		 return "News API: 뉴스 등록됨";
		}		
	}//main

