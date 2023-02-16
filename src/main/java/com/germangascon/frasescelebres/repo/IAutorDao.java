package com.germangascon.frasescelebres.repo;

import com.germangascon.frasescelebres.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorDao extends JpaRepository<Autor, Integer> {
    @Query(value="SELECT * FROM autor ORDER BY autor.id LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<Autor> getAutoresLimit(int offset);
}
