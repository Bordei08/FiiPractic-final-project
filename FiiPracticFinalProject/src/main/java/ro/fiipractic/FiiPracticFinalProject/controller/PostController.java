package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody Post post) {
        postService.addPost(post);
    }


    @PostMapping(value = "/repost", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRepost(@RequestBody Map<String, String> body ){
        postService.addRepost(body.get("userId"), body.get("postId"));
    }

    @DeleteMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
    }

    @GetMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable String postId) {
        return postService.getPostById(postId);
    }

    @GetMapping(value = "/posts-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUser(@PathVariable String userId) {
        return postService.getAllPostByUserId(userId);
    }

    @GetMapping(value = "/user-feed/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getFeedForUser(@PathVariable String userId) {
        return postService.getFeed(userId);
    }


    @PatchMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchPost(@PathVariable String postId, @RequestBody Map<String,String> body) {
        postService.updatePost(postId, body.get("message"));
    }

    @GetMapping(value = "/reposts-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllRepostsByUser(@PathVariable String userId){
        return postService.getAllRepostsByUser(userId);
    }

    @GetMapping(value = "/sharers-post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllSharersByPost(@PathVariable String postId){
        return postService.getAllUsersByRepost(postId);
    }

}
