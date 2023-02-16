package com.germangascon.frasescelebres.models;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class Frase {
    private static final String TAG = Class.class.getSimpleName();
    /** Campos de la tabla */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** Para auto_increment **/
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "texto", length = 255)
    private String texto;
    @Column(name = "fechaProgramada", unique = true)
    private Date fechaProgramada;
    /** Relaciones */
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Autor autor;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Categoria categoria;

    public Frase() {
    }

    public Frase(String texto, Date fechaProgramada, Autor autor, Categoria categoria) {
        this.texto = texto;
        this.fechaProgramada = fechaProgramada;
        this.autor = autor;
        this.categoria = categoria;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaProgramada() {
        return this.fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frase frase = (Frase) o;
        return id == frase.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaProgramada='" + fechaProgramada + '\'' +
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }
}
