package com.julioluis.trainingrest.services;



import com.julioluis.trainingrest.entities.Authority;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(s);

        if(!user.isPresent())
            throw new UsernameNotFoundException(s);

        User myUser=user.get();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        boolean match=encoder.matches("password",myUser.getPassword());
        System.out.println(myUser.getPassword());
        return toUserDetail(user.get());
    }

    private UserDetails toUserDetail(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRol().getDescription())
                .authorities(this.authorities(user.getRol().getAuthorities()))
                .build();
    }

    private String[] authorities(List<Authority> privilageList) {

        String [] authorityList=new String[privilageList.size()];

        for (int i=0;i<privilageList.size();i++) {
            authorityList[i]=privilageList.get(i).getDescription();
        }
        return authorityList;
    }


    public void saveUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String pass=encoder.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            return user.get();

        }catch (Exception ex) {
            return null;
        }

    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
