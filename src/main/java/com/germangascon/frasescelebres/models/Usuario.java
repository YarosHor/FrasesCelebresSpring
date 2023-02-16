package com.germangascon.frasescelebres.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.germangascon.frasescelebres.util.Log;

import javax.persistence.*;

@Entity
public class Usuario {
    private final static String TAG = "Usuario";
    private static final String tabla = "usuario";
    /** Campos de la tabla */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** Para auto_increment **/
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", length = 255)
    private String password;
    private String gender;
    @Column(name = "tipoUsuario", length = 255)
    private String tipoUsuario;
    private String session;
    private String ip;
    private Timestamp ultimaConexion;

    public Usuario() {
        init();
    }

    public void init() {
        id = 0;
        nombre = null;
        email = null;
        password = null;
        gender = null;
        tipoUsuario = null;
        session = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipo) {
        if(tipo.equals(TipoUsuario.USER) || tipo.equals(TipoUsuario.ADMIN)) {
            this.tipoUsuario = tipo;
        } else {
            this.tipoUsuario = TipoUsuario.USER;
        }
    }

    public boolean isAdmin() {
        return this.tipoUsuario.equals(TipoUsuario.ADMIN);
    }

    public String getSession() {
        return session;
    }

    /** La session se asigna mediante login */
    private void setSession(String session) {
        this.session = session;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getUltimaConexion() {
        return this.ultimaConexion;
    }

    private void setUltimaConexion(Timestamp t) {
        this.ultimaConexion = t;
    }

    public boolean validate() {
        return nombre != null && email != null && password != null;
    }

    public String generateAuthToken() {
        return UUID.randomUUID().toString().toLowerCase() + id;
    }

    public Timestamp getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static class Gender {
        public final static String MASCULINO = "M";
        public final static String FEMENINO = "F";
    }

    public static class TipoUsuario {
        public final static String USER = "U";
        public final static String ADMIN = "A";
    }
}