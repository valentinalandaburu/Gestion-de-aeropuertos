package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteExcepciones.*;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import PaqueteUsuario.UsuarioAdmin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUsuariosAdmistrador {
    public static void menuAdminSeccionUsuarios(Aeropuerto aeropuerto, HistorialPasaje historialPasajes, GestionUsuarios usuarios){

        ///otras variables
        int opcion;
        Scanner scanner = new Scanner(System.in);
        boolean controlador = false;
        char continuar;
        ///otras variables


        ///variable que se va a usar para localizar al usuario
        String idUsuario;
        ///variable que se va a usar para localizar al usuario

        ///variable que se van a usar para modificar a un usuario general
        String contrasenia = "";
        String username = "";
        String telPersonal = "";
        char genero;
        boolean usuarioActivo = true;
        ///variable que se van a usar para modificar a un usuario general

        ///variable que se van a usar para modificar a un usuario en general
        String telContactoProf = "";
        ///variable que se van a usar para modificar a un usuario en general

        ///no hay variables especificas para modificar un usuario comercial porque este solo tiene como
        ///distintivo el pasaje, el cual se va a modificar automaticamente en cada funcion que cambie un
        ///valor que contenga el objeto pasaje (como el DNI de un usuario o su nombre).

        do {

            System.out.println("------------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE USUARIOS DEL ADMINISTRADOR");
            System.out.println("------------------------------------------------");


            System.out.println("Si quiere eliminar un usuario presione 1.");
            System.out.println("Si quiere modificar un usuario presione 2.");
            System.out.println("Si quiere buscar un usuario presione 3.");
            System.out.println("Si quiere listar los usuarios del aeropuerto presione 4."); ///filtrado

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {


                case 1:
                    ///Parecer estar bien, testear

                    System.out.println("Usted ha ingresado a eliminar un usuario");
                    System.out.println("Ingrese el id del usuario.");

                    idUsuario = scanner.nextLine();

                    Usuario usu = usuarios.buscar(idUsuario);

                    if (usu.getIdPasaje() == null) {

                        if (usuarios.eliminar(idUsuario)) {   ///si llego a esta instancia es porque o es un usuario
                            ///admin o es un usuario normal sin pasaje o solo tiene pasajes expirados.

                            System.out.println("Usuario eliminado");

                        } else {

                            System.out.println("No se pudo eliminar al usuario");

                        }

                    } else {
                        ///Si llega a esta instancia es porque tiene un pasaje sin expirar.

                        System.out.println("No se puede borrar un usuario comercial con un pasaje no expirado");


                    }

                    break;


                case 2:

                    ///Parecer estar bien, testear
                    System.out.println("Usted ha elegido modificar un usuario.");

                    System.out.println("Ingrese el id del usuario a modificar");
                    idUsuario = scanner.nextLine();
                    Usuario usuarioAModificar = usuarios.buscar(idUsuario);

                    if (usuarioAModificar != null) {
                        do {
                            try {

                                System.out.println("Ingrese el nuevo username: ");
                                username = scanner.nextLine();

                                System.out.println("Ingrese la nueva contrasenia: ");
                                contrasenia = scanner.nextLine();

                                usuarios.validarDatosUsuario(username, contrasenia);

                                controlador = true;

                            } catch (ExcepcionContraseniaUsuario excepcionContraseniaUsuario) {
                                controlador = false;

                                System.out.println("Error " + excepcionContraseniaUsuario.getMessage());

                            } catch (ExcepcionUsuarioConMismoDato excepcionUsuarioConMismoDato) {

                                System.out.println("Error. " + excepcionUsuarioConMismoDato.getMessage());
                                controlador = false;


                            }

                        } while (!controlador);


                        do {

                            try {
                                System.out.println("Ingrese el nuevo numero de telefono");

                                telPersonal = scanner.nextLine();
                                usuarios.validarDatosUsuario(telPersonal);
                                controlador = true;

                            } catch (ExcepcionTelefonoIncorrecto excepcionTelefonoIncorrecto) {
                                System.out.println("Error. " + excepcionTelefonoIncorrecto.getMessage());
                                controlador = false;
                            }

                        } while (!controlador);


                        do {
                            System.out.println("Ingrese el nuevo genero: m-f-o: ");
                            genero = scanner.nextLine().charAt(0);
                            genero = Character.toLowerCase(genero);

                            if (genero == 'm' || genero == 'f' || genero == 'o') {

                                controlador = true;

                            } else {
                                controlador = false;
                            }

                        } while (!controlador);

                        do {

                            try {
                                System.out.println("Ingrese el nuevo estado del usuario: (true para activo, false para inactivo)");
                                usuarioActivo = scanner.nextBoolean();
                                scanner.nextLine();
                                controlador = true;

                            } catch (InputMismatchException exception) {
                                System.out.println("Error. Debe ingresar un booleano");
                                controlador = false;
                            }

                        } while (!controlador);

                        usuarioAModificar.setContrasenia(contrasenia);
                        usuarioAModificar.setUsername(username);
                        usuarioAModificar.setTelPersonal(telPersonal);
                        usuarioAModificar.setGenero(genero);
                        usuarioAModificar.setUsuarioActivo(usuarioActivo);

                        if (usuarioAModificar instanceof UsuarioAdmin) {
                            do {

                                UsuarioAdmin usuAdminAModificar = (UsuarioAdmin) usuarioAModificar;
                                System.out.println("Ingrese el nuevo numero de celular profesional");
                                telContactoProf = scanner.nextLine();

                                try {
                                    usuarios.validarDatosUsuario(telContactoProf);
                                    usuAdminAModificar.setTelContactoProf(telContactoProf);
                                    controlador = true;

                                    usuarios.modificar(usuAdminAModificar, usuAdminAModificar.getIdUsuario());

                                } catch (ExcepcionTelefonoIncorrecto excepcionTelefonoProf) {

                                    System.out.println("Error. " + excepcionTelefonoProf.getMessage());
                                    controlador = false;

                                }

                            } while (!controlador);
                        } else {

                            usuarios.modificar(usuarioAModificar, usuarioAModificar.getIdUsuario());

                        }

                    } else {

                        System.out.println("No existe usuario con ese ID");
                    }


                    break;


                case 3:

                    ///Parecer estar bien, testear
                    System.out.println("Usted ha elegido buscar un usuario.");
                    System.out.println("Ingrese el id del usuario");
                    idUsuario = scanner.nextLine();

                    Usuario usuarioBuscado = usuarios.buscar(idUsuario);

                    if(usuarioBuscado != null) {
                        if (usuarioBuscado instanceof UsuarioAdmin) {

                            UsuarioAdmin usuAdBuscado = (UsuarioAdmin) usuarioBuscado;
                            System.out.println(usuAdBuscado.informacionUsuarioParaAdmin());

                        } else {

                            System.out.println(usuarioBuscado.informacionUsuarioParaAdmin());
                        }
                    }else {
                        System.out.println("No se ha encontrado al usuario");
                    }

                    break;


                case 4:

                    ///Parecer estar bien, testear
                    System.out.println("Usted ha elegido ver el listado de los usuarios del propio aeropuerto");

                    ArrayList<Usuario> encontrados = usuarios.filtrarPorAeropuerto(aeropuerto.getCodigoInternacional());


                    for (Usuario u : encontrados) {
                           System.out.println(u.informacionUsuarioParaAdmin());
                    }


                    break;


                default:

                    System.out.println("Opcion no valida");
                    break;

            }


            System.out.println("Para continuar en el menu de usuarios del administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);


        }while (continuar == 's');


    }



}
