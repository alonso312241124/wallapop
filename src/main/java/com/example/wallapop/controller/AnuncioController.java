package com.example.wallapop.controller;

import com.example.wallapop.entity.Anuncio;
import com.example.wallapop.entity.FotoAnuncio;
import com.example.wallapop.entity.Usuario;
import com.example.wallapop.service.AnuncioService;
import com.example.wallapop.service.FotoAnuncioService;
import com.example.wallapop.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private FotoAnuncioService fotoAnuncioService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/anuncios")
    public String verAnuncios(Model model){
        model.addAttribute("anuncios", anuncioService.findAllAnuncios());
        return "anuncio-list";
    }

    @GetMapping("/anuncios/new")
    public String newAnuncio(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "anuncio-new";
    }

    @PostMapping("/anuncios/new")
    public String newAnuncioInsert(Model model, @Valid Anuncio anuncio, BindingResult bindingResult,
                                   @RequestParam(value = "archivosFotos", required = false)
                                   List<MultipartFile> files, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "anuncio-new";
        }

        try {
            //Asignar la fecha del momento que sube el anuncio
            anuncio.setFechaCreacion(LocalDateTime.now());
            //Asigno al anuncio el objeto usuario de quien ha creado el anuncio.
            String email = authentication.getName();
            Usuario usuarioLogeado = usuarioService.findByEmail(email);
            anuncio.setUsuario(usuarioLogeado);

            fotoAnuncioService.guardarFotos(files, anuncio);
            anuncioService.guardarAnuncio(anuncio);

        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al subir el anuncio: " + e.getMessage());
            return "anuncio-new";
        }
        return "redirect:/anuncios";

    }

    @GetMapping("/anuncio/view/{id}")
    public String viewAnuncioPorId(@PathVariable Long id, Model model) {
        Optional<Anuncio> anuncio = anuncioService.findAnuncioById(id);
        List<FotoAnuncio> fotos = fotoAnuncioService.obtenerFotosPorAnuncioId(id);
        if (anuncio.isPresent()) {
            model.addAttribute("anuncio", anuncio.get());
            model.addAttribute("fotos", fotos);
            return "anuncio-view";
        }
        return "redirect:/anuncios";
    }

    // Devuelve true si el usuario autenticado es el autor del anuncio, false si no lo es o si el anuncio no existe
    private boolean esAutorDelAnuncio(Long anuncioId, Authentication authentication) {
        Optional<Anuncio> anuncio = anuncioService.findAnuncioById(anuncioId);
        if (anuncio.isPresent()) {
            String email = authentication.getName();
            Usuario usuarioLogeado = usuarioService.findByEmail(email);
            return anuncio.get().getUsuario().getId().equals(usuarioLogeado.getId());
        }
        return false;
    }

    @GetMapping("/anuncio/edit/{id}")
    public String editAnuncio(@PathVariable Long id, Model model, Authentication authentication) {
        if (esAutorDelAnuncio(id, authentication)) {
            Anuncio anuncio = anuncioService.findAnuncioById(id).orElseThrow(() ->
                    new IllegalArgumentException("Anuncio no encontrado"));
            model.addAttribute("anuncio", anuncio);
            return "anuncio-edit";
        }
        return "error";
    }

    @PostMapping("/anuncio/edit/{id}")
    public String editProductoUpdate(@PathVariable Long id, Anuncio anuncio, Authentication authentication) {
        if (esAutorDelAnuncio(id, authentication)) {
            Anuncio anuncioExistente = anuncioService.findAnuncioById(id).orElseThrow(() ->
                    new IllegalArgumentException("Anuncio no encontrado"));

            anuncio.setId(id);
            anuncio.setUsuario(anuncioExistente.getUsuario());
            anuncio.setFechaCreacion(LocalDateTime.now());
            anuncioService.guardarAnuncio(anuncio);
            return "redirect:/anuncios";
        }
        return "error";
    }

    @GetMapping("/anuncio/del/{id}")
    public String delAnuncio(@PathVariable Long id, Authentication authentication) {
        if (esAutorDelAnuncio(id, authentication)) {
            anuncioService.deleteAnuncioById(id);
            return "redirect:/anuncios";
        }
        return "error";
    }

    @GetMapping("/mis-anuncios")
    public String verAnunciosDelUsuarioLogeado(Model model, Authentication authentication) {
        //Vemos quién es el usuario logeado
        String email = authentication.getName();
        Usuario usuarioLogeado = usuarioService.findByEmail(email);

        //Hacemos una lista de los anuncios del usuario logeado llamando al servicio
        List<Anuncio> misAnuncios = anuncioService.findAnunciosByUsuario(usuarioLogeado);

        model.addAttribute("anuncios", misAnuncios);
        return "mis-anuncios";
    }

}
