package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.LikeDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.PostDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;

@Service
public class LikeServiceImpl  implements  LikeService{
    private LikeDAO likeRepository;
    private PostDAO postRepository;
    private UserDAO userRepository;


    @Autowired
    public LikeServiceImpl(LikeDAO likeRepository, PostDAO postRepository, UserDAO userRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createLike(String userId, String postId) {
        userRepository.getUserById(userId);
        postRepository.getPostById(postId);
        likeRepository.createLike(userId, postId);
    }

    @Override
    public void deleteLike(String id) {
        likeRepository.getLikeById(id);
        likeRepository.deleteLike(id);
    }

    @Override
    public Like getLikeById(String id) {
        return likeRepository.getLikeById(id);
    }

    @Override
    public List<User> getAllUsersByPostLikes(String postId) {
        return likeRepository.getAllUsersForPost(postId);
    }

    @Override
    public List<Post> getAllPostsByUserLikes(String userId) {
        return likeRepository.getAllPostForUser(userId);
    }
}
