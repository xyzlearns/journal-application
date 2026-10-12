package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServices {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user){
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
        user.setPassword(incomingUser.getPassword());
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public void deleteUserById(ObjectId id){
        repository.deleteById(id);
    }
}
