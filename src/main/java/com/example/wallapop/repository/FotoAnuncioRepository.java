package com.example.wallapop.repository;

import com.example.wallapop.entity.FotoAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoAnuncioRepository extends JpaRepository<FotoAnuncio,Long> {
    List<FotoAnuncio> findFotoAnuncioByAnuncioId(Long anuncioId);
}
