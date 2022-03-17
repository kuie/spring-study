package com.example.spring_study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NewsDAO {
	public static void main(String[] args) throws SQLException{
		NewsDAO dao = new NewsDAO(); //
		dao.getAll().forEach(System.out::println);
		System.out.println("2번뉴스 검색=====");
		System.out.println(dao.getNews(2));
		/* insert 확인 내용
		 * News news = new News();
		news.setTitle(new String ("연습이요"));
        news.setImg(new String("/연습파일.png"));
        news.setContent(new String("연습내용이용"));
        System.out.println(news);
        dao.addNews(news); //뉴스객체 만들어서 넣어줘야함 */ 
		System.out.println("6번뉴스 삭제=====");
		dao.delNews(6);
		System.out.println("=============");
		dao.getAll().forEach(System.out::println);


	}

//드라이버설정 우선
final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
final String JDBC_URL = "jdbc:oracle:thin:webdb/1234@localhost:1521/xe";

public Connection open() throws SQLException {
	Connection conn = null;
	try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(JDBC_URL);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return conn;
	
}

//첫번째
public List <News> getAll() throws SQLException { 
	Connection conn = this.open();
	List<News> newList = new ArrayList();
	String sql = "select aid,title,img,to_char(time,'YYYY-MM-DD HH:MI:SS') cdate,content from news";
	PreparedStatement pstmt;
	try {
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(); //오라클 데이터베이스 출력 화면을 주는 행위
		//첫번째데이터 뭔지 두번째 데이터 뭔지 알려주는거임 
		while(rs.next()) {
			News n = new News();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setTime(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			newList.add(n); //뉴리스트에 담음 
		}	
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
		finally {
			this.close(conn); //컨 변수 만들어서 트캐치 안하고 해결 
	}return newList;
}
		
			

	//두번쨰  변수 하나 데이터만 가져오는 함수 
public News getNews (int aid) throws SQLException {
	Connection conn = this.open();
	News n = new News();
	String sql = "select aid,title,img,to_char(time,'YYYY-MM-DD HH:MI:SS') cdate,content from news where aid=?";
	//?:준비되지 않은 값/즉 변수 받아와야하는 값 , 연동하는 쿼리 짜기 ->PreparedStatement 넣어줘야함 
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid); //첫번째 변수 를 ?에 넣는거 데이터베이스 인덱스 1번부터 
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		n.setAid(rs.getInt("aid"));
		n.setTitle(rs.getString("title"));
		n.setImg(rs.getString("img"));
		n.setTime(rs.getString("cdate"));
		n.setContent(rs.getString("content"));
		pstmt.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {this.close(conn); //커넥트 안닫으면 데이터 계속 접송중임 꺼줘야함 
	}	return n;}

//세번째 업데이트 쿼리 (insert) 
public void addNews(News n) throws SQLException {
	Connection conn = this.open();
	String sql = "insert into news values(news_seq.nextval,?,?,SYSTIMESTAMP ,?)";
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, n.getTitle());
		pstmt.setString(2, n.getImg());
		pstmt.setString(3, n.getContent());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {this.close(conn);

	}	
}
	
	
//네번째 delete 
public void delNews(int aid) throws SQLException {
	Connection conn = this.open();
String sql = "delete from news where aid=?";
try {
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, aid);
	pstmt.executeQuery();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {this.close(conn);

}	
}

//첫번째 생성 후 클로즈 함수 생성 안그럼 넘 길어져서 ;;
public void close(Connection conn) {
	if (conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}//dao
