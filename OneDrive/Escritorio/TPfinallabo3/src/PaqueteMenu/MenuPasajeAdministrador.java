package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAvion.GestionAvion;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPasajeAdministrador {

    public static void menuPasajeAdministrador(GestionAvion avionesTotales,Usuario usuario ,Aeropuerto aeropuerto, HistorialPasaje historialPasaje, GestionUsuarios usuarios, GestionRutaAerea rutas){

        ///variable creada para permitir la eleccion de las opciones
        int opcion;
        char continuar;
        ///variable creada para permitir la eleccion de las opciones

        ///variable creada para buscar o saber si existe un pasaje
        String idPasaje;
        ///variable creada para buscar o saber si existe un pasaje

        ///unica variable que se puede modificar en este menu. Las demas se van a modificar automaticamente cuando se realicen cambios en otros menus
        boolean pasajeActivo;
        ///unica variable que se puede modificar en este menu. Las demas se van a modificar automaticamente cuando se realicen cambios en otros menus

        ///variable creada para hacer el do - while
        boolean controlador;
        ///variable creada para hacer el do - while

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("-----------------------------------------");
            System.out.println("BIENVENIDO AL MENU PASAJE ADMINISTRADOR: ");
            System.out.println("-----------------------------------------");

            System.out.println("Si quiere eliminar un pasaje del historial presione 1."); ///ESTO LO PUEDE HACER UN USUARIO TAMBIEN PERO SOLO SI ES UN PASAJE PROPIO
            System.out.println("Si quiere modificar un pasaje del historial presione 2.");
            System.out.println("Si quiere buscar un pasaje del historial presione 3.");
            System.out.println("Si quiere filtrar pasajes del historial por aeropuerto presione 4.");
            System.out.println("Si quiere un listado del historial de pasajes presione 5.");
            System.out.println("Si quiere acceder al menu de pasajes comercial presione 6");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {

                case 1:

                    System.out.println("Usted ha elegido eliminar un pasaje del historial: "); ///ESTO LO PUEDE HACER UN USUARIO TAMBIEN PERO SOLO SI ES UN PASAJE PROPIO
                    System.out.println("Ingrese el id del pasaje: ");
                    idPasaje = scanner.nextLine();
                    ///Se ingresa el

                    Pasaje pasaje = historialPasaje.buscar(idPasaje);

                    if (pasaje != null && pasaje.isExpirado()) {

                        if (historialPasaje.eliminar(idPasaje)) {

                            System.out.println("Pasaje eliminado del historial.");

                        } else {

                            System.out.println("No se pudo eliminar el pasaje del historial");
                        }

                    } else {
                        System.out.println("El pasaje que quiere eliminar se encuentra activo. primeramente" +
                                "debe darlo de baja");


                    }


                    break;


                case 2:

                    System.out.println("Usted ha elegido modificar un pasaje.");
                    System.out.println("En esta seccion solo se va a poder dar alta o baja logica debido a" +
                            "que la informacion restante se va ir modificando a medida que se realizan cambios" +
                            "en el sistema");

                    System.out.println("Ingrese el id del pasaje a modificar: ");
                    idPasaje = scanner.nextLine();
                    Pasaje pasajeAModificar = historialPasaje.buscar(idPasaje);

                    if (pasajeAModificar != null && !pasajeAModificar.isPasajeExpirado()) {
                        ///Si se encuentra el pasaje en el historial pero ya fue usado (isPasajeExpirado), entonces
                        ///no tiene sentido activarlo o desactivarlo.
                        try {
                            System.out.println("Ingrese true para indicar que el pasaje va a estar activo o false para" +
                                    "indicar que va a estar inactivo.");
                            pasajeActivo = scanner.nextBoolean();

                            pasajeAModificar.setPasajeActivo(pasajeActivo);
                            historialPasaje.modificar(pasajeAModificar, pasajeAModificar.getFechaPasaje());

                        } catch (InputMismatchException exception) {
                            System.out.println("Error. El valor ingresado no es booleano");
                        }
                    } else {
                        if (pasajeAModificar == null) {

                            System.out.println("No existe el pasaje");

                        } else {

                            ///Si el pasaje se uso, no tiene sentido querer activarlo. Como ya se utilizo
                            ///la unica opcion posible es que no estè activo

                            System.out.println("No tiene sentido querer dar de alta o baja logica a un pasaje utilizado");
                        }
                    }


                    break;


                case 3:
                    System.out.println("Usted ha elegido buscar un pasaje del historial.");
                    System.out.println("Ingrese el id del pasaje: ");
                    idPasaje = scanner.nextLine();
                    pasaje = historialPasaje.buscar(idPasaje);

                    if (pasaje != null) {
                        if (pasaje.getIdAeropuerto().equals(aeropuerto.getCodigoInternacional())) {
                            System.out.println(pasaje.informacionPasaje());
                        } else {
                            System.out.println("No se encontro el pasaje en este aeropuerto");
                        }
                    } else {
                        System.out.println("No existe pasaje con el id ingresado");
                    }

                    break;


                case 4:

                    System.out.println("Usted ha elegido filtrar los pasajes por aeropuerto. ");
                    ArrayList<Pasaje> encontrados = historialPasaje.filtrarPorAeropuerto(aeropuerto.getCodigoInternacional());

                    if (encontrados != null) {
                        for (Pasaje p : encontrados) {
                            System.out.println(p.informacionPasaje());
                        }
                    } else {

                        System.out.println("No hay pasajes en ese aeropuerto.");
                    }
                    break;


                case 5:

                    System.out.println("Usted ha elegido listar los pasajes del historial. ");
                    System.out.println(historialPasaje.listado(aeropuerto.getCodigoInternacional()));

                    break;

                case 6:

                    MenuPasajeComercial.menuPasajeComercial(usuarios, aeropuerto, historialPasaje, usuario, avionesTotales, rutas);
                    break;

                default:

                    System.out.println("Opcion no valida");

                    break;


            }
            System.out.println("Para continuar en el menu de pasajes del administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');

    }

}
