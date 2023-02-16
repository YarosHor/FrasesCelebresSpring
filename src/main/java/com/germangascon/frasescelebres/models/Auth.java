package com.germangascon.frasescelebres.models;

public class Auth {
    private int id;
    private String nombre;
    private String gender;
    private String session;
    private String tipoUsuario;

    public Auth(int id, String nombre, String gender, String session, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.gender = gender;
        this.session = session;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

