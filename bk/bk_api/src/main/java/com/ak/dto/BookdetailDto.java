package com.ak.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BookdetailDto extends ADto {

	private String title;
	private String author;
	private String description;
	private String cover;
	private String publisher;
	private String isbn;
}
