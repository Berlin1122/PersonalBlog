$(function(){
    //获取用户的用户名，以此作为跳转编辑页的身份标识
    var userName = $("#userNameSpan").text();
    //alert(userName);
    var blogEditUrl = '/blog/'+userName+'/edit';
    var categoryDeleteUrl = '/blog/'+userName+'/deletecategory';
    var categoryAddUrl = '/blog/'+userName+'/addcategory';
    var categoryName = '';
    var categoryId = 0;
    var articleId = 0;
    var inputCategoryName='';//输入的文章类名，用于新增类别
    var totalPages = parseInt($("#spanTotalPage").text());
    var currPage = parseInt($("#spanCurrPage").text());
    var blogManageUrl = '/blog/'+userName+'/manage/'+totalPages;
    var deleteArticleUrl = '/blog/'+userName+'/deletearticle';
    //分页参数设置
    var options = {
        currentPage: currPage,
        totalPages: totalPages,
        numberOfPages:3,
        size:"normal",
        alignment:"left",

        //getDataByPage(currentPage,totalPages);
        onPageClicked:function (e,originalEvent,type,page) {
            getDataByPage(page);
        }
    }
    $("#writeBlog").click(function () {
        location.href = blogEditUrl;
        console.log("执行跳转编辑页面");
    })
    //获取要删除的类别名和类别id
    $(".category").click(function (e) {
        console.log(e.target.id);
        categoryId = parseInt(e.target.id);
        categoryName = e.target.innerText;
        $("#btnCategory").text(categoryName);

    })
    $(".article").click(function(e){
        //获取要删除（更改）的文章id
        articleId = e.target.id;
        deleteArticle(articleId);
    })
    $("#btnDeleteCategory").click(function () {
        if(categoryId == 0){
            alert("请选择一个类别");
        }else{
            var isDelete = confirm("确定删除"+categoryName+"这个类别吗？");
            if(isDelete){
                var formData = new FormData();
                formData.append("categoryId",categoryId);
                $.ajax({
                    type: 'POST',
                    url: categoryDeleteUrl,
                    async: false,
                    data: formData,
                    processData:false,
                    contentType:false,
                    cache:false,
                    success:function(data){
                        if(data.success == true){
                            console.log('进入响应成功方法');
                            alert('删除成功！');
                            window.location.reload();
                        }else {
                            alert("删除失败")
                        }
                    },
                    error:function (info) {
                        //console.log(info)
                        alert("进入error");
                    },
                    complete:function(data){
                        console.log(data);
                    }
                });
            }
        }
    })
    $("#btnAddCategory").click(function () {
        inputCategoryName = $("#inpCategory").val();
        if(inputCategoryName == ''){
            alert("请输入一个类别名");
        }else{
            var formData = new FormData();
            formData.append("categoryName",inputCategoryName);
            $.ajax({
                type: 'POST',
                url:categoryAddUrl,
                async: false,
                data: formData,
                processData:false,
                contentType:false,
                cache:false,
                success:function(data){
                    if(data.success == true){
                        console.log('进入响应成功方法');
                        alert('添加成功！');
                        //添加成功，重新刷新页面
                        window.location.reload();
                    }else {
                        alert('操作发生错误！');
                    }
                },
                error:function (info) {
                    //console.log(info)
                    alert("进入error");
                },
                complete:function(data){
                    console.log(data);
                }
            });
        }
    })
    $("#pagination").bootstrapPaginator(options);

    function getDataByPage(currPage) {
        location.href=blogManageUrl+'/'+currPage;
    }
    function deleteArticle(articleId) {
        if(articleId > 0){
            var isDelete=confirm("确认删除此文章吗？");
            if(isDelete){
                var formData = new FormData();
                formData.append("articleId",articleId);
                $.ajax({
                    type: 'POST',
                    url: deleteArticleUrl,
                    async: false,
                    data: formData,
                    processData:false,
                    contentType:false,
                    cache:false,
                    success:function(data){
                        if(data.success == true){
                            console.log('进入响应成功方法');
                            alert('删除成功！');
                            //删除成功，重新刷新页面
                            window.location.reload();
                        }else {
                            alert(data.errorMsg);
                        }
                    },
                    error:function (info) {
                        //console.log(info)
                        alert("进入error");
                    },
                    complete:function(data){
                        console.log(data);
                    }
                });
            }
        }
    }
});