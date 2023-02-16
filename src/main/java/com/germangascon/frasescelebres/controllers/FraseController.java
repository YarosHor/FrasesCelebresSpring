package com.germangascon.frasescelebres.controllers;

import com.germangascon.frasescelebres.models.Autor;
import com.germangascon.frasescelebres.models.Categoria;
import com.germangascon.frasescelebres.models.Frase;
import com.germangascon.frasescelebres.repo.IAutorDao;
import com.germangascon.frasescelebres.repo.ICategoriaDao;
import com.germangascon.frasescelebres.repo.IFraseDao;
import com.germangascon.frasescelebres.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/frase")
public class FraseController {
    @Autowired
    private IFraseDao repo;

    @Autowired
    private IAutorDao repoAutor;

    @Autowired
    private ICategoriaDao repoCategoria;

    @GetMapping("/all")
    public List<Frase> getFrases() {
        return repo.findAll();
    }

    @GetMapping("/all/{offset}")
    public List<Frase> getFrasesLimit(@PathVariable("offset") Integer offset){
        return repo.getFrasesLimit(offset);
    }

    @GetMapping(value = "/{id}")
    public Optional<Frase> getFraseById(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }

    @GetMapping(value = "/dia/{dia}")
    public Frase getFraseDelDia(@PathVariable("dia") Date dia) {
        return repo.getFraseDelDia(dia);
    }

    @GetMapping("/categoria/{id}")
    public List<Frase> getFraseByCategoria(@PathVariable("id") Integer id){
        return repo.getFrasesByCategoria(id);
    }

    @GetMapping("/autor/{id}")
    public List<Frase> getFraseByAutor(@PathVariable("id") Integer id){
        return repo.getFrasesByAutor(id);
    }

    @PostMapping("/add")
    public boolean addFrase(@RequestBody Frase frase) {
        try {
            Log.i("Nueva Frase: ", frase.toString());
            repo.save(frase);
            return true;
        } catch (Exception e) {
            Log.e("Add Frase", e.getMessage());
            return false;
        }
    }

    @PostMapping("/addValues")
    public boolean addFraseValues(@RequestParam String texto,
                                  @RequestParam Date fechaProgramada,
                                  @RequestParam int idAutor,
                                  @RequestParam int idCategoria) {

        try {
            Optional<Autor> optionalAutor = repoAutor.findById(idAutor);
            Autor autor = optionalAutor.get();

            if(autor != null) {
                Log.i("Autor:", autor.toString());
            }

            Optional<Categoria> optionalCategoria = repoCategoria.findById(idCategoria);
            Categoria categoria = optionalCategoria.get();

            if(categoria != null) {
                Log.i("Categoria:", categoria.toString());
            }

            Frase frase = new Frase(texto, fechaProgramada, autor, categoria);

            repo.save(frase);
            return true;
        } catch (Exception e) {
            Log.e("Add Frase Values", e.getMessage());
            return false;
        }
    }

    @PutMapping("/update")
    public boolean updateFrase(@RequestBody Frase frase) {
        try {
            Log.i("Update Frase: ", frase.toString());
            repo.save(frase);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteFrase(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            System.out.println("Frase eliminada");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
