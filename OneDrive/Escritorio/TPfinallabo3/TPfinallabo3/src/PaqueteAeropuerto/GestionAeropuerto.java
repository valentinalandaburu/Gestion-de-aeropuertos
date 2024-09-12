package PaqueteAeropuerto;

import PaqueteExcepciones.ExcepcionAeropuertoInactivo;
import PaqueteExcepciones.ExcepcionDatosAeropuerto;
import PaqueteGenericidad.InterfazGenericaBLAME;

import java.util.ArrayList;

public class GestionAeropuerto implements InterfazGenericaBLAME <Aeropuerto> {

    ///En todos aquellos métodos que utilicen un método equals con un campo que es opcional llenarlo (osea que
    // utilizar su getter signifique que devuelva null) se tiene que verificar antes que el dato sea distinto
    ///de null
    private ArrayList<Aeropuerto> aeropuertos;

    public GestionAeropuerto() {
        aeropuertos = new ArrayList<>();
    }

    ///AGREGAR UN ITEM------------------------------------------------------------------
    public void agregar(Aeropuerto nvoAeropuerto){

        aeropuertos.add(nvoAeropuerto);

    }
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///ELIMINAR UN ITEM-----------------------------------------------------------------
    public boolean eliminar(String codigoInternacional){

        Aeropuerto aeropuertoAEliminar = buscar(codigoInternacional);
        return aeropuertos.remove(aeropuertoAEliminar);
    }
    ///ELIMINAR UN ITEM-----------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(Aeropuerto aeropuerto, String codigoInternacional){

        for (int i = 0; i < aeropuertos.size(); i++){

            if(aeropuertos.get(i).getCodigoInternacional().equals(codigoInternacional)){

                aeropuertos.remove(i);
                aeropuertos.add(i, aeropuerto);
                return true;
            }

        }
        return false;
    }
    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y NAJA LÒGICA)--------------------


    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public Aeropuerto buscar(String codigoOnombre){

        Aeropuerto aeropuertoBuscado;

        if (esCodigo(codigoOnombre)){
            return aeropuertoBuscado = buscarPorCodigo(codigoOnombre);
        }else{
            return aeropuertoBuscado = buscarPorNombre(codigoOnombre);
        }

    }

    public Aeropuerto buscarPorNombre(String nombre){
        for (Aeropuerto aeropuertoABuscar: aeropuertos){
            if (aeropuertoABuscar.getNombreAeropuerto().equals(nombre)){

                return aeropuertoABuscar;
            }
        }

        return null;
    }

    public Aeropuerto buscarPorCodigo(String codigo){

        for (Aeropuerto aeropuertoABuscar: aeropuertos){
            if (aeropuertoABuscar.getCodigoInternacional().equals(codigo)){

                return aeropuertoABuscar;
            }
        }

        return null;

    }

    public boolean esCodigo(String codigoONombre){

        if(codigoONombre.length() == 3){   ///EL CODIGO DE IATA SOLO PERMITE 3 LETRAS
            return false;
        }

        for (char c : codigoONombre.toCharArray()) {
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {  ///SI ALGUNA LETRA ES MINUSCULA O HAY UN NUMERO ROMPE LAS REGLAS DEL CODIGO DE IATA
                return false;
            }
        }

        return true;
    }

    ///CONSULTA POR UN ITEM--------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado(){

        StringBuilder listado = new StringBuilder();

        listado.append("Aeropuertos [");
        for (int i = 0; i < aeropuertos.size(); i++){
            listado.append(aeropuertos.get(i).obtenerInformacion());
            listado.append(", ");
        }
        listado.append("]");
        return listado;
    }

    ///Esta version solo muestra el nombre de los aeropuertos
    public StringBuilder listadoV2(){

        StringBuilder listado = new StringBuilder();

        listado.append("Aeropuertos [");
        for (int i = 0; i < aeropuertos.size(); i++){
            listado.append(aeropuertos.get(i).getCodigoInternacional());
            listado.append(" ");
            listado.append(aeropuertos.get(i).getNombreAeropuerto());
            listado.append(", ");
        }
        listado.append("]");
        return listado;
    }
    ///LISTADO---------------------------------------------------------------------------


    public void validarDatosAeropuerto(String nombreA)throws ExcepcionDatosAeropuerto {

        for (Aeropuerto a : aeropuertos){
            if (a.getNombreAeropuerto().equals(nombreA)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con ese nombre.");


            }
        }
    }

    public void validarDatosAeropuerto(String nombreA, String direccion) throws ExcepcionDatosAeropuerto {

        for (Aeropuerto a : aeropuertos){

            if (a.getNombreAeropuerto().equals(nombreA)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con ese nombre.");

            }

            if (a.getDireccion().equals(direccion)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con esa direccion.");

            }
        }
    }

    public void validarDatosAeropuerto(String nombreA, String direccion, String codigoInternacional) throws ExcepcionDatosAeropuerto {

        for (Aeropuerto a : aeropuertos){

            if (a.getNombreAeropuerto().equals(nombreA)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con ese nombre.");

            }

            if (a.getDireccion().equals(direccion)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con esa direccion.");

            }

            if (a.getCodigoInternacional().equals(codigoInternacional)){

                throw new ExcepcionDatosAeropuerto("Ya existe un aeropuerto con ese codigo internacional.");

            }

            ///En los métodos de arriba no hay posibilidad de NullPointerException porque son datos que se cargan
            ///obligatoriamente al crear el aeropuerto

        }
    }


    public void verificacionDeAeropuertosActivosParaEstablecerRuta(String aeropuertoSalida, String aeropuertoLlegada) throws ExcepcionAeropuertoInactivo {

        if(!buscarPorCodigo(aeropuertoSalida).isAeropuertoActivo()){
            throw new ExcepcionAeropuertoInactivo("El aeropuerto de salida no se encuentra activo");
        }
        if(!buscarPorCodigo(aeropuertoLlegada).isAeropuertoActivo()){
            throw new ExcepcionAeropuertoInactivo("El aeropuerto de llegada no se encuentra activo");
        }

        ///Las rutas aereas estan obligadas a ser creadas con un aeropuerto de salida y llegada establecidos

    }

}
