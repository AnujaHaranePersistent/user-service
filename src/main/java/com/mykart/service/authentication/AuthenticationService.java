package com.mykart.service.authentication;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationRepository authRepository;


    public Authentication getUserByUsername(String username)
    {
        return authRepository.findByUsername(username);
    }
    public Authentication saveUsername(Authentication auth)
    {
        return authRepository.save(auth);
    }
    public Authentication findById(int user_id )
    {
        return authRepository.findById(user_id);
    }
}
