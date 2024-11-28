package com.example.wallapop.controller;

import com.example.wallapop.entity.Usuario;
import com.example.wallapop.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutenticacionController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@Valid @ModelAttribute Usuario usuario,
                                   BindingResult bindingResult,
                                   Model model) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.usuario", "Este email ya está registrado en la base de datos");
        }

        if (usuario.getPassword().length() < 4) {
            bindingResult.rejectValue("password", "error.usuario", "La contraseña debe tener al menos 4 caracteres");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            return "redirect:/anuncios";
        } catch (Exception e) {
            model.addAttribute("error",
                    "Ocurrió un error al procesar el registro");
            return "register";
        }
    }
}
