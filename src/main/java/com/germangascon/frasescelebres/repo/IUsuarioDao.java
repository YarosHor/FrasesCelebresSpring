package com.germangascon.frasescelebres.repo;

import com.germangascon.frasescelebres.models.Autor;
import com.germangascon.frasescelebres.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    /*@Query(value="SELECT * FROM usuario ORDER BY usuario.id LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<Usuario> getUsuariosLimit(int offset);*/
    Usuario findByNombre(String nombre);
}
