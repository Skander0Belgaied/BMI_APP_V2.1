package com.bmi.service.app;

import java.util.List;



public class ServicerResponseApi {
	private String status;
	private List<?> data;
	
	public ServicerResponseApi(String string, Object book) {
		// TODO Auto-generated constructor stub
	}
	public ServicerResponseApi(String string, List<?> bookStore) {
		// TODO Auto-generated constructor stub
	}
	public ServicerResponseApi() {
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
}
