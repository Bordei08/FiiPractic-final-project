package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.LikeDAO;

import java.util.List;

@Service
public class LikeServiceImpl  implements  LikeService{
    private LikeDAO likeRepository;


    @Autowired
    public LikeServiceImpl(LikeDAO likeRepository){
        this.likeRepository = likeRepository;
    }

    @Override
    public void createLike(String userId, String postId) {
            likeRepository.createLike(userId, postId);
    }

    @Override
    public void deleteLike(String id) {
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
