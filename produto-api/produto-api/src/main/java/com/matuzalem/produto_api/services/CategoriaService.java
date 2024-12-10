package com.matuzalem.produto_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.matuzalem.produto_api.model.Categoria;
import com.matuzalem.produto_api.model.dto.CategoriaDTO;
import com.matuzalem.produto_api.repositories.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
            .map(CategoriaDTO::convert)
            .collect(Collectors.toList());
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.save(Categoria.convert(categoriaDTO));
        return CategoriaDTO.convert(categoria);
    }

    public CategoriaDTO update(String id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        if(categoriaDTO.getName() != null && !categoria.getName().equals(categoriaDTO.getName())) {
            categoria.setName(categoriaDTO.getName());
        }
        categoria = categoriaRepository.save(categoria);
        return CategoriaDTO.convert(categoria);
    }

    public CategoriaDTO delete(String id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaRepository.delete(categoria);
        return CategoriaDTO.convert(categoria);
    }

    public Page<CategoriaDTO> getAllPage(Pageable pageable) {
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return categorias.map(CategoriaDTO::convert);
    }
}
