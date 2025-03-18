package com.example.cliniko.service;

import com.example.cliniko.model.Usuario;
import com.example.cliniko.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")  // Si tienes roles, puedes agregarlos aquí
                .build();
    }
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    public Usuario save(Usuario usuario) {
    	
        return usuarioRepository.save(usuario);
    }
}
