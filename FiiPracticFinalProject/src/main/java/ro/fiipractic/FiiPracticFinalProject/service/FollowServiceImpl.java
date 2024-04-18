package ro.fiipractic.FiiPracticFinalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.Follow;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.FollowDAO;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private FollowDAO followRepository;
    private UserDAO userRepository;

    @Autowired
    public FollowServiceImpl(FollowDAO followRepository, UserDAO userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createFollow(String user1Id, String user2Id, Timestamp timestamp) {
        userRepository.getUserById(user1Id);
        userRepository.getUserById(user2Id);
        followRepository.createNewFollower(user1Id, user2Id, timestamp);
    }

    @Override
    public boolean isFollowBetween(String user1Id, String user2Id) {
        return followRepository.getFollowByUser1IdAndUser2Id(user1Id, user2Id);
    }

    @Override
    public void deleteFollow(String id) {
        followRepository.getFollowById(id);
        followRepository.deleteFollower(id);
    }

    @Override
    public List<User> getFollowers(String user1Id) {
        return followRepository.getFollowers(user1Id);
    }

    @Override
    public Follow getFollowById(String id) {
        return followRepository.getFollowById(id);
    }

    @Override
    public List<User> getFollow(String user2Id) {
        return followRepository.getFollow(user2Id);
    }
}
