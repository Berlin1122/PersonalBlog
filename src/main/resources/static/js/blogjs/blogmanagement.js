$(function(){
    //获取用户的用户名，以此作为跳转编辑页的身份标识
    var userName = $("#userNameSpan").text();
    //alert(userName);
    var blogEditUrl = '/blog/'+userName+'/edit';
    $("#writeBlog").click(function () {
        location.href = blogEditUrl;
        console.log("执行跳转编辑页面");
    })

});