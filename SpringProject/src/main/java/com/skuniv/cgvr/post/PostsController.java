package com.skuniv.cgvr.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;

    @RequestMapping("notice/all")
    public String notice_all() {
        return "Notice/allnotice";
    }

    @RequestMapping("notice/general")
    public String notice_general(Model model) {
        List<Posts> postsList = this.postsService.getList();
        model.addAttribute("postsList", postsList);
        return "Notice/normalnotice";
    }

    @RequestMapping("notice/lecture")
    public String notice_lecture() {
        return "Notice/lectnotice";
    }

    @RequestMapping("notice/laboratory")
    public String notice_laboratory() {
        return "Notice/labnotice";
    }




    @GetMapping("notice/post_form")
    public String post_form() {
        return "post_form";
    }
}
