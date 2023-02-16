package com.germangascon.frasescelebres.repo;

import com.germangascon.frasescelebres.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoriaDao extends JpaRepository<Categoria, Integer> {
    @Query(value="SELECT * FROM categoria ORDER BY categoria.id LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<Categoria> getCategoriasLimit(int offset);
}
