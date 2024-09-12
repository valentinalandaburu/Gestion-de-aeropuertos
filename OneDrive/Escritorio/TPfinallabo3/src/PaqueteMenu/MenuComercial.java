package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionContraseniaUsuario;
import PaqueteExcepciones.ExcepcionTelefonoIncorrecto;
import PaqueteExcepciones.ExcepcionUsuarioConMismoDato;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import PaqueteUsuario.UsuarioAdmin;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuComercial {

    public static void menuComercial(GestionRutaAerea rutasTotales,GestionAvion avionesTotales,GestionUsuarios usuarios, Aeropuerto aeropuerto, Usuario comercial, HistorialPasaje hPasajes) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        String contrasenia;
        String username;
        String nombreCompleto;
        String dni;
        String telPersonal;
        char genero;

        char letra;

        do {
            System.out.println("----------------------------");
            System.out.println("BIENVENIDO AL MENU COMERCIAL");
            System.out.println("----------------------------");

            System.out.println("Si quiere modificar su usuario presione 1.");
            System.out.println("Si quiere ingresar al menu de pasajes presione 2.");
            System.out.println("Si quiere informacion de usuarios administradores presione 3.");
            System.out.println("Si usted quiere ver como esta su informacion cargada en sistema presione 4.");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Usted ha elegido modificar su usuario.");

                    System.out.println("Desea modificar su username y su contrasenia? apriete s para indicar que si: ");
                    letra = scanner.nextLine().charAt(0);

                    if (letra == 's') {

                        try {

                            System.out.println("Ingrese su nuevo username: ");
                            username = scanner.nextLine();

                            System.out.println("Ingrese su nueva contrasenia: ");
                            contrasenia = scanner.nextLine();

                            usuarios.validarDatosUsuario(username, contrasenia);

                            comercial.setUsername(username);
                            comercial.setContrasenia(contrasenia);

                        } catch (ExcepcionUsuarioConMismoDato excepcionUsuarioConMismoDato) {

                            System.out.println("Error. " + excepcionUsuarioConMismoDato.getMessage());

                        } catch (ExcepcionContraseniaUsuario excepcionContraseniaUsuario) {

                            System.out.println("Error. " + excepcionContraseniaUsuario.getMessage());
                        }
                    }

                    System.out.println("Desea cambiar su nombre y apellido? apriete s para indicar que si");
                    letra = scanner.nextLine().charAt(0);

                    if (letra == 's') {

                        System.out.println("Ingrese su nombre completo (primero nombre y despues apellido): ");
                        nombreCompleto = scanner.nextLine();

                        comercial.setNombreCompleto(nombreCompleto);
                    }

                    System.out.println("Desea cambiar su genero? apriete s para indicar que si");
                    letra = scanner.nextLine().charAt(0);

                    if (letra == 's') {
                        System.out.println("Ingrese genero: m - f - o");
                        genero = scanner.nextLine().charAt(0);
                        genero = Character.toLowerCase(genero);

                        if (genero == 'm' || genero == 'f' || genero == 'o') {
                            comercial.setGenero(genero);
                        }

                    }

                    System.out.println("Desea cambiar su numero de contacto? apriete s para indicar que si");
                    letra = scanner.nextLine().charAt(0);


                    if (letra == 's') {

                        try {

                            System.out.println("Ingrese numero: ");
                            telPersonal = scanner.nextLine();

                            usuarios.validarDatosUsuario(telPersonal);
                            comercial.setTelPersonal(telPersonal);

                        } catch (ExcepcionTelefonoIncorrecto excepcionTelefonoIncorrecto) {

                            System.out.println("Error. " + excepcionTelefonoIncorrecto.getMessage());
                        }

                    }

                    System.out.println("Desea modificar su DNI? apriete s para indicar que si");
                    letra = scanner.nextLine().charAt(0);

                    if (letra == 's') {

                        System.out.println("Ingrese DNI: ");
                        dni = scanner.nextLine();

                        comercial.setDni(dni);

                    }

                    if (usuarios.modificar(comercial, comercial.getIdUsuario())) {
                        System.out.println("Usuario modificado.");

                    } else {
                        System.out.println("No se pudo modificar el usuario");
                    }

                    break;

                case 2:
                    System.out.println("Usted ha elegido ir al menu de pasajes.");
                    MenuPasajeComercial.menuPasajeComercial(usuarios, aeropuerto, hPasajes, comercial, avionesTotales, rutasTotales);

                    break;

                case 3:
                    System.out.println("Usted ha elegido saber la informacion de los administradores de este aeropuerto.");
                    ArrayList<UsuarioAdmin> usuariosAdmin = usuarios.filtrarUsuariosAdministradores(aeropuerto.getCodigoInternacional());
                    for (UsuarioAdmin admin : usuariosAdmin) {
                        System.out.println(admin.informacionUsuarioParaComercial());
                    }

                    break;

                case 4:

                    System.out.println("Usted ha elegido ver su informacion: ");
                    System.out.println(comercial.informacionUsuarioParaComercial());

                    break;

                default:
                    System.out.println("Opcion incorrecta.");

                    break;
            }

            System.out.println("Apriete 's' para seguir en el menu comercial: ");
            letra = scanner.nextLine().charAt(0);

        }while (letra == 's');

    }
}
