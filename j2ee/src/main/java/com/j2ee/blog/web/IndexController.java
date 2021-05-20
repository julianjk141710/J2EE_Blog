package com.j2ee.blog.web;
import com.j2ee.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
//        int i = 9 / 0;
//        String blog = null;
//        if (blog == null) {
//            throw new NotFoundException("博客不存在");
//        }

        System.out.println("-----index------");
        model.addAttribute("msg", "IndexController");
        return "index";
    }

}
