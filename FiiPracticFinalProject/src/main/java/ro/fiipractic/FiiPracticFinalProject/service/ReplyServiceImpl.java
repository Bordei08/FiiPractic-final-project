package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.repository.ReplyDAO;

import java.util.List;

@Service
public class ReplyServiceImpl implements  ReplyService {

    private ReplyDAO replyRepository;


    @Autowired
    public ReplyServiceImpl(ReplyDAO replyRepository){
        this.replyRepository = replyRepository;
    }

    @Override
    public void createReply(String userId, String postId, String parentId, String message, boolean varPublic) {
        replyRepository.createReply(postId, parentId, userId, message, varPublic);
    }

    @Override
    public void deleteReply(String id) {
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

    public void updateReply(String replyId, String message){
        replyRepository.updateMessage(replyId, message);
    }

}
