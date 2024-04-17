package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.Reply;

import java.util.List;

public interface ReplyService {

    public void createReply(String userId, String postId, String parentId, String message, boolean varPublic);
    public void deleteReply(String id);

    public Reply getReplyById(String id);

    public List<Reply> getAllRepliesByUserId(String userId);

    public List<Reply> getAllRepliesByPostId(String postId);

    public Reply getParentReplyByParentId(String parentId);

    public List<Reply> getAllRepliesById(String id);

    public void updateReply(String replyId, String message);

}
