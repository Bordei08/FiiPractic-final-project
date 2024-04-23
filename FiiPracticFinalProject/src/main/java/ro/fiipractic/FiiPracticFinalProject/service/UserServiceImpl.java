package ro.fiipractic.FiiPracticFinalProject.service;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.FiiPracticFinalProject.exception.*;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.UserDAO;
import ro.fiipractic.FiiPracticFinalProject.util.UserUtil;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean verifyObject(User user){
        return !(user.getPassword() == null || user.getUsername() == null || user.getFirstName() == null || user.getLastName()  == null || user.getEmail() == null);
     }

    public void registerUser(User user) throws UsernameAlreadyExistsException {

        if(!verifyObject(user))
            throw new UnprocessableEntityException("The body is wrong to create a new user");

        try{
            userRepository.getUserByUsername(user.getUsername());
        }
        catch (UserNotFoundException e){

            Pattern uppercasePattern = Pattern.compile("[A-Z]");
            Matcher uppercaseMatcher = uppercasePattern.matcher(user.getPassword());
            Pattern digitPattern = Pattern.compile("[0-9]");
            Matcher digitMatcher = digitPattern.matcher(user.getPassword());
            Pattern specialCharPattern = Pattern.compile("[^A-Za-z0-9]");
            Matcher specialCharMatcher = specialCharPattern.matcher(user.getPassword());

            if(user.getPassword().length() < 8 || !uppercaseMatcher.find() || !digitMatcher.find() || !specialCharMatcher.find() ){
                throw  new WeakPasswordException("The Password is weak");
            }

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
        userRepository.getUserById(user.getId());
        userRepository.updateUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getId());
    }

    public User getUserById(String id){
        return userRepository.getUserById(id);
    }

    public void deleteUser(String id){
        userRepository.getUserById(id);
        userRepository.deleteUser(id);
    }

}
