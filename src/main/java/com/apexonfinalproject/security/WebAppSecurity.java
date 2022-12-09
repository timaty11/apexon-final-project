package com.apexonfinalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebAppSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT login, password, activated from users WHERE login=?")
                .authoritiesByUsernameQuery("SELECT users.login as username, roles.role_name as role " +
                        "FROM users " +
                        "INNER JOIN user_roles ON users.id = user_roles.user_id " +
                        "INNER JOIN roles ON user_roles.role_id = roles.id " +
                        "WHERE users.login = ?  ")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/auth/authorized").authenticated()
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .antMatchers("/admin/control-panel").hasAnyAuthority("ADMIN", "MANAGER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/auth/authorized", true)
                .failureUrl("/auth/login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .and()
                .exceptionHandling()
                .accessDeniedPage("/ad");
    }
}