package com.germangascon.frasescelebres.controllers;


import com.germangascon.frasescelebres.models.Categoria;
import com.germangascon.frasescelebres.repo.ICategoriaDao;
import com.germangascon.frasescelebres.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaDao repo;

    @GetMapping("/all")
    public List<Categoria> getCategorias() {
        return repo.findAll();
    }

    @GetMapping("/all/{offset}")
    public List<Categoria> getCategoriasLimit(@PathVariable("offset") int offset) {
        return repo.getCategoriasLimit(offset);
    }

    @GetMapping(value = "/{id}")
    public Optional<Categoria> getCategoriaById(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }

    @PostMapping("/add")
    public boolean addCategoria(@RequestBody Categoria categoria) {
        try {
            Log.i("Nueva Categoría: ", categoria.toString());
            repo.save(categoria);
            return true;
        } catch (Exception e) {
            Log.e("Add Frase", e.getMessage());
            return false;
        }
    }

    @PutMapping("/update")
    public boolean updateCategoria(@RequestBody Categoria categoria) {
        try {
            Log.i("Update Categoria: ", categoria.toString());
            repo.save(categoria);
            return true;
        } catch (Exception e){
            Log.e("Add frase", e.getMessage());
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteCategoria(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            System.out.println("Categoria eliminada");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
