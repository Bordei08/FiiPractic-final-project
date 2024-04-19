package ro.fiipractic.FiiPracticFinalProject.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.PostService;

import javax.print.DocFlavor;
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

    @Operation(summary = "Create a new post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new post",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Already exist this post",
                    content = @Content)
    })
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody Post post) {
        postService.addPost(post);
    }

    @Operation(summary = "Create a new repost")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new repost",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Already exist this repost",
                    content = @Content)
    })
    @PostMapping(value = "/repost", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRepost(@RequestBody Map<String, String> body) {
        postService.addRepost(body.get("userId"), body.get("postId"));
    }

    @Operation(summary = "Delete a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a post",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
    }

    @Operation(summary = "Get a post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a post ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @GetMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable String postId) {
        return postService.getPostById(postId);
    }

    @Operation(summary = "Get a posts by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of post",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/posts-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUser(@PathVariable String userId) {
        return postService.getAllPostByUserId(userId);
    }

    @Operation(summary = "Get feed for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of post",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/user-feed/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getFeedForUser(@PathVariable String userId) {
        return postService.getFeed(userId);
    }

    @Operation(summary = "Update a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated a post",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @PatchMapping(value = "/post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchPost(@PathVariable String postId, @RequestBody Map<String, String> body) {
        postService.updatePost(postId, body.get("message"));
    }

    @Operation(summary = "Get reposts by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of post",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/reposts-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllRepostsByUser(@PathVariable String userId) {
        return postService.getAllRepostsByUser(userId);
    }

    @Operation(summary = "Get sharers for a post ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @GetMapping(value = "/sharers-post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllSharersByPost(@PathVariable String postId) {
        return postService.getAllUsersByRepost(postId);
    }

}
