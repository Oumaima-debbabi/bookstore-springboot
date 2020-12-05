package com.example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.SecurityConfig;
import com.example.demo.entities.MyUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.entities.Panier;
import java.util.List;
import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    PanierService panierSercie;
    @Autowired
    UserRepository userRepository;
  SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByUserName(userName);

        user.orElseThrow(() ->
                new UsernameNotFoundException("User not found:  " + userName));

//        return user.map(MyUserDetails::new).get();
        return new MyUserDetails(user.get());
    }
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
				 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity addUser(UserEntity user) throws Exception {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) throw new Exception("This " +
                "username " +
                "already exist");
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setActive(true);
        return userRepository.save(user);
    }

    public UserEntity addAdmin(UserEntity user) throws Exception {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) throw new Exception("This username " +
                "already exist");
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("ROLE_ADMIN");
        user.setActive(true);
        return userRepository.save(user);
    }

    public UserEntity deleteUser(Long userId) {
        UserEntity utl=
                userRepository.findById(userId)
				 .orElseThrow(()-> new IllegalArgumentException("Il n'existe pas"));
        List<Panier> c= panierSercie.getUserCommands(userId);
        for (Panier com: c)
        	panierSercie.deleteCommand(com.getId());
        userRepository.deleteById(userId);
        return utl;
    }

    public UserEntity updateUserName(Long userId, UserEntity user){
        UserEntity u = getUserById(userId);
        u.setUserName(user.getUserName());
        return userRepository.save(u);
    }
}
