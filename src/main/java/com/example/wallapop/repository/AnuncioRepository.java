package com.example.wallapop.repository;

import com.example.wallapop.entity.Anuncio;
import com.example.wallapop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
    List<Anuncio> findByUsuario(Usuario usuario);
    List<Anuncio> findAllByOrderByFechaCreacionDesc();
    List<Anuncio> findByUsuarioOrderByFechaCreacionDesc(Usuario u);
}
