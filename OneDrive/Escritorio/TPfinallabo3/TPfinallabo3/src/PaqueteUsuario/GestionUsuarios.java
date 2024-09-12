package PaqueteUsuario;

import PaqueteAvion.Avion;
import PaqueteAvion.AvionComercial;
import PaqueteCC.GeneradorStringAleatorio;
import PaqueteExcepciones.*;
import PaqueteHangar.Hangar;
import PaqueteGenericidad.InterfazGenericaBLAME;
import PaqueteJsonUtiles.JsonUtiles;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static PaqueteCC.GeneradorStringAleatorio.StringAleatorio;

public class GestionUsuarios implements InterfazGenericaBLAME<Usuario> {


    private HashMap<String, Usuario> usuarios;

    public GestionUsuarios() {
        this.usuarios = new HashMap<>();
    }

    ///AGREGAR UN ITEM------------------------------------------------------------------
    public void agregar(Usuario usuario) {

        if (!usuarios.containsKey(usuario.getIdUsuario())) {
            usuarios.put(usuario.getIdUsuario(), usuario);
        } else {
            System.out.println("Ya existe un usuario con ese nombre");
        }

    }
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///ELIMINAR UN ITEM-----------------------------------------------------------------
    public boolean eliminar(String idUsuario) {

        Usuario usuarioABuscar = buscar(idUsuario);
        return usuarios.remove(usuarioABuscar.getIdUsuario(), usuarioABuscar);
    }
    ///ELIMINAR UN ITEM-----------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(Usuario usuario, String idUsuario) {

