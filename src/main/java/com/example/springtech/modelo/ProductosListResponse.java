package com.example.springtech.modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductosListResponse {
    private int count;
    private String next;
    private String previous;
    private List<Productos> results;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<Productos> getResults() {
		return results;
	}
	public void setResults(List<Productos> results) {
		this.results = results;
	}
    
    
    
}
