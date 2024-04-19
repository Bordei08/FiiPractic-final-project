package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Mention;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.MentionService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class MentionController {
    private MentionService mentionService;

    @Autowired
    public  MentionController(MentionService mentionService){
        this.mentionService = mentionService;
    }

    @PostMapping(value = "/mention", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createMention(@RequestBody Mention mention){
        mentionService.createMention(mention.getUserId(), mention.getPostId());
    }

    @DeleteMapping(value = "/mention/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMention(@PathVariable String id){
        mentionService.deleteMention(id);
    }

    @GetMapping(value = "/mention/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mention getMentionById(@PathVariable  String id){
        return mentionService.getMentionById(id);
    }

    @GetMapping(value = "/users-post-mentions/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByPostMentions(@PathVariable String postId){
        return mentionService.getAllUsersByPostMentions(postId);
    }

    @GetMapping(value = "/posts-user-mentions/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUserMentions(@PathVariable String userId){
        return mentionService.getAllPostsByUserMentions(userId);
    }

}
