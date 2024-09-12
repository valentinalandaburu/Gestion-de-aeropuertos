package PaqueteHangar;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteExcepciones.ExcepcionCapacidadHangar;
import PaqueteExcepciones.ExcepcionCargaJsonHangares;
import PaqueteExcepciones.ExcepcionCargaJsonUsuario;
import PaqueteExcepciones.ExcepcionHangarMismosDatos;
import PaqueteGenericidad.InterfazGenericaBLAME;
import PaqueteJsonUtiles.JsonUtiles;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class GestionHangar implements InterfazGenericaBLAME<Hangar> {

    private ArrayList<Hangar>hangares;

    public GestionHangar() {
        hangares = new ArrayList<>();
    }


    public ArrayList<Hangar> getHangares() {
        return hangares;
    }

    ///AGREGAR UN ITEM------------------------------------------------------------------
    public void agregar(Hangar nvoHangar){

        hangares.add(nvoHangar);

    }
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///ELIMINAR UNO O VARIOS ITEMS-----------------------------------------------------------------
    public boolean eliminar(String idHangar){

        Hangar hangarABuscar = buscar(idHangar);
        return hangares.remove(hangarABuscar);
    }
    ///ELIMINAR UN ITEMS-----------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(Hangar modificado, String idHangar){

        for (int i = 0; i < hangares.size(); i++){

            if(hangares.get(i).getIdHangar().equals(idHangar)){

                hangares.remove(i);
                hangares.add(i,modificado);
                return true;
            }

        }
        return false;
    }

    public boolean modificarIdAeropuerto(String idHangar, String nuevoIdAeropuerto){

        for (int i = 0; i < hangares.size(); i++){

            if(hangares.get(i).getIdHangar().equals(idHangar)){

                hangares.get(i).setIdAeropuertoPertenece(nuevoIdAeropuerto);
                return true;
            }

        }
        return false;

    }

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y NAJA LÒGICA)--------------------


    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public Hangar buscar(String idHangar){

        for (Hangar hangar: hangares){
            if(hangar.getIdHangar().equals(idHangar)){

                return hangar;

            }
        }

        return null;
    }
    ///CONSULTA POR UN ITEM--------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado(){

        StringBuilder listado = new StringBuilder();

        listado.append("Hangar [");
        for (int i = 0; i < hangares.size(); i++){

                listado.append(hangares.get(i).obtenerInformacion());
        }
        listado.append("]");
        return listado;
    }

    public StringBuilder listado(String aeropuertoId){

        StringBuilder listado = new StringBuilder();

        listado.append("Hangar [");
        for (Hangar hangar : hangares){

            if(hangar.getIdAeropuerto().equals(aeropuertoId)) {
                ///No hay riesgo de NullPointerException porque siempre que se cree un hangar va a tener que tener el id
                ///del aeropuerto al cual pertenece
                listado.append(hangar.obtenerInformacion());
            }
        }
        listado.append("]");
        return listado;
    }





    ///LISTADO---------------------------------------------------------------------------


    public boolean containsName(String name){

        for(Hangar hangar : hangares){
            if(hangar.getNombreHangar().equals(name)){

                return true;
            }

        }

        return false;
    }

    public boolean containsId(String id){

        for(Hangar hangar : hangares){
            if(hangar.getIdHangar().equals(hangar)){

                return true;
            }

        }

        return false;
    }




    public void verificarSiHayHangarConMismosDatos(String nombreHangar)throws ExcepcionHangarMismosDatos {

        if (containsName(nombreHangar)){

            throw new ExcepcionHangarMismosDatos("Ya existe un hangar con ese nombre");

        }

    }

    public void verificarSiHayHangarConMismosDatos(String idHangar, String nombreHangar)throws ExcepcionHangarMismosDatos {

        if (containsName(nombreHangar)){

            throw new ExcepcionHangarMismosDatos("Ya existe un hangar con ese nombre");

        }

        if (containsId(idHangar)){

            throw new ExcepcionHangarMismosDatos("Ya existe un hangar con ese id");

        }


    }

    public int capacidadTotalHangares(String idAeropuerto){

        int capacidadTotal = 0;

        for (Hangar hangar : hangares){
            if (hangar.getIdAeropuerto().equals(idAeropuerto)) {

                capacidadTotal = capacidadTotal + hangar.getCapacidadAviones();
            }

        }

        ///retorna la capacidad total de los hangares, osea la suma de todas las capacidades de los distintos
        ///hangares de un aeropuerto.
        return capacidadTotal;
    }


    public static void cargarHangaresDesdeElArchivo(GestionHangar hangaresTotales) throws ExcepcionCargaJsonHangares {

        String hangares;
        hangares = JsonUtiles.leer("Hangares.json");

        String idHangar;
        String idAeropuertoPertenece;
        String nombreHangar;
        int capacidadAviones;
        boolean habilitado;
        HashSet<String> idAvionesEnHangar = new HashSet<>();

        try {

            if (hangares == null) {

                throw new ExcepcionCargaJsonHangares("No se han podido descargar los usuarios normales.");

            } else {

                JSONArray arregloJSon = new JSONArray(hangares);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosHangares = arregloJSon.getJSONObject(i);

                    idHangar = objetosHangares.getString("idHangar");
                    idAeropuertoPertenece = objetosHangares.getString("idAeropuertoPertenece");
                    nombreHangar = objetosHangares.getString("nombreHangar");
                    capacidadAviones = objetosHangares.getInt("capacidadAviones");
                    habilitado = objetosHangares.getBoolean("habilitado");

                    JSONArray arraysDeidAvionesEnHangar = objetosHangares.getJSONArray("idAvionesEnHangar");

                    for (int j = 0; j < arregloJSon.length(); j++){
                        idAvionesEnHangar.add(arraysDeidAvionesEnHangar.getString(i));

                    }

                    Hangar nvoHangar =new Hangar(idHangar, idAeropuertoPertenece, nombreHangar, capacidadAviones, habilitado, idAvionesEnHangar);


                    hangaresTotales.agregar(nvoHangar);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonHangares("No se ha podido cargar la clase GestionUsuario.");

        }

    }


}
