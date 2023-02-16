package com.germangascon.frasescelebres.controllers;

import com.germangascon.frasescelebres.models.Autor;
import com.germangascon.frasescelebres.repo.IAutorDao;
import com.germangascon.frasescelebres.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private IAutorDao repo;

    @GetMapping("/all")
    public List<Autor> getAutores() {
        return repo.findAll();
    }

    @GetMapping("/all/{offset}")
    public List<Autor> getAutoresLimit(@PathVariable("offset") int offset) {
        return repo.getAutoresLimit(offset);
    }


    @GetMapping(value = "/{id}")
    public Optional<Autor> getAutor(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }

    @PostMapping("/add")
    public boolean addAutor(@RequestBody Autor autor) {
        try {
            Log.i("Nuevo Autor: ", autor.toString());
            repo.save(autor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping("/update")
    public boolean updateAutor(@RequestBody Autor autor) {
        try {
            Log.i("Update Autor: ", autor.toString());
            repo.save(autor);
            return true;
        } catch (Exception e){
            Log.e("Update autor", e.getMessage());
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteAutor(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            System.out.println("Autor eliminado correctamente");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
