package com.example.demo.auth;

import static com.example.demo.auth.user.UserPermission.*;
import static com.example.demo.auth.user.UserRole.*;
import static com.example.demo.util.Constants.*;

import com.example.demo.auth.filters.AuthenticationFilter;
import com.example.demo.auth.filters.LoginFilter;
import com.example.demo.auth.filters.LogoutFilter;
import com.example.demo.auth.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  public SecurityConfig(
    PasswordEncoder passwordEncoder,
    UserService userService
  ) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .disable()
      .csrf()
      .disable()
      .formLogin()
      .disable()
      .httpBasic()
      .disable()
      .logout()
      .disable()
      .authorizeRequests()
      .antMatchers("/", SWAGGER_UI_ENDPOINT_1, SWAGGER_UI_ENDPOINT_2) // Allow swagger endpoint
      .permitAll()
      .antMatchers(READ_ENDPOINT)
      .hasAuthority(READ_PERMISSION.getPermission())
      .antMatchers(WRITE_ENDPOINT)
      .hasAuthority(WRITE_PERMISSION.getPermission())
      .antMatchers(MANAGER_ONLY_ENDPOINT)
      .hasRole(MANAGER.name())
      .and()
      .addFilterBefore( // Login
        new LoginFilter(LOGIN_ENDPOINT, authenticationManager()),
        UsernamePasswordAuthenticationFilter.class
      )
      .addFilterBefore( // Logout
        new LogoutFilter(LOGOUT_ENDPOINT),
        UsernamePasswordAuthenticationFilter.class
      )
      .addFilterBefore( // Verify user on every request
        new AuthenticationFilter(),
        UsernamePasswordAuthenticationFilter.class
      );

    // Prevent anonymousUser access
    // http.authorizeRequests().anyRequest().authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }
}
