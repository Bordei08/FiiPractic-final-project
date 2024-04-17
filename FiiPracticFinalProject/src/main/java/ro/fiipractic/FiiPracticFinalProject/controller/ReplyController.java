package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class ReplyController {
    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping(value = "/reply", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createReply(@RequestBody Reply reply) {
        replyService.createReply(reply.getUserId(), reply.getPostId(), reply.getParentId(), reply.getMessage(), reply.getVarPublic());
    }

    @DeleteMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReply(@PathVariable String replyId) {
        replyService.deleteReply(replyId);
    }

    @GetMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reply getReplyById(@PathVariable String replyId) {
        return replyService.getReplyById(replyId);
    }

    @GetMapping(value = "/replies-user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getAllRepliesByUser(@PathVariable String userId) {
        return replyService.getAllRepliesByUserId(userId);
    }

    @GetMapping(value = "/replies-post/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getAllRepliesByPost(@PathVariable String postId) {
        return replyService.getAllRepliesByPostId(postId);
    }

    @GetMapping(value = "/reply-parent/{parentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reply getParentReply(@PathVariable String parentId) {
        return replyService.getParentReplyByParentId(parentId);
    }

    @GetMapping(value = "/replies/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reply> getReplies(@PathVariable String replyId) {
        return replyService.getAllRepliesById(replyId);
    }

    @PatchMapping(value = "/reply/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchReply(@PathVariable String replyId, @RequestBody String message){
        replyService.updateReply(replyId, message);
    }

}
