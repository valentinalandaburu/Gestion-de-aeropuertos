package PaqueteHangar;

import PaqueteAvion.Avion;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionCapacidadAviones;
import PaqueteExcepciones.ExcepcionCapacidadHangar;
import PaqueteExcepciones.ExcepcionHangarInactivo;

import java.util.ArrayList;
import java.util.HashSet;

public class Hangar {

    private String idHangar;
    private String idAeropuertoPertenece;
    private String nombreHangar;
    private int capacidadAviones;
    private boolean habilitado;
    private HashSet<String>idAvionesEnHangar;

    public Hangar(String idHangar, String nombreHangar, int capacidadAviones, boolean habilitado, String idAeropuerto) {
        this.idHangar = idHangar;
        this.idAeropuertoPertenece = idAeropuerto;
        this.nombreHangar = nombreHangar;
        this.capacidadAviones = capacidadAviones;
        this.habilitado = habilitado;
        idAvionesEnHangar = new HashSet<>();
    }

    public Hangar(String idHangar, String idAeropuertoPertenece, String nombreHangar, int capacidadAviones, boolean habilitado, HashSet<String> idAvionesEnHangar) {
        this.idHangar = idHangar;
        this.idAeropuertoPertenece = idAeropuertoPertenece;
        this.nombreHangar = nombreHangar;
        this.capacidadAviones = capacidadAviones;
        this.habilitado = habilitado;
        this.idAvionesEnHangar = idAvionesEnHangar;
    }

    public HashSet getIdAvionesEnHangar() {
        return idAvionesEnHangar;
    }

    public String getIdHangar() {
        return idHangar;
    }

    public String getIdAeropuerto() {
        return idAeropuertoPertenece;
    }

    public void setIdAeropuertoPertenece(String idAeropuerto) {
        this.idAeropuertoPertenece = idAeropuerto;
    }

    public void setIdHangar(String idHangar) {
        this.idHangar = idHangar;
    }

    public String getNombreHangar() {
        return nombreHangar;
    }

    public void setNombreHangar(String nombreHangar) {
        this.nombreHangar = nombreHangar;
    }

    public int getCapacidadAviones() {
        return capacidadAviones;
    }

    public void setCapacidadAviones(int capacidadAviones) {
        this.capacidadAviones = capacidadAviones;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }



    public StringBuilder obtenerInformacion() {

        StringBuilder listado = new StringBuilder();

         listado.append("Hangar{" +
                "idHangar ='" + idHangar + '\'' +
                ", nombreHangar ='" + nombreHangar + '\'' +
                ", capacidadAviones =" + capacidadAviones +
                ", habilitado =" + habilitado);
         listado.append("}");

        return listado;
    }


    public void validarCantidadAviones()throws ExcepcionCapacidadAviones {

        if(getCapacidadAviones() == getIdAvionesEnHangar().size()){

            throw new ExcepcionCapacidadAviones("No se puede generar el nuevo avion porque se supera" +
                    "el limite permitido por el hangar");

        }

    }


    public void agregarAvionAHangar(String matricula){

        idAvionesEnHangar.add(matricula);

    }

    public boolean eliminarAvionDeHangar(String matricula){

        if (idAvionesEnHangar.remove(matricula)){
            return true;
        }

        return false;
    }


    public boolean contieneAvionGuardado(String idAvion){

        if(getIdAvionesEnHangar().contains(idAvion)){

            return true;
        }


        return false;

    }


    public void validarSiCapacidadHangarEsMenorAAvionesAlmacenados(int nvaCapacidadAviones)throws ExcepcionCapacidadHangar {

        if (nvaCapacidadAviones > getIdAvionesEnHangar().size()){

            throw new ExcepcionCapacidadHangar("La capacidad que quiere establecer para el hangar es menor" +
                    "a la cantidad de aviones en el hangar.");

        }

    }

    public boolean validarSiCapacidadHangarEsMenorAAvionesAlmacenados() {

        if (getCapacidadAviones() > getIdAvionesEnHangar().size()){
            return true;

        }

        return false;

    }


    public void verificarHangarActivo()throws ExcepcionHangarInactivo {

        if (!isHabilitado()){

            throw new ExcepcionHangarInactivo("No se puede crear un avion porque el Hangar esta inactivo.");

        }

    }


    /*public StringBuilder obtenerInformacion(String destino) {

        StringBuilder listado = new StringBuilder();

        listado.append("Hangar{" +
                "idHangar ='" + idHangar + '\'' +
                ", nombreHangar ='" + nombreHangar + '\'' +
                ", capacidadAviones =" + capacidadAviones +
                ", habilitado =" + habilitado);

        listado.append(avionesEnHangar.listado(destino));
        listado.append("}");

        return listado;
    }
     */
}
