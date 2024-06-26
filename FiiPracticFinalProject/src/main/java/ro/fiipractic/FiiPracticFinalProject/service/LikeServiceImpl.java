package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.UnprocessableEntityException;
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


    private boolean verifyObject(Like like){
        return !(like.getPostId() == null || like.getUserId() == null);
    }

    @Override
    public void createLike(Like like){
        if(!verifyObject(like))
            throw new UnprocessableEntityException("The body is wrong to create a new like");
        userRepository.getUserById(like.getUserId());
        postRepository.getPostById(like.getPostId());
        likeRepository.createLike(like.getUserId(), like.getPostId());
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
        postRepository.getPostById(postId);
        return likeRepository.getAllUsersForPost(postId);
    }

    @Override
    public List<Post> getAllPostsByUserLikes(String userId) {
        userRepository.getUserById(userId);
        return likeRepository.getAllPostForUser(userId);
    }
}
