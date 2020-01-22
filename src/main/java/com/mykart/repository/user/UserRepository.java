package com.mykart.repository.user;

import com.mykart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findById(int user_id);
}
