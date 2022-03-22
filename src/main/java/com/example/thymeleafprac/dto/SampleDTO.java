package com.example.thymeleafprac.dto;

import java.time.LocalDateTime;

public class SampleDTO {

	private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
	public Long getSno() {		return sno;	}
	public void setSno(Long sno) {		this.sno = sno;	}
	public String getFirst() {		return first;	}
	public void setFirst(String first) {		this.first = first;	}
	public String getLast() {		return last;	}
	public void setLast(String last) {		this.last = last;	}
	public LocalDateTime getRegTime() {		return regTime;	}
	public void setRegTime(LocalDateTime regTime) {		this.regTime = regTime;	}
	
	public SampleDTO(long i, String first, String last, LocalDateTime regTime) {
		this.sno = i;
		this.first = first;
		this.last = last;
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return " [" + sno + ", " + first + ", " + last + ", " + regTime + "]";
	
	}
    
    
}
