$(function(){

    //获取用户的用户名，以此作为跳转编辑页的身份标识
    var userName = $("#userNameText").text();
    var addBlogUrl = '/blog/'+userName+'/add';
    var blogManageUrl = '/blog/'+userName+'/manage';
    var blogTitle = '';
    var blogContent = '';
    var categoryId = '';
    var categoryName = '';
    var brifIntro = '';
    var status = 0;
    var categoryId = 0;
    $("#goback").click(function () {
        //location.href = blogManageUrl;
        history.back(-1);
        console.log("跳转管理页面");
    })
    $(".category").click(function (e) {
        console.log(e.target.id);
        categoryId = parseInt(e.target.id);
        categoryName = e.target.innerText;
        $("#btnCategory").text(categoryName);

    })

    $("#btnPublish").click(function (e) {
        status = 1;
        blogContent = $("#txtAreaBlogData").text();
        blogTitle = $("#blogTitle").val();
        if(blogContent == ''){
            alert("至少写个字吧...");
        }
        if(blogTitle == ''){
            alert("别忘了写个小标题...");
        }
        if(categoryName == ''){
            alert("顺手选个分类呗...");
        }

        if(blogContent != '' && blogTitle != '' && categoryName != ''){
            //截取前150个字符作为博文概要
            var tempContent = blogContent.replace(/<\/?[^>]*>/g, '');
            brifIntro = tempContent.substr(0,149);
            //alert("过滤标签后内容："+tempContent);
            var formData = new FormData();
            formData.append("content",blogContent);
            formData.append("status",status);
            formData.append("briefIntro",brifIntro);
            formData.append("title",blogTitle);
            formData.append("userName",userName);
            formData.append("categoryName",categoryName);
            formData.append("categoryId",categoryId);

            $.ajax({
                type: 'POST',
                url: addBlogUrl,
                async: false,
                data: formData,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data.success){
                        alert("发布成功");
                        console.log('进入响应成功方法');
                    }else {
                        alert('发布失败！');
                    }
                },
                error:function (info) {
                    console.log(info)
                    alert("进入error");
                },
                complete:function(data){
                    console.log(data);
                }

            });
        }

    });

    $("#btnSave").click(function (e) {
        status = 0;
        blogContent = $("#txtAreaBlogData").text();
        blogTitle = $("#blogTitle").val();
        if(blogContent == ''){
            alert("至少写个字吧...");
        }
        if(blogTitle == ''){
            alert("别忘了写个小标题...");
        }
        if(categoryName == ''){
            alert("顺手选个分类呗...");
        }

        if(blogContent != '' && blogTile != '' && categoryName != ''){
            //截取前20个字符作为博客简介
            brifIntro = blogContent.substr(0,20);

            var formData = new FormData();
            formData.append("content",blogContent);
            formData.append("status",status);
            formData.append("briefIntro",brifIntro);
            formData.append("title",blogTitle);
            formData.append("userName",userName);
            formData.append("categoryName",categoryName);

            $.ajax({
                type: 'POST',
                url: addBlogUrl,
                async: false,
                data: formData,
                processData:false,
                contentType:false,
                cache:false,
                success:function(data){
                    if(data.success){
                        alert("保存成功！");
                        console.log('进入响应成功方法');
                    }else {
                        alert('保存失败！');
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

    });

});