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
    public MentionController(MentionService mentionService) {
        this.mentionService = mentionService;
    }


    @Operation(summary = "Create a new mention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new mention",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Already exist this mention",
                    content = @Content)
    })
    @PostMapping(value = "/mention", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createMention(@RequestBody Mention mention) {
        mentionService.createMention(mention.getUserId(), mention.getPostId());
    }

    @Operation(summary = "Delete a mention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a mention",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Mention not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/mention/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMention(@PathVariable String id) {
        mentionService.deleteMention(id);
    }

    @Operation(summary = "Get a mention by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a mention",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Mention.class))}),
            @ApiResponse(responseCode = "404", description = "Mention not found",
                    content = @Content)
    })
    @GetMapping(value = "/mention/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mention getMentionById(@PathVariable String id) {
        return mentionService.getMentionById(id);
    }


    @Operation(summary = "Get users by post mentions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content)
    })
    @GetMapping(value = "/users-post-mentions/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByPostMentions(@PathVariable String postId) {
        return mentionService.getAllUsersByPostMentions(postId);
    }

    @Operation(summary = "Get posts by user mentions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of posts",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/posts-user-mentions/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUserMentions(@PathVariable String userId) {
        return mentionService.getAllPostsByUserMentions(userId);
    }

}
