package com.example.demo2.auth.auth;

import java.util.ArrayList;

import com.example.demo2.auth.auth.InDatabase.DAOUser;
import com.example.demo2.auth.auth.InDatabase.UserDao;
import com.example.demo2.repos.UserRepoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepoCategory userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser save(DAOUser user){
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }

    public DAOUser loadByUserName(DAOUser username, Long id){
        DAOUser newUser;
        // TODO вот это канеш плохо. Но для двух таблиц только так пока видится
        // ошибка может возникнуть в случае если контроллер тормознет и быстрее выполнится второй репозиторий
        // опять же при условии что они непоследовательны
        // а не, они последовательны, логично. Но все равно это неправильно
        // надо смердживать в одну таблицу. не дело это все.
        newUser = userDao.findByUsername(userRepository.findById(id).get().getEmail());
        newUser.setPassword(bcryptEncoder.encode(username.getPassword()));
        newUser.setUsername(username.getUsername());
        return userDao.save(newUser);
    }
}