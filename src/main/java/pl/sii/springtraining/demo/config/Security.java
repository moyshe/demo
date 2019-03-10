package pl.sii.springtraining.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth
      .inMemoryAuthentication()
      .withUser("admin")
        .password(encoder.encode("SiiAdmin"))
        .roles("ADMIN")
        .and()
      .withUser("johndoe")
        .password(encoder.encode("SiiUser"))
        .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/employees/info").authenticated()
        .antMatchers(HttpMethod.GET, "/employees", "/employees/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/employees").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/employees").hasRole("ADMIN")
        .and()
      .httpBasic();
  }
}
