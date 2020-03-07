package com.julioluis.trainingrest.services;



import com.julioluis.trainingrest.entities.Authority;
import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.RolRepository;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;




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
        if(Objects.isNull(user.getId())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pass = encoder.encode(user.getPassword());
            user.setPassword(pass);
            user.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));
        }
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
        return userRepository.getAllActiveUsers();
    }

    public List<User> findTrainers() {
        return userRepository.getAllInstructors();
    }

    public User findById(Integer id) {
       Optional<User> user=userRepository.findById(id);
        return user.get();
    }

    public void deleteUser(Integer id) {
        User user=findById(id);

        user.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));
        userRepository.save(user);

    }

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }
}
