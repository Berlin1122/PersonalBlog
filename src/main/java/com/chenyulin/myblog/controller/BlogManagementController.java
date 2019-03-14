package com.chenyulin.myblog.controller;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.service.ArticleCategoryService;
import com.chenyulin.myblog.service.ArticleService;
import com.chenyulin.myblog.service.UserService;
import com.chenyulin.myblog.utils.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/blog")
public class BlogManagementController {
    @Resource
    private UserService userService;

    @Resource
    private ArticleCategoryService categoryService;

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/loginpage",method = RequestMethod.GET)
    public String userLogin(@ModelAttribute(value="user")User user){
        return "html/adminlogin";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleLogin(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String userName = HttpServletRequestUtil.getString(request,"userName");
        String pwd = HttpServletRequestUtil.getString(request,"pwd");
        System.out.println("userName"+userName);
        System.out.println("pwd"+pwd);
        User user = new User();
        user.setUserName(userName);
        user.setPwd(pwd);

        User userDB = userService.getUserByUserName(user);
        System.out.println("数据库值："+userDB.getPwd()+userDB.getUserName());
        if(userDB != null && userDB.getPwd().equals(user.getPwd())){
            System.out.println("匹配");
            modelMap.put("success",true);
            modelMap.put("url","/blog/"+userName+"/manage");
        }else{
            System.out.println("不匹配");
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/{username}/manage",method = RequestMethod.GET)
    public String manageBlog(@PathVariable("username") String userName, Model model){
        model.addAttribute("userName",userName);
        System.out.println(userName+"===========");
        return "html/blogmanagement";
    }

    @RequestMapping(value = "/{username}/edit",method = RequestMethod.GET)
    public String writeBlog(@PathVariable("username") String userName, Model model){
        model.addAttribute("userName",userName);
        List<ArticleCategory> categoryList = categoryService.getAllArticleCategory();
        for (ArticleCategory category:categoryList) {
            System.out.println(category.getCategoryName());
        }
        model.addAttribute("categoryList",categoryList);

        System.out.println(userName+"===========from edit");
        return "html/blogedit";
    }

    @RequestMapping(value = "/{username}/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addBlog(@PathVariable("username") String userName,HttpServletRequest request){
        //TODO 编写业务实体类,枚举
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Article article = new Article();
        User loginUser = new User();
        loginUser.setUserName(userName);
        User user = userService.getUserByUserName(loginUser);
        String content = HttpServletRequestUtil.getString(request,"content");
        String briefIntro = HttpServletRequestUtil.getString(request,"briefIntro");
        String title = HttpServletRequestUtil.getString(request,"title");
        int status = HttpServletRequestUtil.getInt(request,"status");
        String categoryName = HttpServletRequestUtil.getString(request,"categoryName");

        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName(categoryName);
        ArticleCategory category = categoryService.getCategoryByCategoryName(articleCategory);

        //以上是获取表单的数据
        if(user != null && category != null){
            article.setUser(user);
            article.setTitle(title);
            article.setStatus(status);
            article.setLastEditTime(new Date());
            article.setCreateTime(new Date());
            article.setContent(content);
            article.setBriefIntro(briefIntro);
            article.setCategory(category);
            boolean success = articleService.addArtilcle(article);
            if(success){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
            }

        }else{
            modelMap.put("success",false);
        }
        return modelMap;
    }
}
