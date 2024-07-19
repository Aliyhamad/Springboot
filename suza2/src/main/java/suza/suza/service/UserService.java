package suza.suza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suza.suza.model.User;
import suza.suza.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepo userRepo;



    public User authentication(String username, String password) {
        Optional<User> login = userRepo.findByUsername(username);
        if (login.isPresent()) {
            User user = login.get();
            if (password.matches(user.getPassword())) {
                return user;
            }

        }
        return null;
    }
    public User post (User user){
        return userRepo.save(user);
    }
    //get
    public List<User> getUser(){
        return userRepo.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepo.findById(id);
    }

    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            return userRepo.save(user);
        } else {
            return null;
        }
    }
}




