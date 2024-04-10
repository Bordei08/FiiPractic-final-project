package ro.fiipractic.FiiPracticFinalProject.service;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.InvalidPasswordException;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    private UserDAO userRepository;
    private PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public UserServiceImpl(UserDAO userRepository, PasswordEncryptionService passwordEncryptionService){
        this.passwordEncryptionService = passwordEncryptionService;
        this.userRepository= userRepository;
    }

    public void registerUser(User user){
        userRepository.createUser(user.getUsername() ,user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncryptionService.eencryptPassword(user.getPassword()));
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public void  checkLogin(String username, String password){
        User user = getUserByUsername(username);
        if(!passwordEncryptionService.verifyPassword(password, user.getPassword()))
            throw new InvalidPasswordException("Wrong password");

    }

}
