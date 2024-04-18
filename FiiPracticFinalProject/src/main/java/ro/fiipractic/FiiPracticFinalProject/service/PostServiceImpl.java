package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostDAO postRepository;
    private UserDAO userRepository;

    @Autowired
    public PostServiceImpl(PostDAO postRepository, UserDAO userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void addPost(Post post){
        userRepository.getUserById(post.getCreatorId());
        postRepository.createPost(post.getCreatorId(), post.getMessage(), post.getTimestamp());
    }

    @Override
    public void updatePost(String id, String message) {
        postRepository.getPostById(id);
        postRepository.updateMessage(id, message);
    }

    @Override
    public void deletePost(String id) {
        postRepository.getPostById(id);
        postRepository.deletePost(id);
    }

    @Override
    public List<Post> getFeed(String userId) {
        return  postRepository.getFeed(userId);
    }

    @Override
    public List<Post> getAllPostByUserId(String userId) {
        return postRepository.getAllPostByUserId(userId);
    }

    @Override
    public Post getPostById(String id) {
        return  postRepository.getPostById(id);
    }

}
