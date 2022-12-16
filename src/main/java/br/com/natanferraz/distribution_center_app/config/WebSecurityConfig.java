package br.com.natanferraz.distribution_center_app.config;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    DataSource dataSource;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        log.info("[FELIPE]Setting up SecurityFilterChain");
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/").permitAll()
                        .antMatchers("/user/create").hasAuthority("ADMIN")
                        .antMatchers("/user/register").hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                ).formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        //.defaultSuccessUrl("/")
                )
                .logout((logout)->logout
                        .permitAll())
                .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        log.info("[CUSTOM]Passing through users(DataSource)", dataSource );

        String password = "natan";
        UserDetails root = User.builder()
                .username("natan")
                .password(passwordEncoder().encode(password))
                .authorities("ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        log.info("[CUSTOM]Getting users from database", users);
        if(!users.userExists(root.getUsername())){
            users.createUser(root);
            log.info("[PASSWORD]Root user was created with password: " + password);
        }

        return users;
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
