package com.example.mfu.config;

import com.example.mfu.entities.Role;
import com.example.mfu.services.UserService;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Define the authentication provider bean
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Define the password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the SecurityFilterChain bean for configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/users/new").hasAuthority(Role.ADMIN.name())
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/auth")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // Define AuthenticationManager bean if needed elsewhere in the app
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }

//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Basic
//    private AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/users/new").hasAuthority(Role.ADMIN.name())
//                .anyRequest().permitAll()
//                .and()
//                    .formLogin
//                    .loginPage("/login")
//                    .loginProcessingUrl("/auth")
//                    .permitAll()
//                .and()
//                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
//                    .invalidateHttpSession(true)
//                .and()
//                    .csrf().disable();
//
//    }


}
