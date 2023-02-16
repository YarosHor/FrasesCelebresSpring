package com.germangascon.frasescelebres.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Autor {
    /** Campos de la tabla */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** Para auto_increment **/
    @Column(name = "id",    updatable = false, nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    /** Año de nacimiento del autor. Números negativos representan AC (Antes de Cristo) */
    @Column(name = "nacimiento", nullable = false)
    private int nacimiento;
    /** Año de muerte del autor. Números negativos representan AC (Antes de Cristo)
     * Se utiliza un String para reflejar con null que el autor sigue vivo
     */
    private String muerte;
    private String profesion;
    @OneToMany(mappedBy = "autor", cascade=CascadeType.REMOVE)
    private List<Frase> frase;

    public Autor() {
    }

    public Autor(String nombre, int nacimiento, String muerte, String profesion) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.muerte = muerte;
        this.profesion = profesion;
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

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        if(muerte != null && muerte.length() == 0) {
            this.muerte = null;
        } else {
            this.muerte = muerte;
        }
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Autor autor = (Autor) o;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte='" + muerte + '\'' +
                ", profesion='" + profesion + '\'' +
                '}';
    }
}
