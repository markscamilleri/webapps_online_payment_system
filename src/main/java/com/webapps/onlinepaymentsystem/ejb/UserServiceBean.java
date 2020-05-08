/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.UserJpaDao;
import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
@RolesAllowed({"admin"})
public class UserServiceBean implements UserService {
    
    @EJB(name="UserDao")
    UserJpaDao userDao;   
    
    @EJB
    TimeClientService timeClientService;
    
    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public List<UserDto> getAllAdmins() {
        return userDao.getByRoleName("admin");
    }

    @Override
    public UserDto getUserById(long userId) throws UserNotFoundException {
        return userDao.getById(userId).orElseThrow(() -> new UserNotFoundException(Long.toString(userId)));
    }

    @Override
    @PermitAll
    public UserDto getUserByUsername(String username) throws UserNotFoundException {
        return userDao.getByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        return userDao.getByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @PermitAll
    @Transactional
    public void registerNewUser(String username, String email, String password, String userGroup) {
        try {
            UserDto newUser = new UserDto();
            
            newUser.email = email;
            newUser.username = username;
            newUser.registrationTimestamp = timeClientService.getTimeFromTimeServer();
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hashedPassword = sb.toString();
            
            newUser.encryptedPassword = hashedPassword;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
