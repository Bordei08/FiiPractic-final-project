package ro.fiipractic.FiiPracticFinalProject.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public FollowController(FollowService followService) {
        this.followService = followService;
    }


    @Operation(summary = "Create a new follow")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a new follow",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Already exist this follow",
                    content = @Content)
    })
    @PostMapping(value = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFollow(@RequestBody Follow follow) {
        followService.createFollow(follow.getUser1Id(), follow.getUser2Id(), follow.getTimestamp());
    }

    @Operation(summary = "Get users that follow user1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/followers/{user1Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFollowers(@PathVariable String user1Id) {
        return followService.getFollowers(user1Id);
    }

    @Operation(summary = "Get users that follow user2")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping(value = "/follow-list/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFollowList(@PathVariable String user2Id) {
        return followService.getFollow(user2Id);
    }

    @Operation(summary = "Get a follow by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a follow",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Follow.class))}),
            @ApiResponse(responseCode = "404", description = "Follow not found",
                    content = @Content)
    })
    @GetMapping(value = "/follow/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Follow getFollowById(@PathVariable String id) {
        return followService.getFollowById(id);
    }


    @Operation(summary = "Delete a follow")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a follow",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Follow not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/follow/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollow(@PathVariable String id) {
        followService.deleteFollow(id);
    }

}
