package com.j2ee.blog.web.admin;

import com.j2ee.blog.po.Blog;
import com.j2ee.blog.po.User;
import com.j2ee.blog.service.BlogService;
import com.j2ee.blog.service.TagService;
import com.j2ee.blog.service.TypeService;
import com.j2ee.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        System.out.println(blogService.listBlog(pageable, blog));
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.listType());

        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));

        return "admin/blogs :: blogList";
    }
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("types", typeService.listType());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT;
    }

}