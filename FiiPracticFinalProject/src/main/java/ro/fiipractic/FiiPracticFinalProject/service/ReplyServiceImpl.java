package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.UnprocessableEntityException;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.ReplyDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;
import java.util.Map;

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


    private boolean verifyObject(Reply reply){
        return !(reply.getVarPublic() == null || reply.getPostId() ==  null || reply.getUserId() == null || reply.getMessage() == null);
    }

    @Override
    public void createReply(Reply reply) {
        if(!verifyObject(reply))
            throw new UnprocessableEntityException("The body is wrong to create a new reply");

        userRepository.getUserById(reply.getUserId());
        postRepository.getPostById(reply.getPostId());
        if (reply.getParentId() != null)
            replyRepository.getReplyById(reply.getParentId());
        replyRepository.createReply(reply.getPostId(), reply.getParentId(), reply.getUserId(), reply.getMessage(), reply.getVarPublic());
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
        userRepository.getUserById(userId);
        return replyRepository.getAllRepliesByUserId(userId);
    }

    @Override
    public List<Reply> getAllRepliesByPostId(String postId) {
        postRepository.getPostById(postId);
        return replyRepository.getAllRepliesByPostId(postId);
    }

    @Override
    public Reply getParentReplyByParentId(String parentId) {
        return replyRepository.getParentReplyByParentId(parentId);
    }

    @Override
    public List<Reply> getAllRepliesById(String id) {
        replyRepository.getReplyById(id);
        return replyRepository.getAllRepliesById(id);
    }

    public void updateReply(String replyId, Map<String, String> body) {
        replyRepository.getReplyById(replyId);
        if(body.get("message") == null)
            throw new UnprocessableEntityException("The body is wrong to update this reply");
        replyRepository.updateMessage(replyId, body.get("message"));
    }

}
