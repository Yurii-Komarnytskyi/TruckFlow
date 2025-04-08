package com.ykomarnytskyi2022.dao.dto;

import java.util.List;

public record PageableDto<T> (List<T> page, long totalElements, int currentPage, int totalPages) {
    public static final int FIRST_PAGE_INDEX = 0;
	public static int calculateTotalPages(int listSize, int standardPageSize) {
		return (listSize + standardPageSize - 1) / listSize;
	}
}
