package com.matuzalem.produto_api.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matuzalem.produto_api.model.dto.CategoriaDTO;
import com.matuzalem.produto_api.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getCategorias() {
        return categoriaService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO criarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.save(categoriaDTO);
    }

    @PutMapping("/{id}")
    public CategoriaDTO editarCategoria(@PathVariable String id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.update(id, categoriaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        categoriaService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<CategoriaDTO> getCategoriasPageable(Pageable pageable) {
        return categoriaService.getAllPage(pageable);
    }
}
