package com.ks.service;

import com.ks.dao.UserDao;
import com.ks.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        Optional<User> userOptional = userDao.findOne(Example.of(user));
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
}
