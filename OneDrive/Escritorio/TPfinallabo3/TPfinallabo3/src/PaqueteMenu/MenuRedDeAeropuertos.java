package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteHangar.GestionHangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;

import java.util.Scanner;

import static PaqueteMenu.MenuGestionAeropuertos.menuGestionAeropuertos;

public class MenuRedDeAeropuertos {

    public static void menuDeRedAeropuertos(HistorialPasaje hPasajes,GestionUsuarios usuariosTotales,GestionRutaAerea rutasTotales, GestionAeropuerto aeropuertosTotales, GestionHangar hangaresTotales, Aeropuerto aeropuerto, GestionAvion avionesTotales){

        int opcion;
        char continuar;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("-------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE LA RED DE AEROPUERTOS");
            System.out.println("-------------------------------------------");

            System.out.println("Para acceder a la gestion de aeropuertos presione 1.");
            System.out.println("Para revisar todos los hangares presione 2.");
            System.out.println("Para revisar todos los aviones presione 3.");
            System.out.println("Para revisar todos los pasajes presione 4.");
            System.out.println("Para ver todas las rutas existentes presione 5.");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Usted ha seleccionado ingresar a la seccion de aeropuertos: ");
                    MenuGestionAeropuertos.menuGestionAeropuertos(rutasTotales, usuariosTotales, hPasajes, aeropuertosTotales, hangaresTotales);


                    break;


                case 2:

                    System.out.println("Usted ha seleccinado listar todos los hangares");
                    System.out.println(hangaresTotales.listado());

                    break;


                case 3:

                    System.out.println("Usted ha seleccionado listar todos los aviones");
                    System.out.println(avionesTotales.listado());


                    break;

                case 4:

                    System.out.println("Usted ha seleccionado listar el historial de pasajes de todos los aeropuertos.");
                    System.out.println(hPasajes.listado());
                    break;

                case 5:

                    System.out.println("Usted ha seleccionado listar todas las rutas aereas existentes.");
                    System.out.println(rutasTotales.listado());
                    break;


            }

            System.out.println("Si quiere seguir utilizando el menu de red de aeropuertos presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');


    }

}
