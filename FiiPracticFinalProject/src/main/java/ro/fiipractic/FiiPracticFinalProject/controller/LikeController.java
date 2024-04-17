package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLike(@RequestBody Like like){
        likeService.createLike(like.getUserid(), like.getPostId());
    }

    @GetMapping(value = "/like/{likeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Like getLikeById(@PathVariable String likeId){
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping(value = "/like/{likeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLike(@PathVariable String likeId){
        likeService.deleteLike(likeId);
    }
    @GetMapping(value = "/posts-user-likes/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUserLikes(@PathVariable String userId){
        return likeService.getAllPostsByUserLikes(userId);
    }

    @GetMapping(value = "/users-post-likes/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByPostLikes(@PathVariable String postId){
        return likeService.getAllUsersByPostLikes(postId);
    }

}
