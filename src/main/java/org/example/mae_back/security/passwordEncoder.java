package org.example.mae_back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class passwordEncoder {

@Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return  new BCryptPasswordEncoder();

  }
}
