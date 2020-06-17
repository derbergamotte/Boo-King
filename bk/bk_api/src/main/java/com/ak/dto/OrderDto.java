package com.ak.dto;

import java.util.Calendar;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends ADto {

    private String userName;
    private String bookName;
    private long departmentId;
    private String departmentName;
    private Calendar dateOfTakeBook;
    private Calendar dateOfReturnBook;
}
