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
import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Mention;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @Operation(summary = "Create a new like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "like a new mention",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "The body is wrong to create a new like",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Already exist this like",
                    content = @Content)
    })
    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLike(@RequestBody Like like) {
        likeService.createLike(like);
    }

    @Operation(summary = "Get a like by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a like",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Like.class))}),
            @ApiResponse(responseCode = "404", description = "Like not found",
                    content = @Content)
    })
    @GetMapping(value = "/like/{likeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Like getLikeById(@PathVariable String likeId) {
        return likeService.getLikeById(likeId);
    }

    @Operation(summary = "Delete a like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a like",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Like not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/like/{likeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLike(@PathVariable String likeId) {
        likeService.deleteLike(likeId);
    }


    @Operation(summary = "Get posts by user likes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of post",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @GetMapping(value = "/posts-user-likes/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUserLikes(@PathVariable String userId) {
        return likeService.getAllPostsByUserLikes(userId);
    }

    @Operation(summary = "Get users by post likes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @GetMapping(value = "/users-post-likes/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByPostLikes(@PathVariable String postId) {
        return likeService.getAllUsersByPostLikes(postId);
    }

}
