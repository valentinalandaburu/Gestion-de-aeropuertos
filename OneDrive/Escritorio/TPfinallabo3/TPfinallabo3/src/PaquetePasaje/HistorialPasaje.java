package PaquetePasaje;

import PaqueteCC.GeneradorStringAleatorio;
import PaqueteExcepciones.*;
import PaqueteJsonUtiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class HistorialPasaje {

    private HashMap<LocalDate, ArrayList<Pasaje>> historialPasajes = new HashMap<>();


    public HashMap<LocalDate, ArrayList<Pasaje>> getHistorialPasajes() {
        return historialPasajes;
    }

    public void agregar(Pasaje p){

        ArrayList <Pasaje> pasajes = new ArrayList<>();

        if(historialPasajes.containsKey(p.getFechaPasaje()))
            pasajes = historialPasajes.get(p.getFechaPasaje());

        pasajes.add(p);
        historialPasajes.put(p.getFechaPasaje(), pasajes);

    }

    public boolean eliminar(String idPasaje){

        ArrayList<Pasaje> noEliminados = new ArrayList<>();
        boolean flag = false;

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if(!pasaje.getIdPassage().equals(idPasaje)){

                    noEliminados.add(pasaje);

                }else {

                    flag = true;

                }
            }
        }

        if (flag == true) {
            historialPasajes.put(noEliminados.get(0).getFechaPasaje(), noEliminados);
        }


        return flag;
    }

    public boolean modificar(Pasaje pasajeModificacion, LocalDate fecha){

        boolean flag = false;

        ArrayList <Pasaje> pasajes = historialPasajes.get(fecha);

        if(pasajes != null){

            for (int i = 0; i < pasajes.size(); i++){
                if (pasajes.get(i).getIdPassage().equals(pasajeModificacion.getIdPassage())){

                    pasajes.remove(i);
                    pasajes.add(i, pasajeModificacion);
                    flag = true;

                }
            }

            historialPasajes.put(fecha, pasajes);
        }

        return flag;
    }

    public Pasaje buscar(String idPasaje){

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if(pasaje.getIdPassage().equals(idPasaje)){

                    return pasaje;

                }

            }
        }


        return null;
    }


    public StringBuilder listado(){

        StringBuilder listado = new StringBuilder();

        listado.append("Historial pasajes [");

        for (ArrayList<Pasaje> pasajesEnDeterminadaFecha: historialPasajes.values()){
            listado.append("Pasajes fecha: " + pasajesEnDeterminadaFecha.get(0).getFechaPasaje().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "{");
            for (Pasaje pasaje : pasajesEnDeterminadaFecha){
                listado.append(pasaje.informacionPasaje());
            }
            listado.append("}");
        }

        listado.append("]");

        return listado;

    }


    public StringBuilder listado(String IdAeropuerto){

        StringBuilder listado = new StringBuilder();

        listado.append("Historial pasajes [");

        for (ArrayList<Pasaje> pasajesEnDeterminadaFecha: historialPasajes.values()){
            listado.append("Pasajes fecha: " + pasajesEnDeterminadaFecha.get(0).getFechaPasaje().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "{");
            for (Pasaje pasaje : pasajesEnDeterminadaFecha){
                if (pasaje.getIdAeropuerto().equals(IdAeropuerto)) {
                    listado.append(pasaje.informacionPasaje());
                }
            }
            listado.append("}");
        }

        listado.append("]");

        return listado;

    }


    public ArrayList<Pasaje> filtrarPorUsuario(String id){

        ArrayList<Pasaje> encontrado = null;

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){

                if (pasaje.getIdPasajero().equals(id)){

                    encontrado.add(pasaje);

                }


            }
        }
        return encontrado;


    }

    public ArrayList<Pasaje> filtrarPorAeropuerto(String codigoInternacional) {
        ArrayList<Pasaje> encontrado = null;

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){

                if (pasaje.getIdAeropuerto().equals(codigoInternacional)){

                    encontrado.add(pasaje);

                }


            }
        }
        return encontrado;

    }


    public Boolean pasajeNoExpiradoContieneAeropuerto(String codigoInternacional){

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if (pasaje.getIdAeropuerto().equals(codigoInternacional) && !pasaje.isPasajeExpirado()){
                    ///Si el pasaje tiene un idAeropuerto (codigo internacional) igual al codigo pasado por
                    ///parametro y ademas es un pasaje sin expirar, devuelve true.


                    return true;

                }
            }
        }
        return false;
    }

    public Boolean pasajeNoExpiradoContieneAvion(String idAvion){ ///el id es la matricula del avion

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if (pasaje.getMatriculaAvion().equals(idAvion) && !pasaje.isPasajeExpirado()){
                    ///Si el pasaje tiene un idAeropuerto (codigo internacional) igual al codigo pasado por
                    ///parametro y ademas es un pasaje sin expirar, devuelve true.


                    return true;

                }
            }
        }
        return false;
    }

    ///Como los usuarios comerciales tienen pasajes, y estos al tener que ser guardados aparte en el historial
    ///si hubiera una modificacion en el usuario, entonces se debe modificar en el historial y viceversa.
    ///En este caso no va a haber una modificacion ni en el pasaje del usuario ni en el historial, por lo que
    ///no se usa la gestion de usuarios.



    ///En este caso no va a haber una modificacion ni en el pasaje del usuario ni en el historial, por lo que
    ///no se usa la gestion de usuarios.


    public Pasaje busquedaPasajePorIdUsuario(String idUsuario)  {

        for (ArrayList<Pasaje> pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if (pasaje.getIdPasajero().equals(idUsuario) && !pasaje.isPasajeExpirado()){

                    return pasaje;

                }
            }
        }
        return null;
    }

    public String generarIdParaPasaje(){

        String stringAleatorio;
        ///Se crea un String aleatorio
        boolean controlador;
        ///esta variable va a validar que el string sea único

        do {

            stringAleatorio = GeneradorStringAleatorio.StringAleatorio(5);
            ///Se genera un string aleatorio de tamaño 5.
            controlador = validarExistenciaId(stringAleatorio);
            ///Si no existe un id igual, controlador se hace true y se sale del bucle.

        }while (!controlador);

        return stringAleatorio;
        ///Se retorna el string aleatorio
    }

    public boolean validarExistenciaId(String idPasaje){

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if (pasaje.getIdPassage().equals(idPasaje)){
                    return false;

                }
            }
        }

        return true;
    }



    public static void cargarHistorialPasajeDesdeElArchivo(HistorialPasaje historialPasajes) throws ExcepcionCargaJsonPasajes {

        String pasajesEnArchivo;
        pasajesEnArchivo = JsonUtiles.leer("HistorialPasajes.json");

        try {

            if (pasajesEnArchivo == null) {

                throw new ExcepcionCargaJsonPasajes("No se han podido descargar los pasajes");

            } else {

                JSONArray arregloJSon = new JSONArray(pasajesEnArchivo);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosPasajes = arregloJSon.getJSONObject(i);

                    String idPasaje = objetosPasajes.getString("idPasaje");
                    String idPasajero = objetosPasajes.getString("idPasajero");
                    String idAeropuerto = objetosPasajes.getString("idAeropuerto");
                    String fechaPasaje = objetosPasajes.getString("fechaPasaje"); ///Lo pasa a String, hay que convertirlo a localDate
                    String matriculaAvion = objetosPasajes.getString("matriculaAvion"); ///se modifica si un usuario administrador modifica la matricula de un avion
                    boolean pasajeActivo = objetosPasajes.getBoolean("pasajeActivo"); /// pasarlo a booleano

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate date = LocalDate.parse(fechaPasaje, formatter);

                    Pasaje nvoPasaje = new Pasaje(idPasaje, idPasajero, idAeropuerto, date, matriculaAvion, pasajeActivo);
                    historialPasajes.agregar(nvoPasaje);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonPasajes("No se han podido descargar los pasajes");

        }catch (DateTimeParseException e) {
            throw new ExcepcionCargaJsonPasajes("No se ha descargado correctamente la fecha");

        }

    }


    public void expirarPasaje(String matriculAvion){

        for (ArrayList<Pasaje>pasajes : historialPasajes.values()){
            for (Pasaje pasaje : pasajes){
                if (pasaje.getMatriculaAvion().equals(matriculAvion)){
                    pasaje.setExpirado(true);  ///el pasaje expiró porque el usuario ya lo usó
                    pasaje.setPasajeActivo(false);  ///dado que expiró, se da de baja

                }
            }
        }

    }

}




