package com.julioluis.trainingrest.services;



import com.julioluis.trainingrest.dto.ResponseDTO;
import com.julioluis.trainingrest.entities.Authority;
import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.Status;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.repositories.RolRepository;
import com.julioluis.trainingrest.repositories.UserRepository;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.StatusEnum;
import com.julioluis.trainingrest.utils.UserHelper;
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

    @Autowired
    private UserHelper userHelper;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(s);

        if(!user.isPresent())
            throw new UsernameNotFoundException(s);

        try {
            UserDetails userDetails  = userHelper.toUserDetail(user.get());
            return userDetails;
        } catch (BusinessException e) {
            return null;
        }

    }

    public User saveUser(User user) throws BusinessException {

           if(Objects.isNull(user.getPassword()))
               throw new BusinessException("User require password to be created");

           if(Objects.isNull(user.getId())) {
               BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
               String pass = encoder.encode(user.getPassword());
               user.setPassword(pass);
               user.setStatus(new Status(StatusEnum.ACTIVE.getStatus()));
           }

           User userSaved=  userRepository.save(user);

        return userSaved;
    }

    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }

        return null;

    }

    public List<User> findAllUser() {
        return userRepository.getAllActiveUsers();
    }

    public List<User> findTrainers() {
        return userRepository.getAllInstructors();
    }

    public User findById(Integer id) {
       Optional<User> user=userRepository.findById(id);
       if (!user.isPresent()) {
           return null;
       }
        return user.get();
    }

    public User deleteUser(Integer id) throws BusinessException {
        User user=findById(id);

        if(Objects.isNull(user))
            throw new BusinessException("Unable to delete user with the id "+ id);

        user.setStatus(new Status(StatusEnum.INACTIVE.getStatus()));
       User userDeleted= userRepository.save(user);

       return userDeleted;

    }

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }
}
