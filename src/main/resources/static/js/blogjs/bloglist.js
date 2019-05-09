$(function () {
    var totalPages = parseInt($("#spanTotalPage").text());
    var currPage = parseInt($("#spanCurrPage").text());
    var categoryId = parseInt($("#spanCategoryId").text());
    var categoryName = $("#spanCategoryName").text();
    var title = $("#spanTitle").text();
    var showBlogByCategoryUrl = '/blog/showblogbycategory/';
    var blogSearchUrl = '/blog/searcharticlebytitle/';

    $("#btnCategory").text(categoryName);
    //分页参数设置,此处对应的分页功能除了响应点击类别之外还有搜索标题,搜索标题和分类查看使用的是同一个html页面
    var options = {
        currentPage: currPage,
        totalPages: totalPages,
        numberOfPages: 3,
        bootstrapMajorVersion: 3,
        size: "normal",
        alignment: "center",

        onPageClicked: function (e, originalEvent, type, page) {
            getDataByPage(page);
        }
    }
    $("#pagination").bootstrapPaginator(options);

    function getDataByPage(currPage) {
        //当请求来自分类查看时(categoryId = 0)
        if (isNaN(categoryId)) {
            location.href = blogSearchUrl + title + '/' + totalPages + '/' + currPage;
        } else {
            location.href = showBlogByCategoryUrl + totalPages + '/' + currPage + '/' + categoryId;
        }
    }
})