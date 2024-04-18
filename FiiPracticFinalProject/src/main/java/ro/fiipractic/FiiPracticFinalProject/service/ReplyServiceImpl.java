package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.ReplyDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyRepository;
    private UserDAO userRepository;
    private PostDAO postRepository;

    @Autowired
    public ReplyServiceImpl(ReplyDAO replyRepository, UserDAO userRepository, PostDAO postRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createReply(String userId, String postId, String parentId, String message, boolean varPublic) {
        userRepository.getUserById(userId);
        postRepository.getPostById(postId);
        if (parentId != null)
            replyRepository.getReplyById(parentId);
        replyRepository.createReply(postId, parentId, userId, message, varPublic);
    }

    @Override
    public void deleteReply(String id) {
        replyRepository.getReplyById(id);
        replyRepository.deleteReply(id);
    }

    @Override
    public Reply getReplyById(String id) {
        return replyRepository.getReplyById(id);
    }

    @Override
    public List<Reply> getAllRepliesByUserId(String userId) {
        return replyRepository.getAllRepliesByUserId(userId);
    }

    @Override
    public List<Reply> getAllRepliesByPostId(String postId) {
        return replyRepository.getAllRepliesByPostId(postId);
    }

    @Override
    public Reply getParentReplyByParentId(String parentId) {
        return replyRepository.getParentReplyByParentId(parentId);
    }

    @Override
    public List<Reply> getAllRepliesById(String id) {
        return replyRepository.getAllRepliesById(id);
    }

    public void updateReply(String replyId, String message) {
        replyRepository.updateMessage(replyId, message);
    }

}
