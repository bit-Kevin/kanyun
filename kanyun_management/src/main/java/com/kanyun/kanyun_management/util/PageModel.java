package com.kanyun.kanyun_management.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageModel<T> {
    private  String message;
    private  int state;
    private int pageNum;  // 当前页数
    private int pageSize; // 每页显示条数
    private int pages; // 总页数
    private long total; // 总条数
    private List<T> data;

}