        if (usuarios.containsKey(idUsuario)) {

            usuarios.put(idUsuario, usuario);
            return true;

        } else {

            return false;
        }

    }
    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------


    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public Usuario buscar(String idUsuario) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getIdUsuario().equals(idUsuario)) {

                return usuario;

            }
        }

        return null;
    }


    ///Metodo utilizado para modificar el nombre del pasajero. Al existir la posibilidad de que exista mas de un
    ///pasajero con el mismo nombre, se utiliza el DNI.

    public Usuario buscar(String username, String contrasenia, String aeropuertoElegido) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia) && usuario.getUsuarioAeropuerto().equals(aeropuertoElegido)) {

                return usuario;

            }
        }

        return null;
    }

    public Usuario ingresoUsuario(String username, String contrasenia, String aeropuertoElegido) throws ExcepcionUsuarioNoEncontrado {

        Usuario usuario = buscar(username, contrasenia, aeropuertoElegido);

        if (usuario == null) {
            throw new ExcepcionUsuarioNoEncontrado("Usuario no encontrado. Compruebe si A) su username es el correcto, B) Si su contrasenia es correcta, C) es usuario de este aeropuerto");
        } else {
            return usuario;
        }

    }

    public boolean contieneUsername(String user) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getUsername().equals(user)) {

                return true;
            }

        }

        return false;
    }

    public boolean contieneDni(String dni) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getDni().equals(dni)) {

                return true;
            }

        }

        return false;
    }

    public boolean contieneTelefono(String telefono) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getTelPersonal().equals(telefono)) {

                return true;
            }

        }

        return false;
    }

    public boolean contieneTelefonoProf(String telefonoProf) {

        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof UsuarioAdmin) {
                if (((UsuarioAdmin) usuario).getTelContactoProf().equals(telefonoProf)) {
                    return true;
                }

            }

        }

        return false;
    }

    public boolean contieneID(String nvoIdUsuario) {

        if (usuarios.containsKey(nvoIdUsuario)) {
            return true;

        } else {

            return false;
        }
    }


    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    ///FIJARSE PORQUE NO SE UTILIZA
    public boolean contieneDatos(String dni, String nombreCompleto) {


        for (Usuario usuario : usuarios.values()) {
            if (usuario.getDni().equals(dni) && usuario.getNombreCompleto().equals(nombreCompleto)) {

                return true;

            }
        }

        return false;
    }

    ///CONSULTA POR UN ITEM--------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado() {

        StringBuilder listado = new StringBuilder();

        listado.append("Usuarios [");
        for (Usuario usu : usuarios.values()) {
            listado.append(usu.informacionUsuarioParaAdmin());
            ///POR TEMAS DE PRIVACIDAD SOLO EL USUARIO ADMINISTRADOR VA A PODER TENER ACCESO A ESTE METODO DE ESTA
            ///CLASE, POR ESO ES QUE SE UTILIZA ESPECIFICAMENTE informacionUsuarioParaAdmin());
        }
        listado.append("]");
        return listado;
    }
    ///LISTADO---------------------------------------------------------------------------

    public ArrayList<Usuario> filtrarPorAeropuerto(String codigoInternacional) {
        ArrayList<Usuario> encontrados = new ArrayList<>();

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getUsuarioAeropuerto().equals(codigoInternacional)) {
                encontrados.add(usuario);
            }
        }

        return encontrados;
    }

    public void validarDatosUsuario(String username, String contr, String dni) throws ExcepcionContraseniaUsuario, ExcepcionUsuarioConMismoDato {

        if (contr.length() < 8) {
            throw new ExcepcionContraseniaUsuario("La clave ingresada es muy pequeña");
        }

        if (contieneUsername(username)) {
            throw new ExcepcionUsuarioConMismoDato("Ya existe un usuario con ese username");

        }

        if (contieneDni(dni)) {
            throw new ExcepcionUsuarioConMismoDato("Ya existe un usuario con ese dni");
        }

    }

    public void validarDatosUsuario(String username, String contr) throws ExcepcionContraseniaUsuario, ExcepcionUsuarioConMismoDato {

        if (contr.length() < 8) {
            throw new ExcepcionContraseniaUsuario("La clave ingresada es muy pequeña");
        }

        if (contieneUsername(username)) {
            throw new ExcepcionUsuarioConMismoDato("Ya existe un usuario con ese username");

        }

    }

    public void validarDatosUsuario(String telefono) throws ExcepcionTelefonoIncorrecto {

        if (contieneTelefono(telefono) || contieneTelefonoProf(telefono)) {
            throw new ExcepcionTelefonoIncorrecto("Telefono registrado");

        }
    }

    public ArrayList<UsuarioAdmin> filtrarUsuariosAdministradores(String nomAeropuerto) {
        ArrayList<UsuarioAdmin> encontrados = new ArrayList<>();
        for (Usuario usu : usuarios.values()) {
            if (usu instanceof UsuarioAdmin && usu.getUsuarioAeropuerto().equals(nomAeropuerto)) {
                encontrados.add((UsuarioAdmin) usu);
            }
        }

        return encontrados;
    }


    public boolean existeUsuariosDeAeropuerto(String idAeropuerto){

        for (Usuario usuario:usuarios.values()){
            if(usuario.getUsuarioAeropuerto().equals(idAeropuerto)){

                return true;

            }
        }

        return false;

    }


    public String crearIdAleatoriaParaUsuario(){

        String idAleatorioParaUsuario;
        boolean controlador = false;

        do {

            idAleatorioParaUsuario = GeneradorStringAleatorio.StringAleatorio(5);
            ///se genera una palabra con o sin sentido aleatoria.
            controlador = contieneID(idAleatorioParaUsuario);
            ///si existe el id, se devuelve true. En caso contrario false.
        }while (controlador);
        ///se van a seguir generando palabras aleatorias hasta que se genere una que no se repita.

        return idAleatorioParaUsuario;
    }


    public void darDeBajaPasajesUsuariosPorAvion(String idAeropuerto){

        for (Usuario usuario : usuarios.values()){
            if (usuario.getIdPasaje() != null && usuario.getUsuarioAeropuerto().equals(idAeropuerto)){

                usuario.setIdPasaje(null);

            }
        }
    }




    public void cargarGestionUsuarioDesdeArchivo(GestionUsuarios usuariosTotales){

        try {
            cargarUsuariosNormalesDesdeElArchivo(usuariosTotales);
            cargarUsuariosAdministradoresDesdeElArchivo(usuariosTotales);
        }catch (ExcepcionCargaJsonUsuario excepcionCargaJsonUsuario){

            System.out.println("Error. " + excepcionCargaJsonUsuario);
        }




    }



    public static void cargarUsuariosNormalesDesdeElArchivo(GestionUsuarios usuariosTotales) throws ExcepcionCargaJsonUsuario {

        String usuariosNormales;
        usuariosNormales = JsonUtiles.leer("UsuariosNormales.json");

        String usuarioAeropuerto;
        String idUser;
        String contrasenia;
        String username;
        String nombreCompleto;
        String dni;
        String telPersonal;
        String generoString;
        char genero;
        boolean usuarioActivo;
        String idPasaje;


        try {

            if (usuariosNormales == null) {

                throw new ExcepcionCargaJsonUsuario("No se han podido descargar los usuarios normales.");

            } else {

                JSONArray arregloJSon = new JSONArray(usuariosNormales);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosUsuariosNormales = arregloJSon.getJSONObject(i);

                    usuarioAeropuerto = objetosUsuariosNormales.getString("usuarioAeropuerto");
                    idUser = objetosUsuariosNormales.getString("idUser");
                    contrasenia = objetosUsuariosNormales.getString("contrasenia");
                    username = objetosUsuariosNormales.getString("username"); ///Lo pasa a String, hay que convertirlo a localDate
                    nombreCompleto = objetosUsuariosNormales.getString("nombreCompleto"); ///se modifica si un usuario administrador modifica la matricula de un avion
                    dni = objetosUsuariosNormales.getString("dni"); /// pasarlo a booleano
                    telPersonal = objetosUsuariosNormales.getString("telPersonal"); /// pasarlo a booleano
                    generoString = objetosUsuariosNormales.getString("genero"); /// pasarlo a booleano
                    usuarioActivo = objetosUsuariosNormales.getBoolean("usuarioActivo"); /// pasarlo a booleano
                    idPasaje = objetosUsuariosNormales.getString("idPasaje"); /// pasarlo a booleano

                    genero = generoString.charAt(0);

                    Usuario usuarioNormalACargar = new Usuario(usuarioAeropuerto, idUser, contrasenia, username, nombreCompleto, dni, telPersonal, genero, usuarioActivo, idPasaje);

                    usuariosTotales.agregar(usuarioNormalACargar);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonUsuario("No se ha podido cargar la clase GestionUsuario.");

        }

    }



    public static void cargarUsuariosAdministradoresDesdeElArchivo(GestionUsuarios usuariosTotales) throws ExcepcionCargaJsonUsuario {

        String usuariosAdministradores;
        usuariosAdministradores = JsonUtiles.leer("UsuariosAdministradores.json");

        String usuarioAeropuerto;
        String idUser;
        String contrasenia;
        String username;
        String nombreCompleto;
        String dni;
        String telPersonal;
        String generoString;
        char genero;
        boolean usuarioActivo;
        String idPasaje;
        String telContactoProf;


        try {

            if (usuariosAdministradores == null) {

                throw new ExcepcionCargaJsonUsuario("No se han podido descargar los usuarios normales");

            } else {

                JSONArray arregloJSon = new JSONArray(usuariosAdministradores);

                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosUsuariosNormales = arregloJSon.getJSONObject(i);

                    usuarioAeropuerto = objetosUsuariosNormales.getString("usuarioAeropuerto");
                    idUser = objetosUsuariosNormales.getString("idUser");
                    contrasenia = objetosUsuariosNormales.getString("contrasenia");
                    username = objetosUsuariosNormales.getString("username"); ///Lo pasa a String, hay que convertirlo a localDate
                    nombreCompleto = objetosUsuariosNormales.getString("nombreCompleto"); ///se modifica si un usuario administrador modifica la matricula de un avion
                    dni = objetosUsuariosNormales.getString("dni"); /// pasarlo a booleano
                    telPersonal = objetosUsuariosNormales.getString("telPersonal"); /// pasarlo a booleano
                    generoString = objetosUsuariosNormales.getString("genero"); /// pasarlo a booleano
                    usuarioActivo = objetosUsuariosNormales.getBoolean("usuarioActivo"); /// pasarlo a booleano
                    idPasaje = objetosUsuariosNormales.getString("idPasaje"); /// pasarlo a booleano
                    telContactoProf = objetosUsuariosNormales.getString("telContactoProf"); /// pasarlo a booleano

                    genero = generoString.charAt(0);

                    UsuarioAdmin usuarioAdminACargar = new UsuarioAdmin(usuarioAeropuerto, idUser, contrasenia, username, nombreCompleto, dni, telPersonal, genero, usuarioActivo, idPasaje, telContactoProf);

                    usuariosTotales.agregar(usuarioAdminACargar);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionCargaJsonUsuario("No se han podido descargar los pasajes");

        }

    }

















    /*
    public static void cargarUsuariosDesdeElArchivo(GestionRutaAerea rutasAereasDisponibles) throws ExcepcionDescargaPasajes {

        String usuariosEnArchivo;
        usuariosEnArchivo = JsonUtiles.leer("Usuarios.json");

        String usuarioAeropuerto;
        String idUser;
        String contrasenia;
        String username;
        String nombreCompleto;
        String dni;
        String telPersonal;
        char genero;
        boolean usuarioActivo;
        String idPasaje;




        try {

            if (usuariosEnArchivo == null) {

                throw new ExcepcionDescargaPasajes("No se han podido descargar las rutas aereas.");

            } else {

                JSONArray arregloJSon = new JSONArray(usuariosEnArchivo);


                for (int i = 0; i < arregloJSon.length(); i++){

                    JSONObject objetosUsuario = arregloJSon.getJSONObject(i);
                    objetosUsuario.g

                    usuarioAeropuerto = objetosUsuario.getString("usuarioAeropuerto");
                     = objetosUsuario.getString("aeropuertoSalida");
                     = objetosUsuario.getString("aeropuertoLlegada");
                     = objetosUsuario.getInt("kmRecorrido"); ///Lo pasa a String, hay que convertirlo a localDate
                     = objetosUsuario.getBoolean("pasajeActivo"); /// pasarlo a booleano

                    RutaAerea rutaAerea = new RutaAerea(idRutaAerea, aeropuertoSalida, aeropuertoLlegada, kmRecorrido, idAvionesAsignados ,rutaActiva);
                    rutasAereasDisponibles.agregar(rutaAerea);

                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionDescargaPasajes("No se han podido descargar los pasajes");

        }catch (DateTimeParseException e) {
            throw new ExcepcionDescargaPasajes("No se ha descargado correctamente la fecha");

        }

    }

     */


}
