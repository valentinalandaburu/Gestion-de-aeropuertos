package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionContraseniaUsuario;
import PaqueteExcepciones.ExcepcionTelefonoIncorrecto;
import PaqueteExcepciones.ExcepcionUsuarioConMismoDato;
import PaqueteExcepciones.ExcepcionUsuarioNoEncontrado;
import PaqueteHangar.GestionHangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import PaqueteUsuario.UsuarioAdmin;

import java.util.Scanner;

public final class Login {

    public static void menuLogin(GestionAvion avionesTotales, GestionHangar hangaresTotales, GestionRutaAerea rutasDisponibles, Aeropuerto aeropuerto, GestionAeropuerto aeropuertosRegistrados, GestionUsuarios usuariosTotales, HistorialPasaje hPasajes) {

        int eleccion;
        String codigo;

        boolean control = false;

        String idAleatorioParaUsuario = "";
        String contrasenia = "";
        String username = "";
        String nombreCompleto;
        String dni = "";
        String telPersonal;
        char genero;
        String telProfesional;
        Scanner scanner = new Scanner(System.in);

        char letra;


        do {

            System.out.println("----------------------------");
            System.out.println("BIENVENIDO AL MENU DE LOGUEO");
            System.out.println("-----------------------------");

            System.out.println("Para acceder al menu del aeropuerto ingrese su usuario o cree uno.");

            System.out.println("Para crear una cuenta presione 1.");
            System.out.println("Para ingresar a su cuenta presione 2.");
            eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion) {

                case 1:


                    idAleatorioParaUsuario = usuariosTotales.crearIdAleatoriaParaUsuario();
                    System.out.println("Usted ha elegido crear una cuenta");

                    do {
                        try {

                            System.out.println("Ingrese su username: ");
                            username = scanner.nextLine();
                            ///Se ingresa el username.

                            System.out.println("Ingrese su contrasenia: ");
                            contrasenia = scanner.nextLine();
                            ///Se ingresa la contraseña.


                            System.out.println("Ingrese su dni: ");
                             dni = scanner.nextLine();
                            ///Se ingresa el dni.

                            usuariosTotales.validarDatosUsuario(username, contrasenia, dni);
                            ///Se validan
                            control = true;

                            } catch (ExcepcionUsuarioConMismoDato | ExcepcionContraseniaUsuario excepcion) {
                                System.out.println("Error. " + excepcion.getMessage());
                                control = false;

                            }
                    } while (!control);

                    do {

                        System.out.println("Ingrese su nombre completo: ");
                        nombreCompleto = scanner.nextLine();
                        ///Se ingresa el nombre completo del usuario

                        System.out.println("Ingrese se numero de contacto: ");
                        telPersonal = scanner.nextLine();
                        ///Se ingresa el numero de telefono

                        try {

                            usuariosTotales.validarDatosUsuario(telPersonal);
                            control = true;

                        } catch (ExcepcionTelefonoIncorrecto e) {
                            System.out.println("Error. " + e.getMessage());
                            control = false;
                        }

                    } while (!control);

                    do {
                        System.out.println("Ingrese su genero: M - F - O");
                        genero = scanner.nextLine().charAt(0);
                        genero = Character.toLowerCase(genero);
                        ///se ingresa el genero

                        if (genero == 'm' || genero == 'f' || genero == 'o') {
                            control = true;
                        } else {
                            control = false;
                        }


                    } while (!control);


                    System.out.println("Para crear un usuario administrador presione 1:");
                    eleccion = scanner.nextInt();
                    scanner.nextLine();
                    ///Se elige entre crear un usuario normal o uno administrador

                    if (eleccion == 1) {

                        ///Si elige crear un usuario administrador,...

                        System.out.println("Ingrese el codigo de verificacion para continuar: ");
                        codigo = scanner.nextLine();
                        ///...se pide ingresar el codigo de permiso.

                        if (codigo.equals("aaa111")) {
                            try {

                                ///Si ingresa bien el codigo...

                                System.out.println("Ingrese el nro de contacto profesional dado por la empresa: ");
                                telProfesional = scanner.nextLine();
                                ///...ingresa su contacto profesional...
                                usuariosTotales.validarDatosUsuario(telProfesional);
                                ///...se valida...

                                UsuarioAdmin usuarioAdmin = new UsuarioAdmin(idAleatorioParaUsuario, aeropuerto.getCodigoInternacional(), contrasenia, username, nombreCompleto, dni, telPersonal, genero, telProfesional);
                                ///...se instancia un UsuarioAdmin,...

                                usuariosTotales.agregar(usuarioAdmin);
                                ///...se lo ingresa a la clase GestionUsuario

                                aeropuerto.agregarIdUsuarioAAeropuerto(usuarioAdmin.getIdUsuario());
                                ///...y se guarda su id en el aeropuerto.


                            } catch (ExcepcionTelefonoIncorrecto e) {
                                System.out.println("Error. " + e.getMessage());
                            }


                        } else {
                            System.out.println("codigo incorrecto");
                        }

                    } else {

                        ///Si se elige crear un usuario normal...

                        Usuario usuarioNormal = new Usuario(idAleatorioParaUsuario, aeropuerto.getCodigoInternacional(), contrasenia, username, nombreCompleto, dni, telPersonal, genero);
                        ///...se instancia un Usuario,...
                        usuariosTotales.agregar(usuarioNormal);
                        ///...se lo ingresa a la clase GestionUsuario...
                        aeropuerto.agregarIdUsuarioAAeropuerto(usuarioNormal.getIdUsuario());
                        ///...y se guarda su id en el aeropuerto.

                    }


                    break;


                case 2:

                    ///El usuario quiere logearse.
                    System.out.println("Ingrese su username: ");
                    username = scanner.nextLine();
                    ///Ingresa su username

                    System.out.println("Ingrese su contrasenia: ");
                    contrasenia = scanner.nextLine();
                    ///Ingresa su contraseña

                    try {

                        ///ingresoUsuario lanza la excepcion ExcepcionUsuarioNoEncontrado si el username o la contrasenia
                        ///no son correctos
                        Usuario usuario = usuariosTotales.ingresoUsuario(username, contrasenia, aeropuerto.getCodigoInternacional());

                        if(usuario.isUsuarioActivo()) {
                            System.out.println("Si quiere ingresar al menu presione 1.");
                            System.out.println("Si quiere eliminar su cuenta presione 2");
                            eleccion = scanner.nextInt();
                            scanner.nextLine();

                            if (eleccion == 1) {
                                if (usuario instanceof UsuarioAdmin) {
                                    UsuarioAdmin usuarioAdmin = (UsuarioAdmin) usuario;
                                    MenuAdministrador.menuAdministrador(avionesTotales, hangaresTotales, usuariosTotales, rutasDisponibles, aeropuertosRegistrados, aeropuerto, usuarioAdmin, hPasajes);
                                    ///Si elige entrear al menu siendo usuario admin, se lo manda al menu del usuario administrador

                                } else {

                                    if (aeropuerto.isAeropuertoActivo()) {

                                        MenuComercial.menuComercial(rutasDisponibles, avionesTotales, usuariosTotales, aeropuerto, usuario, hPasajes);

                                    } else {
                                        System.out.println("No se puede ingresar al menu principal del aeropuerto porque el aeropuerto se encuentra inactivo");
                                    }
                                    ///Si elige entrear al menu siendo usuario normal, se lo manda al menu normal.

                                }
                            }

                            if (eleccion == 2) {

                                aeropuerto.removerIdUsuario(usuario.getIdUsuario());
                                ///Si elige borrar su usuario, directamente se borra del registro de IDs de usuarios del aeropuerto
                                // sin importar si es admin o no


                                usuariosTotales.eliminar(usuario.getIdUsuario());
                                ///Finalmente se borrar su usuario


                            }

                        }


                    } catch (ExcepcionUsuarioNoEncontrado e) {
                        System.out.println("Error" + e.getMessage());
                    }

                    break;

                default:

                    System.out.println("La opcion ingresada no es valida.");

                    break;


            }

            System.out.println("Si quiere seguir utilizando el menu de logueo presione 's': ");
            letra = scanner.nextLine().charAt(0);


        } while (letra == 's');
    }
}