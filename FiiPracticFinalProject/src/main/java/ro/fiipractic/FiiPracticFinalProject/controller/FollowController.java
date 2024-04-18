package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.FiiPracticFinalProject.models.Follow;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.FollowService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/fiipractic-final-project")
public class FollowController {
    private FollowService followService;

    @Autowired
    public FollowController(FollowService followService){
        this.followService= followService;
    }


    @PostMapping(value = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFollow(@RequestBody Follow follow) {
       followService.createFollow(follow.getUser1Id(), follow.getUser2Id(), follow.getTimestamp());
    }

    @GetMapping(value = "/followers/{user1Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFollowers(@PathVariable String user1Id) {
        return followService.getFollowers(user1Id);
    }

    @GetMapping(value = "/follow-list/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFollowList(@PathVariable String user2Id) {
        return followService.getFollow(user2Id);
    }

    @GetMapping(value = "/follow/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Follow getFollowById(@PathVariable String id){
        return followService.getFollowById(id);
    }


    @DeleteMapping(value = "/follow/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollow(@PathVariable String id) {
        followService.deleteFollow(id);
    }

}
