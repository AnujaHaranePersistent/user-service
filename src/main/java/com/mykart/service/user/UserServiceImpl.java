package com.mykart.service.user;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import com.mykart.repository.user.UserRepository;

import com.mykart.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int user_id) {
        return userRepository.findById(user_id);
    }

    @Override
    public User saveUser(User user) {
        String result = RandomStringUtils.random(10, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0XABC1DeLMnOpRSTvW".toCharArray());
       user.setCart_id(result);
        User savedUser= userRepository.save(user);
        Authentication auth=new Authentication(user.getUser_id(),user.getFirst_name()+user.getUser_id(),user.getPassword());
        authService.saveUsername(auth);
        return savedUser;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
