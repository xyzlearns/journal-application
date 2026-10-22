package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserServices {

    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

    public void saveNewUser(User user){
        try {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Arrays.asList("USER"));
            repository.save(user);
        } catch (Exception e) {
            logger.info("exception happening");
            throw new RuntimeException("an error occured");
        }
    }

    public void saveExistingUser(User user){
        repository.save(user);
    }


    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User findByUserName(String userName){
        return repository.findByUserName(userName);
    }

    public ResponseEntity<?> findUserById(ObjectId id){
        User user = repository.findById(id).orElse(null);
        if(user!=null) return new ResponseEntity<>(user,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateUser(String userName, User incomingUser){
        User user = repository.findByUserName(userName);
        user.setUserName(incomingUser.getUserName());
        user.setPassword(passwordEncoder.encode(incomingUser.getPassword()));
        saveExistingUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public void deleteUserById(ObjectId id){
        repository.deleteById(id);
    }

    public ResponseEntity<?> saveAdmin(User user) {
        try {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            repository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
