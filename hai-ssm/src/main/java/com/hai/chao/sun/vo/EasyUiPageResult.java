package com.hai.chao.sun.vo;

import java.util.List;

public class EasyUiPageResult<T> {

    
    private Long total;
    private List<T> rows;
    public Long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
   }  
}
