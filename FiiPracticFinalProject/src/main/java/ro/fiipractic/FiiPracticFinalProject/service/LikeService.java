package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;

public interface LikeService {
    public void createLike(String userId, String postId);

    public void deleteLike(String id);

    public Like getLikeById(String id);

    public List<User> getAllUsersByPostLikes(String postId);

    public List<Post> getAllPostsByUserLikes(String userId);
}
