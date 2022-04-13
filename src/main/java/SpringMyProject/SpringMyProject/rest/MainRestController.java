package SpringMyProject.SpringMyProject.rest;


import SpringMyProject.SpringMyProject.entities.Comments;
import SpringMyProject.SpringMyProject.entities.ShopItems;
import SpringMyProject.SpringMyProject.entities.Users;
import SpringMyProject.SpringMyProject.repositories.CommentRepository;
import SpringMyProject.SpringMyProject.services.ShopItemService;
import SpringMyProject.SpringMyProject.services.UserService;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost"})
public class MainRestController {

    @Value("${target.url}")
    private String targetURL;
    @Value("${loadURL}")
    private String loadURL;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;



    @Autowired
    private ShopItemService shopItemService;
    @Autowired
    private CommentRepository commentRepository;


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/addcomment")
    public ResponseEntity<?> toadditem(@RequestParam(name = "comment") String comment,
                                       @RequestParam(name = "itemId") Long itemId, Model model) {
        ShopItems item = shopItemService.getItem(itemId);
        Comments newComment = new Comments();
        if (item!=null) {
            newComment.setItem_id(itemId);
            newComment.setName(comment);
            newComment.setUser(getUser());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            newComment.setPostDate(timestamp);
            shopItemService.saveComment(newComment);
        }
        model.addAttribute("item", item);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/deletecomment")
    public ResponseEntity<?> deleteComment(@RequestParam(name = "commentId") Long commentId) {
        Comments comment  = shopItemService.getOneCommentary(commentId);
        if (comment  != null) {
            shopItemService.deleteComment(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/editcomment")
    public ResponseEntity<?> editcomment(@RequestParam(name = "commentId") Long commentId,
                                         @RequestParam(name = "commentText") String commentText) {
        Comments comment  = shopItemService.getOneCommentary(commentId);
        if (comment  != null) {
            comment.setName(commentText);
            shopItemService.saveComment(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }



    @GetMapping(value = "/allcomments")
    public ResponseEntity<?> getAllComments(@RequestParam(name = "itemId") Long id, Model model) {
            List<Comments> comments = commentRepository.findAll();
            List<Comments> comments2 = new ArrayList<>();
            for (Comments cm: comments) {
                if (cm.getItem_id() == id) {
                    comments2.add(cm);
                }
            }
            model.addAttribute(comments2);
            return new ResponseEntity<>(comments2, HttpStatus.OK);
    }


    @GetMapping(value = "/getComment")
    public ResponseEntity<?> getComment(@RequestParam(name = "commentId") Long id, Model model) {
        Comments comment = shopItemService.getOneCommentary(id);
        if (comment!=null) {
            model.addAttribute(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = (Users)authentication.getPrincipal();
            return user;
        }else {
            return null;
        }
    }





}
