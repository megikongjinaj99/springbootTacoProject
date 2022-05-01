package com.megiapp.springjwt.security.services;

import com.megiapp.springjwt.models.User;
import com.megiapp.springjwt.payload.request.UserUpdateRequest;
import com.megiapp.springjwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public Optional<User> update(UserUpdateRequest user) throws Exception {
        // First get the User from DB
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            return userRepository.findById(user.getId())
                    .map(userToBeUpdated -> {
                        userToBeUpdated.setPhone(user.getPhone());
                        return userRepository.save(userToBeUpdated);
                    });
        }
        return null;
    }
}
