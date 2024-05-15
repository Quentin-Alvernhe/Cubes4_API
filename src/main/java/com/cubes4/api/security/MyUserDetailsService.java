package com.cubes4.api.security;

import com.cubes4.api.dao.UserDao;
import com.cubes4.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userDao
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inexistant"));

        return new MyUserDetails(user);
    }
}
