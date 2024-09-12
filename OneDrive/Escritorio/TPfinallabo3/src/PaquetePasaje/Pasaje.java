package PaquetePasaje;


import PaqueteRutaAerea.RutaAerea;
import ParteCategoria.Categoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;






public class Pasaje {
    private String idPasaje; ///no se lo puede modificar
    private String idPasajero;
    private String idAeropuerto;
    private LocalDate fechaPasaje; ///no se puede modificar
    private String matriculaAvion; ///se modifica si un usuario administrador modifica la matricula de un avion
    private boolean pasajeActivo; ///solo lo puede modificar el usuario administrador
    private boolean expirado; ///no se puede modificar

    ///EL PASAJE ES UN DOCUMENTO MUY IMPORTANTE. DEBE SER OBLIGATORIO QUE SI SE VA A COMPRAR UNO, SE ENCUENTRE
    ///TODA LA INFORMACION CORRESPONDIENTE. EL ID SE ESTABLECE AUTOMATICAMENTE DESPUES DE INSTANCIAR EL OBJETO
    ///PARA ASEGURARSE QUE EL ID SEA UNICO.


    public Pasaje(String idPasaje, String idPasajero, String idAeropuerto, LocalDate fechaPasaje, String matriculaAvion, boolean pasajeActivo) {
        this.idPasaje = idPasaje;
        this.idPasajero = idPasajero;
        this.idAeropuerto = idAeropuerto;
        this.fechaPasaje = fechaPasaje;
        this.matriculaAvion = matriculaAvion;
        this.pasajeActivo = pasajeActivo;
        expirado = false;
    }

    public String getIdPasajero() {
        return idPasajero;
    }

    public String getIdPassage() {
        return idPasaje;
    }

    public void setIdPasaje(String idPasaje) {
        this.idPasaje = idPasaje;
    }

    public void setIdPasajero(String idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(String idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }


    public LocalDate getFechaPasaje() {
        return fechaPasaje;
    }

    public void setFechaPasaje(LocalDate fechaPasaje) {
        this.fechaPasaje = fechaPasaje;
    }

    public String getMatriculaAvion() {
        return matriculaAvion;
    }

    public void setMatriculaAvion(String matriculaAvion) {
        this.matriculaAvion = matriculaAvion;
    }

    public boolean isPasajeActivo() {
        return pasajeActivo;
    }

    public boolean isPasajeExpirado() {
        return expirado;
    }

    public void setPasajeActivo(boolean pasajeActivo) {
        this.pasajeActivo = pasajeActivo;
    }

    public boolean isExpirado() {
        return expirado;
    }

    public void setExpirado(boolean expirado) {
        this.expirado = expirado;
    }

    public StringBuilder informacionPasaje() {

        StringBuilder listado = new StringBuilder();

        listado.append("Pasaje{");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        listado.append("Hora de compra de pasaje: " + fechaPasaje.format(formatter) + " ");
        listado.append("idPasaje=" + idPasaje +
                ", matriculaAvion='" + matriculaAvion + '\'' +
                '}');

        return listado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasaje pasaje = (Pasaje) o;
        return Objects.equals(idPasaje, pasaje.idPasaje) && Objects.equals(fechaPasaje, pasaje.fechaPasaje);
    }

}
