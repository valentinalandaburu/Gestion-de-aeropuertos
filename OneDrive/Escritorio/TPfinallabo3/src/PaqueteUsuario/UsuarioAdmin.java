package PaqueteUsuario;

public class UsuarioAdmin extends Usuario {

    private String telContactoProf;

    public UsuarioAdmin(String idUsuario,String usuarioAeropuerto,String contrasenia, String username, String nombreCompleto, String dni, String telPersonal, char genero, String telContactoProf) {
        super(idUsuario,usuarioAeropuerto,contrasenia, username, nombreCompleto, dni, telPersonal, genero);

        this.telContactoProf = telContactoProf;
    }


    public UsuarioAdmin(String usuarioAeropuerto, String idUser, String contrasenia, String username, String nombreCompleto, String dni, String telPersonal, char genero, boolean usuarioActivo, String idPasaje, String telContactoProf) {
        super(usuarioAeropuerto, idUser, contrasenia, username, nombreCompleto, dni, telPersonal, genero, usuarioActivo, idPasaje);
        this.telContactoProf = telContactoProf;
    }

    ///ESTE CONSTRUCTOR SE VA A UTILIZAR PARA CUANDO LA PERSONA QUIERA MODIFICAR SU USUARIO. AL YA ESTAR CREADO
    ///NO SE NECESITA ESTABLECER DE CUAL AEROPUERTO ES NI SU ID.
    public String getTelContactoProf() {
        return telContactoProf;
    }

    public void setTelContactoProf(String telContactoProf) {
        this.telContactoProf = telContactoProf;
    }

    @Override
    public StringBuilder informacionUsuarioParaAdmin() {
        StringBuilder listado = new StringBuilder();

                listado.append("UsuarioAdmin{" +
                ", telContactoProf='" + telContactoProf + '\'' +
                ", usuarioAeropuerto='" + usuarioAeropuerto + '\'' +
                ", idUser=" + idUser +
                ", contrasenia='" + contrasenia + '\'' +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", dni='" + dni + '\'' +
                ", telPersonal='" + telPersonal + '\'' +
                ", genero=" + genero +
                "} ");

                return listado;
    }

       @Override
    public StringBuilder informacionUsuarioParaComercial() {
        StringBuilder listado = new StringBuilder();

                listado.append("UsuarioAdmin{" +
                ", telContactoProf='" + telContactoProf + '\'' +
                ", usuarioAeropuerto='" + usuarioAeropuerto + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", genero=" + genero +
                "} ");

                return listado;
    }


}
