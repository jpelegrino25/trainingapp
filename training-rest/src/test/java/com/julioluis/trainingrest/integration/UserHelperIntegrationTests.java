package com.julioluis.trainingrest.integration;

import com.julioluis.trainingrest.entities.Authority;
import com.julioluis.trainingrest.entities.Rol;
import com.julioluis.trainingrest.entities.User;
import com.julioluis.trainingrest.utils.BusinessException;
import com.julioluis.trainingrest.utils.UserHelper;
import com.julioluis.trainingrest.utils.prototypes.ModelType;
import com.julioluis.trainingrest.utils.prototypes.PrototypeFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class UserHelperIntegrationTests {

    @Autowired
    private UserHelper userHelper;

    @Test
    public void testAuthorities() throws BusinessException {

        Authority authority = new Authority();
        authority.setDescription("AUTORITY 1");
        List<Authority> authorities= Arrays.asList(authority);

        String [] authoritiesStr=userHelper.authorities(authorities);

        assertNotNull(authoritiesStr);
        assertEquals(true,authoritiesStr.length>0);

    }


    @Test
    public void testToUserDetail() throws BusinessException, CloneNotSupportedException {

        User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
        if(Objects.isNull(user.getRol().getAuthorities())) {
            Authority authority = new Authority();
            authority.setDescription("TRAINING_MAINTANANCE");
            Rol rol=user.getRol();
            rol.setAuthorities(Arrays.asList(authority));
            user.setRol(rol);
        }

        UserDetails userDetails=userHelper.toUserDetail(user);

        assertNotNull(userDetails);
        assertEquals(true,userDetails.isEnabled());
    }

    @Test
    public void testToUserDetail_throws_BusinessException_onNullUsername() {

        assertThrows(BusinessException.class,()-> {
            User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
            user.setUsername(null);

            UserDetails userDetails=userHelper.toUserDetail(user);

        });
    }


    @Test
    public void testToUserDetail_throws_BusinessException_onNullPassword() {

        assertThrows(BusinessException.class,()-> {
            User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
            user.setPassword(null);

            UserDetails userDetails=userHelper.toUserDetail(user);

        });
    }


    @Test
    public void testToUserDetail_throws_BusinessException_onNullRol() {

        assertThrows(BusinessException.class,()-> {
            User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
            user.setRol(null);

            UserDetails userDetails=userHelper.toUserDetail(user);

        });
    }


    @Test
    public void testToUserDetail_throws_BusinessException_onNullAuthorities_on_rol() {

        assertThrows(BusinessException.class,()-> {
            User user=(User) PrototypeFactory.trainingProptotype(ModelType.USER);
            user.getRol().setAuthorities(null);

            UserDetails userDetails=userHelper.toUserDetail(user);

        });
    }

    @Test
    public void testAuthorities_throws_businessException_on_emptyAuthorities() {
        assertThrows(BusinessException.class,()-> {
            List<Authority> authorities= Arrays.asList();
            String [] authoritiesStr=userHelper.authorities(authorities);
        });

    }


}
