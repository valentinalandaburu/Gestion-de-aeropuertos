package PaqueteRutaAerea;

import PaqueteExcepciones.ExcepcionRutaEnUso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class RutaAerea {

    private String idRutaAerea;
    private String aeropuertoSalida; ///codigoIATA
    private String aeropuertoLlegada; ///codigoIATA
    private int kmRecorrido; ///este atributo es inmodificable
    private HashSet<String>idAvionesAsignados;
    private boolean rutaActiva;


    public RutaAerea(String idRutaAerea,String aeropuertoSalida, String aeropuertoLlegada, int kmRecorrido) {
        this.idRutaAerea = idRutaAerea;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.kmRecorrido = kmRecorrido;
        idAvionesAsignados = new HashSet<>();
        rutaActiva = true;
    }

    public RutaAerea(String idRutaAerea, String aeropuertoSalida, String aeropuertoLlegada, int kmRecorrido, HashSet<String> idAvionesAsignados, boolean rutaActiva) {
        this.idRutaAerea = idRutaAerea;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.kmRecorrido = kmRecorrido;
        this.idAvionesAsignados = idAvionesAsignados;
        this.rutaActiva = rutaActiva;
    }

    public HashSet<String> getIdAvionesConEstaRuta() {
        return idAvionesAsignados;
    }

    public void agregarIdAvionesConEstaRuta(String idviaje) {
        idAvionesAsignados.add(idviaje);
    }

    public String getIdRutaAerea() {
        return idRutaAerea;
    }

    public String getAeropuertoSalida() {
        return aeropuertoSalida;
    }

    public void setAeropuertoSalida(String aeropuertoSalida) {
        this.aeropuertoSalida = aeropuertoSalida;
    }


    public String getAeropuertoLlegada() {
        return aeropuertoLlegada;
    }

    public void setAeropuertoLlegada(String aeropuertoLlegada) {
        this.aeropuertoLlegada = aeropuertoLlegada;
    }

    public int getKmRecorrido() {
        return kmRecorrido;
    }

    public void setKmRecorrido(int kmRecorrido) {
        this.kmRecorrido = kmRecorrido;
    }

    public boolean isRutaActiva() {
        return rutaActiva;
    }

    public void setRutaActiva(boolean rutaActiva) {
        this.rutaActiva = rutaActiva;
    }

    public StringBuilder listado() {
        StringBuilder listar = new StringBuilder();
        listar.append("RutaAerea{" +
                "idRutaAerea ='" + idRutaAerea + '\'' +
                ", aeropuertoSalida ='" + aeropuertoSalida + '\'' +
                ", aeropuertoLlegada ='" + aeropuertoLlegada + '\'' +
                ", kmRecorrido =" + kmRecorrido +
                ", Cantidad de aviones que realizan esta ruta = " + getIdAvionesConEstaRuta().size() +
                ", Ruta activa = " + rutaActiva +
                '}');

        return listar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutaAerea rutaAerea = (RutaAerea) o;
        return kmRecorrido == rutaAerea.kmRecorrido && Objects.equals(idRutaAerea, rutaAerea.idRutaAerea) && Objects.equals(aeropuertoSalida, rutaAerea.aeropuertoSalida) && Objects.equals(aeropuertoLlegada, rutaAerea.aeropuertoLlegada);
    }


    public boolean eliminarIdAvionDeHashSet(String idAvion){

        return idAvionesAsignados.remove(idAvion);


    }


}
