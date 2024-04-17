package ro.fiipractic.FiiPracticFinalProject.service;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.InvalidPasswordException;
import ro.fiipractic.FiiPracticFinalProject.exception.UserNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.exception.UsernameAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;
import ro.fiipractic.FiiPracticFinalProject.util.UserUtil;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService{

    private UserDAO userRepository;
    private PasswordEncryptionService passwordEncryptionService;
    private UserUtil userUtil;

    @Autowired
    public UserServiceImpl(UserDAO userRepository, PasswordEncryptionService passwordEncryptionService, UserUtil userUtil){
        this.passwordEncryptionService = passwordEncryptionService;
        this.userRepository= userRepository;
        this.userUtil = userUtil;
    }

    public void registerUser(User user) throws UsernameAlreadyExistsException {
        try{
            userRepository.getUserByUsername(user.getUsername());
        }
        catch (UserNotFoundException e){
            userRepository.createUser(user.getUsername() ,user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncryptionService.eencryptPassword(user.getPassword()));
            return;
        }

        throw new UsernameAlreadyExistsException("This username is already in use");
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

    public List<User> getUsersByFirstName(String firstName){
        return userRepository.getUsersByFirstName(firstName);
    }

    public  List<User> getUsersByLastName(String lastName) {
        return userRepository.getUsersByLastName(lastName);
    }

    public void patchUser(String id, Map<String, String> partialUser){
        User user = userRepository.getUserById(id);
        userUtil.patchUser(user,partialUser);
        userRepository.updateUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getId());
    }

    public User getUserById(String id){
        return userRepository.getUserById(id);
    }

    public void deleteUser(String id){
        userRepository.deleteUser(id);
    }

}
