package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteHangar.GestionHangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.UsuarioAdmin;

import java.util.Scanner;

public final class MenuAdministrador {

    public static void menuAdministrador(GestionAvion avionesTotales, GestionHangar hangaresTotales, GestionUsuarios usuariosTotales, GestionRutaAerea rutasDisponibles, GestionAeropuerto aeropuertosRegistrados, Aeropuerto aeropuerto, UsuarioAdmin admin, HistorialPasaje hPasajes){

        int opcion;
        Scanner scanner = new Scanner(System.in);

        String clave;
        char continuar;



        do {

            System.out.println("------------------------------------------------------------------");
            System.out.println("Saludos " + admin.getNombreCompleto() + ", bienvenido al menu de administrador");
            System.out.println("------------------------------------------------------------------");

            System.out.println("Si quiere ingresar a la seccion usuarios presione 1.");
            System.out.println("Si quiere ingresar a la seccion de aeropuerto presione 2.");
            System.out.println("Si quiere ingresar a la seccion de pasajes presione 3.");
            System.out.println("Si quiere ingresar a la torre de control presione 4 .");
            System.out.println("Si quiere ingresar a la seccion de rutas aereas presione 5.");
            System.out.println("Si quiere ingresar al menu de red de aeropuertos presione 6.");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    MenuUsuariosAdmistrador.menuAdminSeccionUsuarios(aeropuerto, hPasajes, usuariosTotales);

                    break;
                case 2:

                    MenuAeropuertoAdm.menuAeropuertoAdm(avionesTotales, hangaresTotales, usuariosTotales, rutasDisponibles, aeropuerto, aeropuertosRegistrados, hPasajes);


                    break;
                case 3:

                    MenuPasajeAdministrador.menuPasajeAdministrador(avionesTotales, admin, aeropuerto, hPasajes, usuariosTotales, rutasDisponibles);

                    break;

                case 4:

                    TorreDeControl.torreDeControl(hPasajes, usuariosTotales, aeropuerto, rutasDisponibles, hangaresTotales, avionesTotales, aeropuertosRegistrados);

                    break;

                case 5:

                    MenuGestionRutas.menuGestionRutas(aeropuerto, rutasDisponibles, aeropuertosRegistrados);

                    break;


                case 6:
                    System.out.println("Ingrese el codigo de permiso para ingresar a esta seccion: ");
                    clave = scanner.nextLine();

                    if (clave.equals("aaa111")) {

                        MenuRedDeAeropuertos.menuDeRedAeropuertos(hPasajes, usuariosTotales, rutasDisponibles, aeropuertosRegistrados, hangaresTotales, aeropuerto, avionesTotales);

                    } else {
                        System.out.println("Usted no tiene permiso para entrar a este aerea");

                    }


                    break;

                default:

                    System.out.println("La opcion ingresada no es valida");

                    break;


            }

            System.out.println("Si quiere seguir en el menu del administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');




    }


}
