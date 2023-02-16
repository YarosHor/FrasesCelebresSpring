package com.germangascon.frasescelebres.controllers;

import com.germangascon.frasescelebres.models.Usuario;
import com.germangascon.frasescelebres.repo.IUsuarioDao;
import com.germangascon.frasescelebres.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioDao repo;

    @GetMapping(value = "/{id}")
    public Optional<Usuario> getUsuario(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }

    @PostMapping("/add")
    public boolean addUsuario(@RequestBody Usuario usuario) {
        try {
            Log.i("Nuevo Usuario: ", usuario.toString());
            repo.save(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping("/update")
    public boolean updateUsuario(@RequestBody Usuario usuario) {
        try {
            Log.i("Update Usuario: ", usuario.toString());
            repo.save(usuario);
            return true;
        } catch (Exception e){
            Log.e("Update usuario", e.getMessage());
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteUsuario(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            System.out.println("Usuario eliminado correctamente");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
