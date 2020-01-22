package com.mykart.service.user;

import com.mykart.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(int user_id);
    public User saveUser(User emp);
    public void deleteUser(User emp);
    public User updateUser(User emp);
 //   public List<User> findByManager(int mgr_id);
}
