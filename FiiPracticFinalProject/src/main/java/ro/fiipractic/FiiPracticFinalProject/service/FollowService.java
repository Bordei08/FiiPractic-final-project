package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.sql.Timestamp;
import java.util.List;

public interface FollowService {
    public void createFollow(String user1Id, String user2Id, Timestamp timestamp);
    public boolean isFollowBetween(String user1Id, String  user2Id);
    public void deleteFollow(String user1Id, String user2Id);
    public List<User> getFollowers(String user1Id);

    public List<User> getFollow(String user2Id);

}
