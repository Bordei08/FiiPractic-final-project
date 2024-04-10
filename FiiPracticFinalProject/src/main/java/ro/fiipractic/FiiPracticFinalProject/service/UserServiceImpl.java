package ro.fiipractic.FiiPracticFinalProject.service;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    private UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository){
        this.userRepository= userRepository;
    }

    public void registerUser(User user){
        userRepository.createUser(user.getUsername() ,user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
}
