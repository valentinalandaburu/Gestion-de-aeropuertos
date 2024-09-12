package PaqueteAvion;

import PaqueteExcepciones.ExcepcionAsientosTotales;
import PaqueteExcepciones.ExcepcionCapacidadAviones;
import PaqueteExcepciones.ExcepcionPasajeComprado;
import ParteCategoria.Categoria;

import java.util.ArrayList;
import java.util.HashSet;

public class AvionComercial extends Avion implements Hospitalidad{

    private String aerolinea; ///se puede modificar
    private int capacidad;  ///se puede modificar siempre y cuando no sea un valor mas chico que la cantidad de asientos ocupados o mas grande que la capacidad permitida por el aeropuerto

    private HashSet<String>idPasajesDelAvion;
    ///Coleccion que contiene todos los id de los pasajes que compraron los usuarios para este avion.
    ///Todos los pasajes que esten dentro de esta colección estan sin expirar.

    private Categoria categoria;


    ///CONSTRUCTORES--------------------------------------------------------------------
    public AvionComercial(String matricula, String modelo, int kmRecorridos, String idHangar ,int capacidad, String aerolinea, String nombreCateogria, boolean asientoCuero, boolean jacuzzi, boolean wifi) {
        super(matricula, modelo, kmRecorridos, idHangar);
        this.aerolinea = aerolinea;
        this.capacidad = capacidad;
        categoria = new Categoria(nombreCateogria, asientoCuero, jacuzzi, wifi);
        this.idPasajesDelAvion = new HashSet<>();
    }

    public AvionComercial(String matricula, String idHangar, String idRutaAsignada, String modelo, int kmRecorridos, boolean avionActivo, String aerolinea, int capacidad, HashSet<String> idPasajesDelAvion, Categoria categoria) {
        super(matricula, idHangar, idRutaAsignada, modelo, kmRecorridos, avionActivo);
        this.aerolinea = aerolinea;
        this.capacidad = capacidad;
        this.idPasajesDelAvion = idPasajesDelAvion;
        this.categoria = categoria;
    }

    ///CONSTRUCTORES--------------------------------------------------------------------




    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public HashSet<String> getIdPasajesDelAvion() {
        return idPasajesDelAvion;
    }

    public void setIdPasajesDelAvion(HashSet<String> idPasajesDelAvion) {
        this.idPasajesDelAvion = idPasajesDelAvion;
    }

