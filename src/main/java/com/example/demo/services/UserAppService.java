package com.example.demo.services;

import com.example.demo.models.UserApp;
import com.example.demo.repositories.UserAppRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppService {

    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private JwtAuthentificationService jwtAuthentificationService;
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public void createUserApp(UserApp userApp, String role) throws Exception {
        Optional<UserApp> userAppOptional = userAppRepository.findByUsername(userApp.getUsername());
        if(userAppOptional.isPresent() && bcrypt.matches(userAppOptional.get().getPassword(), userApp.getPassword()) ){
            throw new Exception();
        }

        userAppRepository.save(new UserApp(userApp.getUsername(), bcrypt.encode(userApp.getPassword()), role));
    }

    public ResponseCookie logUserApp(UserApp userApp) throws Exception {
        Optional<UserApp> userAppOptional = userAppRepository.findByUsername(userApp.getUsername());
        if(userAppOptional.isPresent() && bcrypt.matches( userApp.getPassword(),userAppOptional.get().getPassword()) ){
            return jwtAuthentificationService.generateToken(userApp.getUsername());
        }
        throw new Exception();
    }
    
    public UserApp findByUserName(String username){
        return userAppRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("utilisateur non trouvé"));
    }
}
