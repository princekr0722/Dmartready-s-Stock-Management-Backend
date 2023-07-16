package com.dmart.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageOf<T> {
	private int currentPageNumber;
	private int totalCurrentPageElements;
	private int pageSize;
	private long totalPages;
	private long totalElements;
	private List<T> pageContent;
	
	public PageOf(Page<T> page) {
		totalPages = page.getTotalPages();
		currentPageNumber = page.getNumber()+1;
		pageSize = page.getSize();
		totalElements = page.getTotalElements();
		pageContent = page.getContent();
		totalCurrentPageElements = page.getNumberOfElements();
	}
}