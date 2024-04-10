package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;

public interface UserService {
    public void registerUser(User user);

    public List<User> getAllUsers();

    public User getUserByUsername(String username);

    public List<User> getUsersByFirstName(String firstName);

    public List<User> getUsersByLastName(String lastName);

    public void checkLogin(String username, String password);
}
