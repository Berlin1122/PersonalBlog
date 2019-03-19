$(function () {
    var totalPages = parseInt($("#spanTotalPage").text());
    var currPage = parseInt($("#spanCurrPage").text());
    var categoryId = parseInt($("#spanCategoryId").text());
    var categoryName = $("#spanCategoryName").text();
    var showBlogByCategoryUrl = '/blog/showblogbycategory/';
    $("#btnCategory").text(categoryName);
    //分页参数设置
    var options = {
        currentPage: currPage,
        totalPages: totalPages,
        numberOfPages:3,
        bootstrapMajorVersion:3,
        size:"normal",
        alignment:"center",

        onPageClicked:function (e,originalEvent,type,page) {
            getDataByPage(page);
        }
    }
    $("#pagination").bootstrapPaginator(options);

    function getDataByPage(currPage) {
        location.href=showBlogByCategoryUrl+totalPages+'/'+currPage+'/'+categoryId;
    }
})