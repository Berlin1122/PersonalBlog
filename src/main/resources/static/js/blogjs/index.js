$(function () {
    var articelId = 0;
    var categoryId = 0;
    var blogDetailUrl = '/blog/detail/';
    var blogListUrl = '/blog/showblogbycategory/';
    var adminLoginUrl = '/blog/loginpage';
    $(".article").click(function (e) {
        articelId = parseInt(e.target.id);
        location.href = blogDetailUrl+articelId;
    })
    //获取要的类别名和类别id
    $(".category").click(function (e) {
        console.log(e.target.id);
        categoryId = parseInt(e.target.id);
        var formData = new FormData();
        formData.append("categoryId",categoryId);
        $.ajax({
            type: 'POST',
            url: blogListUrl,
            async: false,
            data: formData,
            processData:false,
            contentType:false,
            cache:false,
            success:function(data){
                if(data.success == true){
                    console.log('进入响应成功方法');
                    location.href = data.url+'/'+categoryId;
                }else {
                    alert(data.msg);
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
    })
    $("#btnHome").click(function () {
        location.href = '/blog/index';
    })
    $("#btnWriteBlog").click(function () {
        //alert("执行跳转登录界面");
        window.open(adminLoginUrl);
    })
})