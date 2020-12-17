package com.blog.web.admin;

import com.blog.po.Blog;
import com.blog.po.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.vo.TitleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CommentsController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments")
    public String comments(Model model, Pageable pageable) {
        Page<Blog> blogs = blogService.listBlogCommentabled(pageable);
        List<TitleComment> titleComments = new ArrayList<>();
        for (Blog blog : blogs) {
            TitleComment titleComment = new TitleComment();
            titleComment.setTitle(blog.getTitle());
            List<Comment> comments = commentService.listCommentByBlogId(blog.getId());
            titleComment.setComments(comments);
            titleComments.add(titleComment);
        }
        model.addAttribute("titleComments", titleComments);
        return "admin/comments";
    }

    @GetMapping("/comments/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        commentService.deleteByCommentId(id);
//        System.out.println("这是要删除的 id --> " + id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/comments";
    }
}
