package PaqueteAeropuerto;

import PaqueteExcepciones.ExcepcionAeropuertoInactivo;
import PaqueteExcepciones.ExcepcionCapacidadAeropuerto;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;

import java.util.HashSet;

public class Aeropuerto {

    private String nombreAeropuerto;
    private String direccion;
    private int capacidadTotalAviones;
    private int capacidadActual;
    private String codigoInternacional;  ///Se rige bajo codigo de IATA
    private boolean aeropuertoActivo;  ///es lo que va a indicar si se puede comprar pasajes o no
    private HashSet<String>idHangares;
    private HashSet<String>usuariosDeAeropuerto;

    public Aeropuerto(String nombreAeropuerto, String direccion, int capacidadTotalAviones, String codigoInternacional, boolean aeropuertoActivo) {
        this.nombreAeropuerto = nombreAeropuerto;
        this.direccion = direccion;
        this.capacidadTotalAviones = capacidadTotalAviones;
        this.capacidadActual = 0;
        this.codigoInternacional = codigoInternacional;
        this.aeropuertoActivo = aeropuertoActivo;
        idHangares = new HashSet<>();
        usuariosDeAeropuerto = new HashSet<>();
    }

    public Aeropuerto(String nombreAeropuerto, String direccion, int capacidadTotalAviones, String codigoInternacional) {
        this.nombreAeropuerto = nombreAeropuerto;
        this.direccion = direccion;
        this.capacidadTotalAviones = capacidadTotalAviones;
        this.codigoInternacional = codigoInternacional;
        aeropuertoActivo = true;
    }

    public Aeropuerto() {  ///Los constructores vacios se utilizan para la seccion de modificacion
        this.nombreAeropuerto = " ";
        this.direccion = " ";
        this.capacidadTotalAviones = 40;
        this.codigoInternacional = " ";
        this.aeropuertoActivo = true;
        idHangares = new HashSet<>();
    }

    public String getNombreAeropuerto() {
        return nombreAeropuerto;
    }

    public void setNombreAeropuerto(String nombreAeropuerto) {
        this.nombreAeropuerto = nombreAeropuerto;
    }

    public HashSet<String> getIdHangaresDeAeropuerto() {
        return idHangares;
    }
    public void agregarIdHangarPertenencia(String idHangar){

        idHangares.add(idHangar);

    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public HashSet<String> getUsuariosDeAeropuerto() {
        return usuariosDeAeropuerto;
    }

    public void agregarIdUsuarioAAeropuerto(String idUsuario) {
        this.usuariosDeAeropuerto = usuariosDeAeropuerto;
    }

    public void removerIdUsuario(String id){

        usuariosDeAeropuerto.remove(id);

    }

    public void removerIdHangarPertenencia(String id){

        idHangares.remove(id);

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCapacidadTotalAviones() {
        return capacidadTotalAviones;
    }

    public void setCapacidadTotalAviones(int capacidadTotalAviones) {
        this.capacidadTotalAviones = capacidadTotalAviones;
    }

    public String getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(String codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
    }

    public boolean isAeropuertoActivo() {
        return aeropuertoActivo;
    }

    public void setAeropuertoActivo(boolean aeropuertoActivo) {
        this.aeropuertoActivo = aeropuertoActivo;
    }

    public StringBuilder obtenerInformacion(){
        StringBuilder listado = new StringBuilder();
        listado.append("Aeropuerto{" +
                "nombreAeropuerto ='" + nombreAeropuerto + '\'' +
                ", direccion ='" + direccion + '\'' +
                ", capacidadTotalAviones =" + capacidadTotalAviones +
                ", capacidadActual =" + capacidadActual +
                ", cantidad de hangares = " + getIdHangaresDeAeropuerto().size() +
                ", codigoInternacional =" + codigoInternacional + '\'' +
                ", aeropuertoActivo = " + aeropuertoActivo);
        listado.append("}");

        return listado;
    }


    public void validacionDeCapacidad(Hangar hangarNvoOModificado, GestionHangar hangares)throws ExcepcionCapacidadAeropuerto {

        ///Como aeropuerto lleva el control de la capacidad actual de aviones que se pueden almacenar
        ///Es el aeropuerto quien se encarga de validar la capacidad de un hangar nuevo o modificado

        if (!getIdHangaresDeAeropuerto().contains(hangarNvoOModificado.getIdHangar())) {
            ///Si el hashset de id de hangares del aeropuerto no contiene el id de este hangar...

            if (getCapacidadTotalAviones() > getCapacidadActual() + hangarNvoOModificado.getCapacidadAviones()){
                ///...Si la capacidad total de aviones permitidos por el aeropuerto es mayor a la capacidad actual del aeropuerto
                ///sumada a la capacidad establecida para el hangar...

                agregarIdHangarPertenencia(hangarNvoOModificado.getIdHangar());
                ///...Se agrega el id...

                setCapacidadActual(getCapacidadActual() + hangarNvoOModificado.getCapacidadAviones());
                ///...se actualiza la capacidad actual del aeropuerto...

                hangares.agregar(hangarNvoOModificado);
                ///...y se agrega el hangar a la clase GestionHangares

            }else {

                throw new ExcepcionCapacidadAeropuerto("La capacidad ingresada para el hangar supera la capacidad del aeropuerto.");
                ///En caso contrario lanza la excepcion.

            }

        }else {

            ///En caso de que el aeropuerto ya contenga el id del hangar, eso significa que se está queriendo
            ///realizar una modificación

            int capacidadViejoHangar = hangares.buscar(hangarNvoOModificado.getIdHangar()).getCapacidadAviones();
            ///Al ser una modificación, es posible que se haya modificado la capacidad del hangar, por lo que se
            ///busca su capacidad antes de realizar el cambio.


            int diferencia = capacidadViejoHangar - hangarNvoOModificado.getCapacidadAviones();
            ///se calcula la diferencia entre la capacidad anterior y la nueva. Si la capacidad no se modificó,
            ///entonces 'diferencia' va a ser 0. Si se achicó la capacidad del hangar, 'diferencia' va a ser un
            ///numero negativo.

            if (getCapacidadTotalAviones() <= getCapacidadActual() + diferencia){
                ///Supongamos que la capacidad total es 20 y la capacidad actual es 15. Si 'diferencia' es por
                ///ejemplo -2, 0 o 5, entonces la capacidad nueva del hangar no sobrepasa el límite.

                hangares.modificar(hangarNvoOModificado, hangarNvoOModificado.getIdHangar());
                ///Al no sobrepasar el límite, se guarda la modificación

            }else {

                ///Siguiendo con el ejemplo de arriba, si 'diferencia' fuera, por ejemplo, 6, entonces
                ///se sobrepasa el límite del aeropuerto

                throw new ExcepcionCapacidadAeropuerto("La capacidad ingresada para el hangar supera la capacidad del aeropuerto.");
                ///Se tira la excepción

            }

        }

    }


    public void validacionDeCapacidad(int nvaCapacidadMaxima)throws ExcepcionCapacidadAeropuerto {

        if (nvaCapacidadMaxima < getCapacidadActual()){

            throw new ExcepcionCapacidadAeropuerto("La capacidad maxima que se quiere ingresar para el aeropuerto es más" +
                    "chica que su capacidad actual .");

        }

    }

    public void verificarAeropuertoActivo() throws ExcepcionAeropuertoInactivo{

        if (!isAeropuertoActivo()){
            throw new ExcepcionAeropuertoInactivo("El aeropuerto se encuentra inactivo.");

        }


    }

}
