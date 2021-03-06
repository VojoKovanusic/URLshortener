package com.shortener.url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shortener.url.model.User;
import com.shortener.url.util.Util;

@Service
public class UserServiceImpl implements UserService {


    private List<User> users;
    private Util util;

    @Autowired
    public UserServiceImpl(List<User> users, Util util) {
        this.users = users;
        this.util = util;
    }


    @Override
    public User saveUser(User user) {
        String password = this.util.getPasswordHash(user.getPassword());
        user.setPassword(password);
        users.add(user);
        return user;
    }




    @Override
    public User findUserByAccountId(String accountId) {
        User user = getUser(accountId);

        if (user == null) {
            throw new UsernameNotFoundException(accountId);
        }

        return user;

    }
    @Override
    public User getUser(String accountId) {
        return this.users.stream()
                .filter(u -> u.getAccountId()
                        .equals(accountId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isUserExist(User user) {

        return this.users.stream().anyMatch(u -> u.getAccountId().equals(user.getAccountId()));
    }

    @Override
    public boolean isLoginUser(String accountId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String currentlyLoggingID = ((UserDetails) principal).getUsername();
            if (accountId.equals(currentlyLoggingID))
                return true;
        }
        return false;
    }

    @Override
    public User getCurrentUser() {

        return findUserByAccountId(getCurrentlyLoggingID());
    }

    @Override
    public String getCurrentlyLoggingID() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            ((UserDetails) principal).getUsername();
            return ((UserDetails) principal).getUsername();
        }
        return null;

    }
}
