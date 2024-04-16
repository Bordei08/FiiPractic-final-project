package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.exception.UsernameAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void registerUser(User user) throws UsernameAlreadyExistsException;

    public List<User> getAllUsers();

    public User getUserByUsername(String username);

    public List<User> getUsersByFirstName(String firstName);

    public List<User> getUsersByLastName(String lastName);

    public void checkLogin(String username, String password);

    public void patchUser(String id, Map<String, String> partialUser);

    public User getUserById(String id);

}
