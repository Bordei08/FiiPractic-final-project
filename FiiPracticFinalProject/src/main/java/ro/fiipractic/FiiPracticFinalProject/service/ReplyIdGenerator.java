package ro.fiipractic.FiiPracticFinalProject.service;

public interface ReplyIdGenerator {
    public String generateReplyId(String userId, String postId, String parentId);
}
