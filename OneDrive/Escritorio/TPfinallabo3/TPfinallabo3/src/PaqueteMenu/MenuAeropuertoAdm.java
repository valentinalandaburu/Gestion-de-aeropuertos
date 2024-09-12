package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionCapacidadAeropuerto;
import PaqueteExcepciones.ExcepcionCapacidadHangar;
import PaqueteExcepciones.ExcepcionDatosAeropuerto;
import PaqueteHangar.GestionHangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAeropuertoAdm {

    public static void menuAeropuertoAdm(GestionAvion avionesTotales ,GestionHangar hangares,GestionUsuarios usuarios, GestionRutaAerea rutasDisponibles , Aeropuerto aeropuerto, GestionAeropuerto aeropuertosTotales, HistorialPasaje historialPasajes){

        String nvoNombreAeropuerto;
        String direccion;
        int capacidadTotalAviones;
        boolean aeropuertoActivo;

        int opcion;
        char continuar = 's';
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("---------------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DEL AEROPUERTO DEL ADMINISTRADOR");
            System.out.println("---------------------------------------------------");


            System.out.println("Para modificar el aeropuerto presione 1.");
            System.out.println("Para ver informacion del aeropuerto presione 2.");
            System.out.println("Para ingresar al menu de los hangares del aeropuerto " + aeropuerto.getNombreAeropuerto() + "presione 3.");
            opcion = scanner.nextInt();
            scanner.nextLine();


            switch (opcion) {


                case 1:

                    System.out.println("Usted ha elegido modificar su aeropuerto.");
                    try {
                        System.out.println("Si quiere modificar el nombre y direccion presione s: ");
                        continuar = scanner.nextLine().charAt(0);

                        if (continuar == 's') {
                            System.out.println("Ingrese el nuevo nombre del aeropuerto: ");
                            nvoNombreAeropuerto = scanner.nextLine();     //// se debe validar y tiene que modificar los pasajes

                            System.out.println("Ingrese la nueva direccion del aeropuerto: ");
                            direccion = scanner.nextLine();

                            aeropuertosTotales.validarDatosAeropuerto(nvoNombreAeropuerto, direccion);

                            aeropuerto.setNombreAeropuerto(nvoNombreAeropuerto);
                            aeropuerto.setDireccion(direccion);
                        }


                    } catch (ExcepcionDatosAeropuerto excepcionDatosAeropuerto) {

                        System.out.println("Error. " + excepcionDatosAeropuerto.getMessage());

                    }


                    try {

                        System.out.println("Si quiere modificar la capacidad presione s: ");
                        continuar = scanner.nextLine().charAt(0);

                        if (continuar == 's') {
                            System.out.println("Ingrese la nueva capacidad del aeropurto: ");
                            capacidadTotalAviones = scanner.nextInt();  ///validar
                            aeropuerto.validacionDeCapacidad(capacidadTotalAviones);
                            aeropuerto.setCapacidadTotalAviones(capacidadTotalAviones);
                            ///Ya que aeropuerto contiene la capacidad actual, se verifica con el aeropuerto
                        }

                    } catch (ExcepcionCapacidadAeropuerto excepcionCapacidadAeropuerto) {

                        System.out.println("Error. " + excepcionCapacidadAeropuerto.getMessage());

                    }


                    try {
                        System.out.println("Ingrese con true si el aeropuerto va a estar activo o false para inactivo");
                        aeropuertoActivo = scanner.nextBoolean();
                        aeropuerto.setAeropuertoActivo(aeropuertoActivo);


                    } catch (InputMismatchException inputMismatchException) {

                        System.out.println("Error. El valor ingresado no es valido");

                    }

                    scanner.nextLine(); ///se pone el nextLine en este lugar porque si se pone abajo de scanner.nextBoolean();
                    ///lo ignora si se ingresa mal el dato


                    aeropuertosTotales.modificar(aeropuerto, aeropuerto.getCodigoInternacional());

                    break;


                case 2:

                    System.out.println("usted ha elegido mostrar informacion del aeropuerto");
                    System.out.println(aeropuerto.obtenerInformacion());

                    break;


                case 3:

                    System.out.println("Usted ha elegido ingresar al menu de hangares.");
                    MenuHangarAdmin.menuHangarDelAdmin(aeropuertosTotales, avionesTotales, rutasDisponibles, historialPasajes, usuarios, aeropuerto, hangares);
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;

            }

            System.out.println("Si quiere continuar en el menu del aeropuerto para administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');

    }


}
