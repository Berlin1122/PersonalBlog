$(function () {
    var articelId = 0;
    var categoryId = 0;
    var blogDetailUrl = '/blog/detail/';
    var blogListUrl = '/blog/showblogbycategory/';
    var adminLoginUrl = '/blog/loginpage';
    var blogSearchUrl = '/blog/searcharticlebytitle';
    var blogTitle = '';
    $(".article").click(function (e) {
        articelId = parseInt(e.target.id);
        location.href = blogDetailUrl + articelId;
    })
    //获取要的类别名和类别id
    $(".category").click(function (e) {
        console.log(e.target.id);
        categoryId = parseInt(e.target.id);
        var formData = new FormData();
        formData.append("categoryId", categoryId);
        $.ajax({
            type: 'POST',
            url: blogListUrl,
            async: false,
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                if (data.success == true) {
                    console.log('进入响应成功方法');
                    location.href = data.url + '/' + categoryId;
                } else {
                    alert(data.msg);
                }
            },
            error: function (info) {
                //console.log(info)
                alert("进入error");
            },
            complete: function (data) {
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
    $("#btnSearch").click(function () {
        blogTitle = ($("#inpTitle").val()).trim();
        if (blogTitle != '') {
            var formData = new FormData();
            formData.append("title", blogTitle);
            $.ajax({
                type: 'POST',
                url: blogSearchUrl,
                async: false,
                data: formData,
                processData: false,
                contentType: false,
                cache: false,

                success: function (data) {
                    if (data.success) {
                        location.href = data.url;
                    } else {
                        alert(data.msg)
                    }
                },
                error: function (info) {
                    alert("搜索进入error");
                },
            })
        }
    })
    function footerPosition(){
        $("footer").removeClass("fixed-bottom");
        var contentHeight = document.body.scrollHeight,//网页正文全文高度
            winHeight = window.innerHeight;//可视窗口高度，不包括浏览器顶部工具栏
        if(!(contentHeight > winHeight)){
            //当网页正文高度小于可视窗口高度时，为footer添加类fixed-bottom
            $("footer").addClass("fixed-bottom");
        }
    }
    footerPosition();
    $(window).resize(footerPosition);
})