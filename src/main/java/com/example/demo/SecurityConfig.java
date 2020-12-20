//package com.example.demo;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.entities.UserEntity;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.services.MyUserDetailsService;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(getUserDetailsService());
// }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user").
//                hasAnyRole("USER", "ADMIN")
//                .antMatchers("/").permitAll()
//                .and().formLogin()
//        ;
//    }
//
//    @Bean
//    public UserDetailsService getUserDetailsService() {
//        return new MyUserDetailsService();
//    }
//
//    @PostConstruct
//    public void init() {
//    if(userRepository.findAll().size()==0){
//        UserEntity entity = new UserEntity();
//        entity.setActive(true);
//        entity.setUserName("admin");
//        entity.setPassword(passwordEncoder.encode("admin"));
//        entity.setRoles("ROLE_ADMIN,ROLE_USER");
//        userRepository.save(entity);
//    }
//
//
//    }
//}
