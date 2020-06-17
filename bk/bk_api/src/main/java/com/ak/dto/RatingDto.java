package com.ak.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RatingDto extends ADto{

	private String bookName;
	private String userName;
	private int rate;
	private String comment;
}
