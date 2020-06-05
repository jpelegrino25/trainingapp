package com.julioluis.trainingrest.utils;

import com.julioluis.trainingrest.entities.Authority;
import com.julioluis.trainingrest.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserHelper {

    public String[] authorities(List<Authority> privilageList) throws BusinessException {

        if(Objects.isNull(privilageList) || privilageList.isEmpty())
            throw new BusinessException("At least rol should have one authority");

        String [] authorityList=new String[privilageList.size()];

        for (int i=0;i<privilageList.size();i++) {
            authorityList[i]=privilageList.get(i).getDescription();
        }
        return authorityList;
    }

    public UserDetails toUserDetail(User user) throws BusinessException {
        if(Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword()))
            throw new BusinessException("User required credentials properties");
        if (Objects.isNull(user.getRol()))
            throw new BusinessException("User required Rol property");
        if(Objects.isNull(user.getRol().getAuthorities()))
            throw new BusinessException("Authorities should not be null");

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRol().getDescription())
                .authorities(authorities(user.getRol().getAuthorities()))
                .build();
    }

}
