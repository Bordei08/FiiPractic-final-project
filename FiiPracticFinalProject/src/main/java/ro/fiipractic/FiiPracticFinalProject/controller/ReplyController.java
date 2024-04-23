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
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.service.ReplyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class ReplyController {
    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    @Operation(summary = "Create a new reply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new reply",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "The body is wrong to create a new reply",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Already exist this reply",
                    content = @Content)
    })
    @PostMapping(value = "/reply", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createReply(@RequestBody Reply reply) {
        replyService.createReply(reply);
    }

    @Operation(summary = "Delete a reply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted reply",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reply not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReply(@PathVariable String replyId) {
        replyService.deleteReply(replyId);
    }


    @Operation(summary = "Get a reply by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reply",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reply.class))}),
            @ApiResponse(responseCode = "404", description = "Reply not found",
                    content = @Content)
    })
    @GetMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reply getReplyById(@PathVariable String replyId) {
        return replyService.getReplyById(replyId);
    }


    @Operation(summary = "Get all replies by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of all replies posted by a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reply.class))})
    })
    @GetMapping(value = "/replies-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getAllRepliesByUser(@PathVariable String userId) {
        return replyService.getAllRepliesByUserId(userId);
    }

    @Operation(summary = "Get all replies by post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of all replies posted at a post",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reply.class))})
    })
    @GetMapping(value = "/replies-post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getAllRepliesByPost(@PathVariable String postId) {
        return replyService.getAllRepliesByPostId(postId);
    }

    @Operation(summary = "Get a \"parent\" reply by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reply",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reply.class))}),
            @ApiResponse(responseCode = "404", description = "Reply not found",
                    content = @Content)
    })
    @GetMapping(value = "/reply-parent/{parentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reply getParentReply(@PathVariable String parentId) {
        return replyService.getParentReplyByParentId(parentId);
    }

    @Operation(summary = "Get all replies by \"parent\" reply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of all replies posted at another reply",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reply.class))})
    })
    @GetMapping(value = "/replies/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getReplies(@PathVariable String replyId) {
        return replyService.getAllRepliesById(replyId);
    }


    @Operation(summary = "Update a reply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated reply",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "The body is wrong to update this reply",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reply not found",
                    content = @Content)
    })
    @PatchMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchReply(@PathVariable String replyId, @RequestBody Map<String, String> body) {
        replyService.updateReply(replyId,body);
    }

}
