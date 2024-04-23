package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.UnprocessableEntityException;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    private PostDAO postRepository;
    private UserDAO userRepository;

    @Autowired
    public PostServiceImpl(PostDAO postRepository, UserDAO userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void addPost(Post post) {
        userRepository.getUserById(post.getCreatorId());
        if (post.getRepostId() != null)
            userRepository.getUserById(post.getSharerId());
        if (post.getSharerId() != null)
            postRepository.getPostById(post.getRepostId());
        postRepository.createPost(post.getCreatorId(), post.getMessage(), post.getTimestamp(), post.getRepostId(), post.getSharerId());
    }

    @Override
    public void addRepost(Map<String, String> body) {
        if(body.get("userId") == null || body.get("postId") == null)
            throw new UnprocessableEntityException("The body is wrong to create a new repost");
        userRepository.getUserById(body.get("userId"));
        Post repost = postRepository.getPostById(body.get("postId"));
        repost.setRepostId(body.get("postId"));
        repost.setSharerId(body.get("userId"));
        postRepository.createRepost(repost);
    }

    @Override
    public void updatePost(String id, Map<String, String> body) {
        postRepository.getPostById(id);
        if(body.get("message") == null)
            throw new UnprocessableEntityException("The body is wrong to update this post");
        postRepository.updateMessage(id, body.get("message"));
    }

    @Override
    public void deletePost(String id) {
        postRepository.getPostById(id);
        postRepository.deletePost(id);
    }

    @Override
    public List<Post> getFeed(String userId) {
        userRepository.getUserById(userId);
        return postRepository.getFeed(userId);
    }

    @Override
    public List<Post> getAllPostByUserId(String userId) {
        userRepository.getUserById(userId);
        return postRepository.getAllPostByUserId(userId);
    }

    @Override
    public Post getPostById(String id) {
        return postRepository.getPostById(id);
    }

    @Override
    public List<User> getAllUsersByRepost(String repostId) {
        postRepository.getPostById(repostId);
        return postRepository.getAllUsersByRepost(repostId);
    }

    @Override
    public List<Post> getAllRepostsByUser(String sharerId) {
        userRepository.getUserById(sharerId);
        return postRepository.getAllRepostsByUser(sharerId);
    }

}
