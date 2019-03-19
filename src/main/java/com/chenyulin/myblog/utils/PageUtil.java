package com.chenyulin.myblog.utils;

public class PageUtil {
    public static final double DATA_COUNT_PERPAGE = 6.0;//每页展示的数据条数

    /**
     * 计算总的页数
     * @param dataCount 总的数据条数
     * @return
     */
    public static int calTotalPages(int dataCount){
        return (int)(Math.ceil(dataCount/DATA_COUNT_PERPAGE));
    }

    public static void main(String[] args) {
        System.out.println(PageUtil.calStartIndex(3));
    }

    /**
     * 根据页码，计算数据库中起始行数
     * @param page
     * @return
     */
    public static int calStartIndex(int page){
        return (page-1)*(int)DATA_COUNT_PERPAGE;
    }

}
