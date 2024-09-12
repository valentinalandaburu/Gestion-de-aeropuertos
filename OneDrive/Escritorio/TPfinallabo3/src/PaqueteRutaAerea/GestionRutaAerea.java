package PaqueteRutaAerea;

import PaqueteExcepciones.*;
import PaqueteJsonUtiles.JsonUtiles;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteUsuario.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GestionRutaAerea {

    private HashMap<String, RutaAerea> rutasAereasDisponibles = new HashMap<>();

    public void agregar(RutaAerea ruta) {

        rutasAereasDisponibles.put(ruta.getIdRutaAerea(), ruta);

    }
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///ELIMINAR UN ITEM-----------------------------------------------------------------
    public boolean eliminar(String idRuta) {

        RutaAerea rut = buscar(idRuta);
        return rutasAereasDisponibles.remove(rut.getIdRutaAerea(), rut);
    }
    ///ELIMINAR UN ITEM-----------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(RutaAerea ruta, String idRuta) {

        if (rutasAereasDisponibles.containsKey(idRuta)) {

            RutaAerea aModificar = rutasAereasDisponibles.get(idRuta);
            aModificar = ruta;
            rutasAereasDisponibles.put(idRuta, ruta);
            return true;

        } else {

            return false;
        }

    }
    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y NAJA LÒGICA)--------------------


    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public RutaAerea buscar(String idRuta) {

        for (RutaAerea rut : rutasAereasDisponibles.values()) {
            if (rut.getIdRutaAerea().equals(idRuta)) {

                return rut;

            }
        }

        return null;
    }

    public RutaAerea buscar(String aeropuertoSalida, String aeropuertoLlegada) {

        for (RutaAerea rutaAerea : rutasAereasDisponibles.values()) {
            if (rutaAerea.getAeropuertoSalida().equals(aeropuertoSalida) && rutaAerea.getAeropuertoLlegada().equals(aeropuertoLlegada)) {

                return rutaAerea;

            }

        }

        return null;
    }

    public boolean containsKey(String idRutaAerea) {

        if (rutasAereasDisponibles.containsKey(idRutaAerea)) {

            return true;
        }

        return false;
    }

    public boolean containsRecorrido(String aeropuertoSalida, String aeropuertoLlegada) {

        for (RutaAerea ruta : rutasAereasDisponibles.values()) {
            if (ruta.getAeropuertoSalida().equals(aeropuertoSalida) && ruta.getAeropuertoLlegada().equals(aeropuertoLlegada)) {

                return true;
            }

        }

        return false;
    }

    ///CONSULTA POR UN ITEM--------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado() {

        StringBuilder listado = new StringBuilder();

        listado.append("Rutas [");
        for (RutaAerea ruta : rutasAereasDisponibles.values()) {
                listado.append(ruta.listado());
                listado.append(", ");
        }
        listado.append("]");
        return listado;
    }

    ///Este metodo esta pensado para que el usuario comercial tenga una lista de las rutas aereas que
    ///existen y buscar un avion que tenga esa ruta aerea en caso de que haya. Revisar esto para ver si
    ///se puede mejorar y hacer que el usuario pueda ver solo las rutas disponibles que aparte de que
    ///salgan de su aeropuerto, hayan aviones que las tengan
    /*
    public StringBuilder listado(String nombreAeropuerto){

        StringBuilder listado = new StringBuilder();

        listado.append("Rutas que salen desde el aeropuerto " + nombreAeropuerto + ": ");

        for (RutaAerea ruta : rutasAereasDisponibles.values()){
            if (ruta.getAeropuertoSalida().equals(nombreAeropuerto)){

                listado.append(ruta.listado());

            }
        }

        listado.append("}");

        return listado;
    }

     */
    ///LISTADO---------------------------------------------------------------------------


    ///FILTRADO-------------------------------------------------------------------------
    //En menu avion suplantar el metodo listado por esta:
    public ArrayList<RutaAerea> filtradoPorNombreAeropuerto(String codigoIATA) {

        ArrayList<RutaAerea> rutaAereasEncontradas = new ArrayList<>();

        for (RutaAerea ruta : rutasAereasDisponibles.values()) {
            if (ruta.getAeropuertoSalida().equals(codigoIATA)) {

                rutaAereasEncontradas.add(ruta);

            }
        }

        return rutaAereasEncontradas;
    }

    public void listadoRutasPorAeropuerto(String codigoIATA) {

        ArrayList<RutaAerea> rutasPosibles = filtradoPorNombreAeropuerto(codigoIATA);

        for (RutaAerea ruta : rutasPosibles) {

            System.out.println(ruta.listado());

        }


    }

    ///FILTRADO-------------------------------------------------------------------------


    public void validarRutaAerea(String aeropuertoSalida, String aeropuertoLlegada, String idRuta) throws ExcepcionRutaConMismoDato {

        if (containsKey(idRuta)) {
            throw new ExcepcionRutaConMismoDato("Ya existe una ruta con ese id");
        }

        if (containsRecorrido(aeropuertoSalida, aeropuertoLlegada)) {
            throw new ExcepcionRutaConMismoDato("Ya existe una ruta con ese recorrido");
        }
    }

    public void validarRutaAerea(String aeropuertoSalida, String aeropuertoLlegada) throws ExcepcionRutaConMismoDato {

        if (containsRecorrido(aeropuertoSalida, aeropuertoLlegada)) {
            throw new ExcepcionRutaConMismoDato("Ya existe una ruta con ese recorrido");
        }
    }


    public boolean existeDeAeropuertoEnRutas(String codigoIATA) {

        for (RutaAerea ruta : rutasAereasDisponibles.values()) {
            if (ruta.getAeropuertoSalida().equals(codigoIATA) || ruta.getAeropuertoLlegada().equals(codigoIATA)) {
                return true;
            }
        }

        return false;
    }

    public static void cargarGestionRutaAereaDesdeElArchivo(GestionRutaAerea rutasAereasDisponibles) throws ExcepcionCargaJsonRutaAerea {

        String rutasEnArchivo;
        rutasEnArchivo = JsonUtiles.leer("RutasAereas.json");


        HashSet<String> idAvionesAsignados = new HashSet<>();
        String idAvion;

        try {

            if (rutasEnArchivo == null) {

                throw new ExcepcionCargaJsonRutaAerea("No se han podido descargar las rutas aereas.");

            } else {

                JSONArray arregloJSon = new JSONArray(rutasEnArchivo);


                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosRutasAereas = arregloJSon.getJSONObject(i);
                    JSONArray idDeAviones = new JSONArray(objetosRutasAereas.getJSONArray("idDeAviones"));

                    for (int j = 0; j < idDeAviones.length(); j++){
                        idAvionesAsignados.add(idDeAviones.getString(i));

                    }

                    String idRutaAerea = objetosRutasAereas.getString("idRutaAerea");
                    String aeropuertoSalida = objetosRutasAereas.getString("aeropuertoSalida");
                    String aeropuertoLlegada = objetosRutasAereas.getString("aeropuertoLlegada");
                    int kmRecorrido = objetosRutasAereas.getInt("kmRecorrido"); ///Lo pasa a String, hay que convertirlo a localDate
                    boolean rutaActiva = objetosRutasAereas.getBoolean("pasajeActivo"); /// pasarlo a booleano

                    RutaAerea rutaAerea = new RutaAerea(idRutaAerea, aeropuertoSalida, aeropuertoLlegada, kmRecorrido, idAvionesAsignados ,rutaActiva);
                    rutasAereasDisponibles.agregar(rutaAerea);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonRutaAerea("No se han podido cargar las rutas aereas a la clase gestora");

        }

    }



    /*
    public void modificarNombreAeropuertoSalida(String vjoNombreAeropuerto, String nvoNombreAeropuerto){

        for (RutaAerea ruta : rutasAereasDisponibles.values()){
            if (ruta.getAeropuertoSalida().equals(vjoNombreAeropuerto)){

                ruta.setAeropuertoLlegada(nvoNombreAeropuerto);

            }
        }

    }
    */


}
