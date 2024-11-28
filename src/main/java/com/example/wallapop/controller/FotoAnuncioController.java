package com.example.wallapop.controller;

import org.springframework.ui.Model;
import com.example.wallapop.entity.Anuncio;
import com.example.wallapop.repository.AnuncioRepository;
import com.example.wallapop.repository.FotoAnuncioRepository;
import com.example.wallapop.service.FotoAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class FotoAnuncioController {

    @Autowired
    private FotoAnuncioService fotoAnuncioService;
    @Autowired
    private FotoAnuncioRepository fotoAnuncioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;


    @GetMapping("anuncio/{id1}/fotos/{id2}/delete")
    public String deleteFoto(@PathVariable("id1") Long idAnuncio,
                             @PathVariable("id2") Long idFoto) {
        fotoAnuncioService.deleteFoto(idFoto);
        return "redirect:/anuncio/edit/" + idAnuncio ;
    }


    @PostMapping("/anuncio/edit/{idAnuncio}/addfoto")
    public String addFoto(@PathVariable("idAnuncio") Long idAnuncio,
                          @RequestParam(value = "archivoFoto") MultipartFile archivoFoto) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(idAnuncio);
        if(anuncio.isPresent()){
            fotoAnuncioService.addFoto(archivoFoto, anuncio.get());
        }
        return "redirect:/anuncio/edit/" + idAnuncio;
    }
}
