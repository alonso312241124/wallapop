package com.example.wallapop.service;

import com.example.wallapop.entity.Usuario;
import com.example.wallapop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
