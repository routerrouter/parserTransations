package com.bycoders.parsertransations.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PageInfo {

    private int pageNumber;
    private int pageSize;
    private BigDecimal sum;
    
    
}
