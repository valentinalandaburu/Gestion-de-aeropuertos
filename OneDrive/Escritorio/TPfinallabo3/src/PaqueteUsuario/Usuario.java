package PaqueteUsuario;

import PaqueteCC.GeneradorStringAleatorio;

public class Usuario {

    protected String usuarioAeropuerto; ///Este atributo es inmodificable y es el codigo internacional del aeropuerto
    protected String idUser; ///Este atributo es inmodificable
    protected String contrasenia; ///Este atributo  lo puede modificar el administrador y no modifica los pasaje
    protected String username; ///Este atributo  lo puede modificar el administrador y no modifica los pasaje
    protected String nombreCompleto; ///Este atributo lo puede modificar solamente el propio usuario y modifica los pasajes
    protected String dni; ///Este atributo lo puede modificar solamente el propio usuario y modifica los pasajes
    protected String telPersonal; ///Este atributo lo puede modificar el administrador y no modifica al pasaje
    protected char genero; ///Este atributo  lo puede modificar el administrador y no modifica al pasaje
    protected boolean usuarioActivo; ///Este atributo lo puede modificar el administrador
    protected String idPasaje;

    ///La realidad es que un administrador si bien podrìa modificar lo antes mencionado e incluso màs cosas,
    ///para hacerlo debe tener el consentimiento del usuario por temas de seguridad y privacidad. En este
    ///programa se da por sentado que si el administrador modifica algo de otro usuario es porque tiene el
    ///consentimiento de hacerlo


    public Usuario(String idUsuario, String usuarioAeropuerto, String contrasenia, String username, String nombreCompleto, String dni, String telPersonal, char genero) {
        this.usuarioAeropuerto = usuarioAeropuerto;
        this.idUser = idUsuario;
        this.contrasenia = contrasenia;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.telPersonal = telPersonal;
        this.genero = genero;
        usuarioActivo = true;
        this.idPasaje = null;
    }

    public Usuario(String usuarioAeropuerto, String idUser, String contrasenia, String username, String nombreCompleto, String dni, String telPersonal, char genero, boolean usuarioActivo, String idPasaje) {
        this.usuarioAeropuerto = usuarioAeropuerto;
        this.idUser = idUser;
        this.contrasenia = contrasenia;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.telPersonal = telPersonal;
        this.genero = genero;
        this.usuarioActivo = usuarioActivo;
        this.idPasaje = idPasaje;
    }

    public String getIdUsuario() {
        return idUser;
    }

    public void setIdUsuario(String idUser) {
        this.idUser = idUser;
    }

    public String getUsuarioAeropuerto() {
        return usuarioAeropuerto;
    }

    public void setUsuarioAeropuerto(String usuarioAeropuerto) {
        this.usuarioAeropuerto = usuarioAeropuerto;
    }

    public String getIdPasaje() {
        return idPasaje;
    }

    public void setIdPasaje(String idPasaje) {
        this.idPasaje = idPasaje;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelPersonal() {
        return telPersonal;
    }

    public void setTelPersonal(String telPersonal) {
        this.telPersonal = telPersonal;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public boolean isUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(boolean usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public StringBuilder informacionUsuarioParaAdmin() {

        StringBuilder listado = new StringBuilder();

        listado.append("Usuario{" +
                "usuarioAeropuerto='" + usuarioAeropuerto + '\'' +
                ", idUser='" + idUser + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", dni='" + dni + '\'' +
                ", telPersonal='" + telPersonal + '\'' +
                ", genero=" + genero +
                ", usuarioActivo=" + usuarioActivo +
                '}');

        return listado;
    }

    public StringBuilder informacionUsuarioParaComercial() {

        StringBuilder listado = new StringBuilder();

        listado.append("Usuario{" +
                "usuarioAeropuerto='" + usuarioAeropuerto + '\'' +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", dni='" + dni + '\'' +
                ", telPersonal='" + telPersonal + '\'' +
                ", genero=" + genero +
                '}');

        return listado;
    }
}
