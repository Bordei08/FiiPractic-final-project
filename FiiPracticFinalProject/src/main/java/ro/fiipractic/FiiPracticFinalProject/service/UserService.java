package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.util.List;

public interface UserService {
    public void registerUser(User user);

    public List<User> getAllUsers();
}
