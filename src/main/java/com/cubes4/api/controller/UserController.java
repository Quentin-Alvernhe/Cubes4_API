package com.cubes4.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.cubes4.api.dao.UserDao;
import com.cubes4.api.model.User;

@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userDao.findByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {

        User user = userDao.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<User>(userDao.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {

        try {
            if (user.getId() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Optional<User> userToUpdate = userDao.findById(user.getId());
            if (userToUpdate.isEmpty() || userToUpdate.get() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return new ResponseEntity<User>(userDao.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {

        User userToDelete = userDao.findById(id).orElse(null);
        if (userToDelete == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userDao.delete(userToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
