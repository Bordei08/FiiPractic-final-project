package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Mention;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.MentionDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;
@Service
public class MentionServiceImpl implements  MentionService {

    private MentionDAO mentionRepository;
    private UserDAO userRepository;
    private PostDAO postRepository;

    @Autowired
    public MentionServiceImpl(MentionDAO mentionRepository, UserDAO userRepository, PostDAO postRepository){
        this.mentionRepository = mentionRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createMention(String userId, String postId) {
        userRepository.getUserById(userId);
        postRepository.getPostById(postId);
        mentionRepository.createMention(userId, postId);
    }

    @Override
    public void deleteMention(String id) {
            mentionRepository.getMentionById(id);
            mentionRepository.deleteMention(id);
    }

    @Override
    public Mention getMentionById(String id) {
        return  mentionRepository.getMentionById(id);
    }

    @Override
    public List<User> getAllUsersByPostMentions(String postId) {
        postRepository.getPostById(postId);
        return mentionRepository.getAllUsersByPostMentions(postId);
    }

    @Override
    public List<Post> getAllPostsByUserMentions(String userId) {
        userRepository.getUserById(userId);
        return mentionRepository.getAllPostsByUserMentions(userId);
    }
}
