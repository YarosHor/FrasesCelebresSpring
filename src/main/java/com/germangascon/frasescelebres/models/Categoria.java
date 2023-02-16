package com.germangascon.frasescelebres.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** Para auto_increment **/
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(unique=true)
    private String nombre;
    @OneToMany(mappedBy = "categoria", cascade=CascadeType.REMOVE)
    private List<Frase> frase;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    /**
     * Hacemos el método privado ya que no permitimos modificar el id desde fuera
     * al ser de tipo autoincrement
     */
    private void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Categoria categoria = (Categoria) o;

        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
