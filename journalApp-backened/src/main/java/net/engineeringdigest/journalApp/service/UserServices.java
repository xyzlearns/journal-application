package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServices {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user){
        repository.save(user);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User findUserById(ObjectId id){
        return repository.findById(id).orElse(null);
    }

    public void deleteUserById(ObjectId id){
        repository.deleteById(id);
    }
}