    public boolean tieneAsientosDisponibles(){
        if (getIdPasajesDelAvion() != null) {
            if (getIdPasajesDelAvion().size() < getCapacidad()) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarRegistroPasaje(String idPasaje){

        if (this.idPasajesDelAvion.remove(idPasaje)){
            return true;
        }else{
            return false;
        }
    }

    public StringBuilder informacionAvion(){

        StringBuilder listado = new StringBuilder();
        listado.append("AvionComercial{" +
                "aerolinea='" + aerolinea + '\'' +
                ", capacidad=" + capacidad +
                ", asientos ocupados=" + getIdPasajesDelAvion().size() +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kmRecorridos=" + kmRecorridos +
                ", avionActivo=" + avionActivo +
                "} ");

        return listado;
    }

    public StringBuilder informacionAvionParaUsuarioNormal(){

        StringBuilder listado = new StringBuilder();
        listado.append("AvionComercial{" +
                "aerolinea='" + aerolinea + '\'' +
                ", capacidad=" + capacidad +
                ", asientos ocupados=" + getIdPasajesDelAvion().size() +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kmRecorridos=" + kmRecorridos +
                "} ");

        return listado;
    }

    public void saludarPasajeros(){
        System.out.println("Se esta saludando a los pasajeros, se esta presentando el personal y dando las" +
                "indicaciones necesarias");
    }
    public void darDesayuno(){

        System.out.println("Se esta dando el desayuno a los pasajeros");

    }
    public void darAlmuerzo(){

        System.out.println("Se esta dando el almuerzo a los pasajeros");


    }
    public void darMerienda(){
        System.out.println("Se esta dando la merienda a los pasajeros");


    }
    public void darCena(){

        System.out.println("Se esta dando la cena a los pasajeros");
    }



    ///valor = asientos que se quiere establecer en la categoria
    ///capacidadTotalAvion = el maximo que permite el avion de asientos
    ///asientosDeclarados = la suma de todos los asientos de las distintas categorias
    public void validarAsientos(int valor,int capacidadTotalAvion ,int asientosDeclarados)throws ExcepcionAsientosTotales{


        ///Si la cantidad de asientos que se quiere establecer en la categoria es un numero negativo, no lo permite.
        if (valor<0){
            throw  new ExcepcionAsientosTotales("El valor de asientos para la categoria debe ser positivo");
        }

        ///Si la cantidad de asientos que se quiere establecer en la categoria es 0, no lo permite.
        if(valor == 0){
            throw  new ExcepcionAsientosTotales("No se puede tener una clase con 0 asientos");
        }

        ///Si la cantidad de asientos que se quiere establecer, sumado a la cantidad de asientos de las otras categorias del avion, es mayor a la cantidad maxima de asientos permitidos por el avion, no lo permite.
        if (valor + asientosDeclarados > capacidadTotalAvion){

            throw new ExcepcionAsientosTotales("La suma de los asientos de las distintas categorias con la cantidad de asientos que se quiere ingresar para esta categoria, supera la capacidad del avion");
        }
    }


    ///valor = asientos que se quiere establecer en la categoria
    ///capacidadTotalAvion = el maximo que permite el avion de asientos
    ///asientosDeclarados = la suma de todos los asientos de las distintas categorias
    ///asientosOcupadosCateg = asientos ocupados en la categoria
    public void validarAsientos(int valor,int capacidadTotalAvion,int asientosDeclarados ,int asientosOcupadosCateg)throws ExcepcionAsientosTotales{

        ///Si la cantidad de asientos que se quiere establecer en la categoria es un numero negativo, no lo permite.
        if (valor<0){
            throw new ExcepcionAsientosTotales("El valor de asientos para la categoria debe ser positivo");
        }

        ///Si la cantidad de asientos que se quiere establecer en la categoria es 0, no lo permite.
        if(valor == 0){
            throw new ExcepcionAsientosTotales("No se puede tener una clase con 0 asientos");
        }

        ///Si la cantidad de asientos que se quiere establecer es menor a los asientos comprados (ocupados) en la categoria, no lo permite.
        if(valor < asientosOcupadosCateg){
            throw new ExcepcionAsientosTotales("La cantidad de asientos que se quiere establecer es menor a los asientos ocupados");
        }

        ///Si la cantidad de asientos que se quiere establecer, sumado a la cantidad de asientos de las otras categorias del avion, es mayor a la cantidad maxima de asientos permitidos por el avion, no lo permite.
        if (valor + asientosDeclarados > capacidadTotalAvion){
            throw new ExcepcionAsientosTotales("La suma de los asientos de las distintas categorias con la cantidad de asientos que se quiere ingresar para esta categoria, supera la capacidad del avion");
        }
    }

    public void validarExistenciaPasaje() throws ExcepcionPasajeComprado {
       if(!getIdPasajesDelAvion().isEmpty()){

           throw new ExcepcionPasajeComprado("Hay pasajes comprados para este avion.");

       }
    }


    public void validarNuevaCapacidadAvion(int nvacapacidadAvion)throws ExcepcionAsientosTotales {

        if (nvacapacidadAvion < getIdPasajesDelAvion().size()){
            throw new ExcepcionAsientosTotales("La nueva capacidad que se quiere establecer es menor" +
                    "a la cantidad de asientos ocupados");

        }

    }

    @Override
    public void vuelo(int kmDeRuta) {

        saludarPasajeros();

        if (kmDeRuta < 100){
            darDesayuno();
        }

        if (kmDeRuta < 200){
            darAlmuerzo();
        }

        if (kmDeRuta < 300){

            darMerienda();
        }

        if (kmDeRuta >= 300){

            darAlmuerzo();
        }

        verificarKilometrajeAvion();

    }
}