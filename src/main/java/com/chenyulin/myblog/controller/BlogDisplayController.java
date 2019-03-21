package com.chenyulin.myblog.controller;

import com.chenyulin.myblog.bean.Article;
import com.chenyulin.myblog.bean.ArticleCategory;
import com.chenyulin.myblog.bean.User;
import com.chenyulin.myblog.service.ArticleCategoryService;
import com.chenyulin.myblog.service.ArticleService;
import com.chenyulin.myblog.service.UserService;
import com.chenyulin.myblog.utils.HttpServletRequestUtil;
import com.chenyulin.myblog.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理主页、博客详情页、分类展示页请求
 */
@Controller
@RequestMapping("/blog")
public class BlogDisplayController {
    @Resource
    private UserService userService;

    @Resource
    private ArticleCategoryService categoryService;

    @Resource
    private ArticleService articleService;

    //访问主页之后获取user,也就是管理员自己,同时把这个用户下的文章类别从数据库取出来
    private static User currUser;
    private static List<ArticleCategory> categoryList;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        User user = new User();
        user.setUserName("Berlin1122");
        this.currUser = userService.getUserByUserName(user);
        List<Article> articleList = articleService.getTop6ArticleListByUser(this.currUser);

        this.categoryList = categoryService.getArticleCategoryByUserId(currUser);

        System.out.println(articleList.size()+"=============");
        model.addAttribute("articleList",articleList);
        model.addAttribute("categoryList",categoryList);
        return "html/index";
    }
    @RequestMapping(value = "/detail/{blogid}",method = RequestMethod.GET)
    public String showBlogDetail(@PathVariable("blogid")String blogId,Model model){
        int blogIdInt = Integer.parseInt(blogId);
        Article tempArticle = new Article();
        tempArticle.setArticleId(blogIdInt);

        Article article = articleService.getArticleById(tempArticle);
        model.addAttribute("article",article);
        model.addAttribute("categoryList",this.categoryList);
        System.out.println("是否有user"+currUser.getUserName());
        return "html/blogdetail";
    }

    /**
     * 处理点击主页文章分类的点击跳转,计算点击的类别下的文章数量,返回总的页数(页数拼接到点击后跳转的实际url中)
     * @param request
     * @return
     */
    @RequestMapping(value = "/showblogbycategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleBlogShowByCategory(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int categoryId = HttpServletRequestUtil.getInt(request,"categoryId");
        Article article = new Article();
        ArticleCategory category = new ArticleCategory();
        category.setCategoryId(categoryId);
        article.setCategory(category);

        int count = articleService.getArticleCountByCategory(article);
        int totalPage = PageUtil.calTotalPages(count);
        if(totalPage == 0){
            modelMap.put("msg","该类别没有文章");
            modelMap.put("success",false);
        }else{
            modelMap.put("url","/blog/showblogbycategory/"+totalPage+"/1");
            modelMap.put("success",true);
        }
        return modelMap;
    }

    @RequestMapping(value = "/showblogbycategory/{totalpage}/{currpage}/{categoryId}",method = RequestMethod.GET)
    public String showBlogByCategory( @PathVariable("totalpage") String totalPage,
                                      @PathVariable("currpage") String currentPage,
                                      @PathVariable("categoryId") String categoryId,
                                      Model model){
        int page = Integer.parseInt(currentPage);
        List<ArticleCategory> categoryList = categoryService.getArticleCategoryByUserId(currUser);
        Article article = new Article();
        ArticleCategory category = new ArticleCategory();
        int currCategoryId = Integer.parseInt(categoryId);
        category.setCategoryId(currCategoryId);
        article.setCategory(category);
        List<Article> articleList = articleService.getArticleListByCategoryId(article,page);
        ArticleCategory currCategory = categoryService.getCategoryByCategoryId(category);

        String categoryName = currCategory.getCategoryName();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("articleList",articleList);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currPage",currentPage);
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("categoryName",categoryName);
        return "html/bloglist";
    }

    @RequestMapping(value = "/searcharticlebytitle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleSearchAticleByTitle(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String title = HttpServletRequestUtil.getString(request,"title");
        Article article = new Article();
        article.setTitle(title);

        int count = articleService.getCountByTitle(article,this.currUser);
        int totalPage = PageUtil.calTotalPages(count);
        System.out.println("总的页面数量"+totalPage);
        if(totalPage > 0){
            modelMap.put("success",true);
            modelMap.put("url","/blog/searcharticlebytitle/"+title+"/"+totalPage+"/1");
        }else{
            modelMap.put("success",false);
            modelMap.put("msg","没有符合查询内容的文章");
        }
        return modelMap;
    }

    @RequestMapping(value = "/searcharticlebytitle/{title}/{totalpage}/{currpage}",method = RequestMethod.GET)
    public String showBlogBySearch(@PathVariable("totalpage") String totalPage,
                                   @PathVariable("title") String title,
                                   @PathVariable("currpage") String currentPage,
                                   Model model){
        int page = Integer.parseInt(currentPage);
        Article article = new Article();
        article.setTitle(title);
        String categoryName = "文章分类";
        List<Article> articleList = articleService.getArticleListByTitle(this.currUser,article,page);
        model.addAttribute("categoryList",this.categoryList);
        model.addAttribute("articleList",articleList);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currPage",currentPage);
        model.addAttribute("title",title);
        model.addAttribute("categoryName",categoryName);

        return "html/bloglist";
    }

}
