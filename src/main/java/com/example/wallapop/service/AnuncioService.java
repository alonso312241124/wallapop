package com.example.wallapop.service;

import com.example.wallapop.entity.Anuncio;
import com.example.wallapop.entity.FotoAnuncio;
import com.example.wallapop.entity.Usuario;
import com.example.wallapop.repository.AnuncioRepository;
import com.example.wallapop.repository.FotoAnuncioRepository;
import com.example.wallapop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private AnuncioRepository anuncioRepository;
    private FotoAnuncioRepository fotoAnuncioRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository,
                           FotoAnuncioRepository fotoAnuncioRepository,
                           UsuarioRepository usuarioRepository) {
        this.anuncioRepository = anuncioRepository;
        this.fotoAnuncioRepository = fotoAnuncioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Los anuncios se pasan ordenados conforme a las especificaciones (Fecha de creación de forma descendente)
    public List<Anuncio> findAllAnuncios(){
        return anuncioRepository.findAllByOrderByFechaCreacionDesc();
    }

    public void guardarAnuncio(Anuncio anuncio){
        anuncioRepository.save(anuncio);
    }

    public Optional<Anuncio> findAnuncioById(Long id) {
        return anuncioRepository.findById(id);
    }

    public void deleteAnuncioById(Long id) {
        anuncioRepository.deleteById(id);
    }

    public List<Anuncio> findAnunciosByUsuario(Usuario usuario) {
        return anuncioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);
    }


}
