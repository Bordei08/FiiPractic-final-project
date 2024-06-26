package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;
import java.util.Map;

public interface PostService {
    public void addPost(Post post);
    public void addRepost(Map<String, String> body);
    public void updatePost(String id, Map<String, String> body) ;
    public void deletePost(String id);
    public List<Post> getFeed(String userId);
    public List<Post> getAllPostByUserId(String userId);
    public Post getPostById(String id);
    public List<User> getAllUsersByRepost(String repostId);
    public List<Post> getAllRepostsByUser(String sharerId);
}
