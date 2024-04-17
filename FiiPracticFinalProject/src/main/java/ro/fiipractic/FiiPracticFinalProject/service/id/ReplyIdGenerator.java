package ro.fiipractic.FiiPracticFinalProject.service.id;

public interface ReplyIdGenerator {
    public String generateReplyId(String userId, String postId, String parentId);
}
