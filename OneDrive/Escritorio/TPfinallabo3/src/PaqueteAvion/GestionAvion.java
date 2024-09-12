package PaqueteAvion;

import PaqueteExcepciones.ExcepcionCargaJsonAviones;
import PaqueteGenericidad.InterfazGenericaBLAME;
import PaqueteJsonUtiles.JsonUtiles;
import PaqueteUsuario.Usuario;
import ParteCategoria.Categoria;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class GestionAvion implements InterfazGenericaBLAME <Avion> {

    private ArrayList<Avion>aviones;

    public GestionAvion() {
        aviones = new ArrayList<>();
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    ///AGREGAR UN ITEM------------------------------------------------------------------
    public void agregar(Avion nvoAvion){

        aviones.add(nvoAvion);

    }
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///ELIMINAR UN ITEM-----------------------------------------------------------------
    public boolean eliminar(String matricula){

        Avion avionAEliminar = buscar(matricula);
        return aviones.remove(avionAEliminar);
    }
    ///ELIMINAR UN ITEM-----------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(Avion avion, String matricula){

        for (int i = 0; i < aviones.size(); i++){

            if(aviones.get(i).getMatricula().equals(matricula)){

                aviones.remove(i);
                aviones.add(i,avion);
                return true;
            }

        }
        return false;
    }
    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y NAJA LÒGICA)--------------------


    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public Avion buscar(String matricula){

        for (Avion avionABuscar: aviones){
            if (avionABuscar.getMatricula().equals(matricula)){

                return avionABuscar;
            }
        }

        return null;
    }

    ///CONSULTA POR UN ITEM--------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado(){

        StringBuilder listado = new StringBuilder();

        listado.append("Aviones [");
        for (int i = 0; i < aviones.size(); i++){
            listado.append(aviones.get(i).informacionAvion());
            listado.append(", ");
        }
        listado.append("]");
        return listado;
    }

    public StringBuilder listado(String idHangar){

        StringBuilder listado = new StringBuilder();

        listado.append("Aviones [");
        for (int i = 0; i < aviones.size(); i++){
            if (aviones.get(i).getIdHangar().equals(idHangar)) {
                listado.append(aviones.get(i).informacionAvion());
                listado.append(", ");
            }
        }
        listado.append("]");
        return listado;
    }

    public StringBuilder listadoPorRutaAereaYDisponibilidad(String idRuta){

        StringBuilder listado = new StringBuilder();

        listado.append("Aviones [");
        for (int i = 0; i < aviones.size(); i++){
            if (aviones.get(i) instanceof AvionComercial) {
                if (((AvionComercial)aviones.get(i)).getIdRutaAsignada() != null){
                    ///Se tiene que agregar esta comprobación previa ya que si el método getIdRutaAsignada() devuelve null, puede provocar una excepción de tipo NullPointerException
                    if (((AvionComercial)aviones.get(i)).getIdRutaAsignada().equals(idRuta) && ((AvionComercial) aviones.get(i)).tieneAsientosDisponibles()) {
                    listado.append(aviones.get(i).informacionAvion());
                    listado.append(", ");
                    }
                }
            }
        }
        listado.append("]");
        return listado;
    }
    ///LISTADO---------------------------------------------------------------------------

    public boolean containsMatricula(String matriculaAConsultar){

            for (Avion avion : aviones) {
                if (avion.getMatricula() != null) {
                    if (matriculaAConsultar.equals(avion.getMatricula())) {
                        ///Acá no se necesita verificar si el avión tiene una matrícula distinta de null porque
                        ///el avión está obligado a tener matricula. No se puede instanciar si el avión no posee
                        ///matricula. No hay posibilidad de que se tire un NullPointerException


                        return true;
                    }
                }
            }

        return false;
    }


    public boolean containsRutaAsignada(String idRuta){
        ///No se necesita saber el aeropuerto de salida porque eso ya se comprueba al llamar esta funcion

            for (Avion avion : aviones) {
                if (avion instanceof AvionComercial) {
                    if (avion.getIdRutaAsignada() != null) {
                        ///Se debe realizar esta verificación para evitar un posible fallo
                        ///como asignar una ruta aun avión es opcional, si se ingresa una ruta y directamente
                        ///se utiliza con equals, sin saber si avion.getIdRutaAsignada() != null, puede lanzar un fallo NullPointerException.
                        ///con idRuta no se realiza la verificación porque por más que un usuario apriete enter en un scanner.nextLine,
                        ///ya ese espacio vacío es distinto de null.
                        if (idRuta.equals(avion.getIdRutaAsignada().equals(idRuta) && ((AvionComercial) avion).tieneAsientosDisponibles())) {
                            return true;
                        }
                    }
                }
            }

        return false;
    }



    public boolean liberarAsiento(String idPasaje){

        for (Avion avion : aviones){
            if (avion instanceof AvionComercial){
                if(((AvionComercial) avion).getIdPasajesDelAvion().contains(idPasaje)){
                    if (((AvionComercial) avion).eliminarRegistroPasaje(idPasaje)){
                        return true;

                    }
                }
            }
        }

        return false;

    }






    public static void cargarAvionesDeCargaDesdeElArchivo(GestionAvion avionesTotales) throws ExcepcionCargaJsonAviones {

        String avionesDeCarga;

        avionesDeCarga = JsonUtiles.leer("AvionesDeCarga.json");

        ArrayList <String> objetosPermitidos = new ArrayList<>();
        float cargaMaxima;
        boolean cargaPeligrosa;
        boolean gruaInterna;
        String matricula;
        String idHangar;
        String idRutaAsignada;
        String modelo;
        int kmRecorridos;
        boolean avionActivo;



        try {

            if (avionesDeCarga == null) {

                throw new ExcepcionCargaJsonAviones("No se han podido descargar los aviones de carga.");

            } else {

                JSONArray arregloJSon = new JSONArray(avionesDeCarga);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetoAvionDeCarga = arregloJSon.getJSONObject(i);

                    cargaMaxima = (float) objetoAvionDeCarga.getDouble("cargaMaxima");
                    cargaPeligrosa = objetoAvionDeCarga.getBoolean("cargaPeligrosa");
                    gruaInterna = objetoAvionDeCarga.getBoolean("gruaInterna");
                    matricula = objetoAvionDeCarga.getString("matricula"); ///Lo pasa a String, hay que convertirlo a localDate
                    idHangar = objetoAvionDeCarga.getString("idHangar"); ///se modifica si un usuario administrador modifica la matricula de un avion
                    idRutaAsignada = objetoAvionDeCarga.getString("idRutaAsignada"); /// pasarlo a booleano
                    modelo = objetoAvionDeCarga.getString("modelo"); /// pasarlo a booleano
                    kmRecorridos = objetoAvionDeCarga.getInt("kmRecorridos"); /// pasarlo a booleano
                    avionActivo = objetoAvionDeCarga.getBoolean("avionActivo"); /// pasarlo a booleano

                    JSONArray listaDeObjetosPermitidos = objetoAvionDeCarga.getJSONArray("objetosPermitidos");

                    for (int j = 0; j < listaDeObjetosPermitidos.length(); j++){

                        objetosPermitidos.add(listaDeObjetosPermitidos.getString(i));

                    }


                    AvionDeCarga aCarg = new AvionDeCarga (matricula, idHangar, idRutaAsignada, modelo, kmRecorridos, avionActivo, objetosPermitidos, cargaMaxima, cargaPeligrosa, gruaInterna);
                    avionesTotales.agregar(aCarg);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonAviones("No se ha podido cargar la clase GestionUsuario.");

        }

    }


    public static void cargarAvionesComercialesDesdeElArchivo(GestionAvion avionesTotales) throws ExcepcionCargaJsonAviones {

        String avionesComerciales;

        avionesComerciales = JsonUtiles.leer("AvionesComerciales.json");


        String aerolinea;
        int capacidad;
        HashSet<String> idPasajesDelAvion = new HashSet<>();
        Categoria categoria;

        String matricula;
        String idHangar;
        String idRutaAsignada;
        String modelo;
        int kmRecorridos;
        boolean avionActivo;

        String nombreCateogria;
        boolean asientoCuero;
        boolean jacuzzi;
        boolean wifi;



        try {

            if (avionesComerciales == null) {

                throw new ExcepcionCargaJsonAviones("No se han podido descargar los aviones comerciales.");

            } else {

                JSONArray arregloJSon = new JSONArray(avionesComerciales);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetoComercial = arregloJSon.getJSONObject(i);

                    aerolinea = objetoComercial.getString("cargaMaxima");
                    capacidad = objetoComercial.getInt("cargaPeligrosa");
                    matricula = objetoComercial.getString("gruaInterna");
                    matricula = objetoComercial.getString("matricula"); ///Lo pasa a String, hay que convertirlo a localDate
                    idHangar = objetoComercial.getString("idHangar"); ///se modifica si un usuario administrador modifica la matricula de un avion
                    idRutaAsignada = objetoComercial.getString("idRutaAsignada"); /// pasarlo a booleano
                    modelo = objetoComercial.getString("modelo"); /// pasarlo a booleano
                    kmRecorridos = objetoComercial.getInt("kmRecorridos"); /// pasarlo a booleano
                    avionActivo = objetoComercial.getBoolean("avionActivo"); /// pasarlo a booleano

                    JSONArray jsonArrayidPasajesDelAvion = objetoComercial.getJSONArray("idPasajesDelAvion");

                    for (int j = 0; j < jsonArrayidPasajesDelAvion.length(); j++){

                        idPasajesDelAvion.add(jsonArrayidPasajesDelAvion.getString(i));

                    }

                    JSONObject objetocategoria = objetoComercial.getJSONObject("categoria");
                    nombreCateogria = objetocategoria.getString("nombreCateogria");
                    asientoCuero = objetocategoria.getBoolean("asientoCuero");
                    jacuzzi = objetocategoria.getBoolean("jacuzzi");
                    wifi = objetocategoria.getBoolean("wifi");


                    Categoria categoriaACargar = new Categoria(nombreCateogria, asientoCuero, jacuzzi, wifi);

                    AvionComercial aCom = new AvionComercial (matricula, idHangar, idRutaAsignada, modelo, kmRecorridos, avionActivo, aerolinea, capacidad, idPasajesDelAvion, categoriaACargar);
                    avionesTotales.agregar(aCom);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonAviones("No se ha podido cargar la clase GestionUsuario.");

        }

    }




}
