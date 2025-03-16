package com.ykomarnytskyi2022.dto;

import java.util.List;
import java.util.Objects;

public class PageableDto<T> {
    private List<T> page;
    private long totalElements;
    private int currentPage;
    private int totalPages;
    
    public PageableDto(
    		List<T> page, 
    		long totalElements, 
    		int currentPage, 
    		int totalPages) {
		this.page = page;
		this.totalElements = totalElements;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
	}
    
	public List<T> getPage() {
		return page;
	}
	public void setPage(List<T> page) {
		this.page = page;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(currentPage, page, totalElements, totalPages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PageableDto)) {
			return false;
		}
		PageableDto<T> other = (PageableDto<T>) obj;
		return currentPage == other.currentPage && Objects.equals(page, other.page)
				&& totalElements == other.totalElements && totalPages == other.totalPages;
	}

	@Override
	public String toString() {
		return "PageableDto [page=" + page + ", totalElements=" + totalElements + ", currentPage=" + currentPage
				+ ", totalPages=" + totalPages + "]";
	}
	
	
	
}
