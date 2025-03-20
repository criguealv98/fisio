package com.example.cliniko.config;

import com.example.cliniko.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;  // Inyección a través del constructor
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(usuarioService)
                                        .passwordEncoder(passwordEncoder);
            return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/login", "/register", "/menu-admin", "/h2-console/**", "/crear-usuario", "/crear-paciente", "/crear-fisio").permitAll()  // Permite acceso a /crear-usuario
              .anyRequest().authenticated()  // Otras rutas requieren autenticación
          )
          .formLogin(form -> form
              .loginPage("/login")
              .defaultSuccessUrl("/menu", true)  // Redirige tras login exitoso
              .permitAll()
          )
          .logout(logout -> logout
              .logoutUrl("/logout")
              .logoutSuccessUrl("/login")  // Redirige a login tras cerrar sesión
              .permitAll()
          )
          .csrf(csrf -> csrf
              .ignoringRequestMatchers("/h2-console/**")  // Deshabilita CSRF solo para H2 Console
              .ignoringRequestMatchers("/login")  // Si sigues con problemas, prueba esto
              .ignoringRequestMatchers("/crear-usuario")
              .ignoringRequestMatchers("/crear-fisio")
              .ignoringRequestMatchers("/crear-paciente")

        		  )
          .headers(headers -> headers.frameOptions().disable());  // Permite iframes para H2 Console

        return http.build();
    }
}

