package com.chenyulin.myblog.controller;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.service.ArticleCategoryService;
import com.chenyulin.myblog.service.ArticleService;
import com.chenyulin.myblog.service.UserService;
import com.chenyulin.myblog.utils.HttpServletRequestUtil;
import com.chenyulin.myblog.utils.ImageUtil;
import com.chenyulin.myblog.utils.PageUtil;
import com.chenyulin.myblog.utils.PathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理管理者登录、管理博客、编辑博客请求
 */

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

        int count = articleService.getArticleCountByUser(userDB);
        int totalPage = PageUtil.calTotalPages(count);

        if(userDB != null && userDB.getPwd().equals(user.getPwd())){
            request.getSession().setAttribute("userName",userDB.getUserName());
            System.out.println("匹配");
            modelMap.put("success",true);
            modelMap.put("url","/blog/"+userName+"/manage/"+totalPage+"/1");
        }else{
            System.out.println("不匹配");
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/{username}/manage/{totalpage}/{currpage}",method = RequestMethod.GET)
    public String manageBlog(@PathVariable("username") String userName,
                             @PathVariable("totalpage") String totalPage,
                             @PathVariable("currpage") String currentPage,
                             Model model){

        model.addAttribute("userName",userName);
        model.addAttribute("currPage",currentPage);
        model.addAttribute("totalPage",totalPage);

        User user = new User();
        user.setUserName(userName);
        User currUser = userService.getUserByUserName(user);
        int page = Integer.parseInt(currentPage);
        List<Article> articleList = articleService.getArticleListByUser(currUser,page);

        List<ArticleCategory> categoryList = categoryService.getArticleCategoryByUserId(currUser);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("articleList",articleList);
        return "html/blogmanagement";
    }

    @RequestMapping(value = "/{username}/edit",method = RequestMethod.GET)
    public String writeBlog(@PathVariable("username") String userName, Model model){
        model.addAttribute("userName",userName);
        User user = new User();
        user.setUserName(userName);

        User currUser = userService.getUserByUserName(user);
        List<ArticleCategory> categoryList = categoryService.getArticleCategoryByUserId(currUser);
        for (ArticleCategory category:categoryList) {
            System.out.println(category.getCategoryName());
        }
        model.addAttribute("categoryList",categoryList);


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
        int categoryId = HttpServletRequestUtil.getInt(request,"categoryId");

        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName(categoryName);
        articleCategory.setCategoryId(categoryId);

        //以上是获取表单的数据
        if(user != null && articleCategory != null){
            article.setUser(user);
            article.setTitle(title);
            article.setStatus(status);
            article.setLastEditTime(new Date());
            article.setCreateTime(new Date());
            article.setContent(content);
            article.setBriefIntro(briefIntro);
            article.setCategory(articleCategory);
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

    @RequestMapping(value = "/{username}/deletecategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCategory(@PathVariable("username") String userName,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int category_id = HttpServletRequestUtil.getInt(request,"categoryId");
        ArticleCategory category = new ArticleCategory();
        category.setCategoryId(category_id);
        boolean isDeleted = categoryService.deleteCategory(category);
        if(isDeleted){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/{username}/addcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCategory(@PathVariable("username") String userName,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        User user = new User();
        user.setUserName(userName);
        User currUser = userService.getUserByUserName(user);
        String categoryName = HttpServletRequestUtil.getString(request,"categoryName");
        ArticleCategory category = new ArticleCategory();
        category.setUser(currUser);
        category.setCategoryName(categoryName);
        boolean isAdded = categoryService.addCategory(category);

        if(isAdded){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/{username}/deletearticle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteArticle(@PathVariable("username") String userName,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int articleId = HttpServletRequestUtil.getInt(request,"articleId");
        System.out.println(articleId+"=========article");
        Article article = new Article();
        article.setArticleId(articleId);

        boolean isDeleted = articleService.removeArticleById(article);
        if(isDeleted){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errorMsg","系统内部错误");
        }
        return modelMap;
    }

    /**
     * 博客中图片上传，方法中，参数任何位置都不能改动
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadfile",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadFile(HttpServletRequest request,
                                         @RequestParam(value = "editormd-image-file", required = true)
                                                 MultipartFile file)throws Exception{
        Map<String,Object> modelMap = new HashMap<String,Object>();

        String basePath = PathUtil.getImgBasePath();
        String imageFileName = ImageUtil.saveToLocal(file,basePath);//保存成功后返回实际存储的物理地址
        if(imageFileName != null){
            modelMap.put("success",1);
            modelMap.put("url",imageFileName);
        }else{
            modelMap.put("success",0);
        }
        return modelMap;
    }

    /**
     * 处理从编辑页面返回管理界面时刷新管理界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/gobackmanage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleEditBack2Manage(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String userName = HttpServletRequestUtil.getString(request,"userName");
        User user = new User();
        user.setUserName(userName);
        User userDB = userService.getUserByUserName(user);
        int count = articleService.getArticleCountByUser(userDB);
        int totalPage = PageUtil.calTotalPages(count);
        modelMap.put("success",true);
        modelMap.put("url","/blog/"+userName+"/manage/"+totalPage+"/1");
        return modelMap;
    }

}

