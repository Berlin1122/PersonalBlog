$(function(){
   var loginUrl = '/blog/login';
   $("button").click(function (e) {
       var userName = $("#inputUserName").val();
       var pwd = $("#inputPassword").val();
       var formData = new FormData();
       formData.append("userName",userName);
       formData.append("pwd",pwd);
       $.ajax({
           type: 'POST',
           url: loginUrl,
           async: false,
           data: formData,
           processData:false,
           contentType:false,
           cache:false,
           success:function(data){
               if(data.success == true){
                   location.href= data.url+'';
                   console.log('进入响应成功方法');
               }else {
                   alert('密码或者用户名输入错误！');
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
   });
});