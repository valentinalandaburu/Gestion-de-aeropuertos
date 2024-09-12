package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionAeropuertoInactivo;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import PaqueteUsuario.UsuarioAdmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPasajeComercial {

    public static void menuPasajeComercial(GestionUsuarios usuariosTotales, Aeropuerto aeropuerto, HistorialPasaje hPasajes, Usuario usuario, GestionAvion avionesTotales, GestionRutaAerea rutasTotales){

        int opcion;
        String eleccion;
        char continuar;


        Scanner scanner = new Scanner(System.in);

        RutaAerea ruta = null;
        String idRuta = "";
        String matriculaAvion = "";

        boolean controlador;


        do {

            System.out.println("---------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE PASAJES COMERCIAL");
            System.out.println("---------------------------------------");

            System.out.println("Para comprar un pasaje presione 1.");
            System.out.println("Para eliminar su pasaje presione 2.");
            System.out.println("Para ver su historial de vuelo en este aeropuerto presione 3.");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    try {
                        System.out.println("Usted ha elegido comprar un pasaje.");

                        aeropuerto.verificarAeropuertoActivo();

                        System.out.println("A continuacion se muestran las rutas aereas que parten desde este aeropuerto: ");

                        rutasTotales.listadoRutasPorAeropuerto(aeropuerto.getCodigoInternacional());
                        ///Se muestran las rutas que salen del aeropuerto

                        System.out.println("Ingrese el id de la que esta interesado: ");
                        idRuta = scanner.nextLine();
                        ///Se ingresa la ruta que se desea hacer

                        if (rutasTotales.containsKey(idRuta) && rutasTotales.buscar(idRuta).getAeropuertoSalida().equals(aeropuerto.getCodigoInternacional())) {
                            ///Si la ruta se encuentra en la clase gestora y esta sale desde el aeropuerto en que está el usuario...
                            controlador = true;
                            ///...el controlador se hace verdadero
                        } else {
                            controlador = false;
                            System.out.println("No se ha podido encontrar la ruta ingresada");
                        }


                        if (controlador) {

                            if (avionesTotales.containsRutaAsignada(idRuta)) {
                                controlador = true;
                            } else {
                                controlador = false;
                                System.out.println("No hay aviones disponibles con esta ruta");
                            }


                        }

                        if (controlador) {
                            ///si el controlador es verdadero, osea que la ruta existe y sale desde este aeropuerto
                            avionesTotales.listadoPorRutaAereaYDisponibilidad(idRuta);

                            System.out.println("Ingrese el avion con el que le interesaria realizar esta ruta: ");
                            matriculaAvion = scanner.nextLine();
                            ///Se ingresa la matricula

                            if (avionesTotales.containsMatricula(matriculaAvion) && avionesTotales.buscar(matriculaAvion).getIdRutaAsignada().equals(idRuta)) {
                                controlador = true;
                            } else {
                                controlador = false;
                            }
                        }

                        if (controlador) {

                            if (usuario instanceof UsuarioAdmin) {
                                System.out.println("Se le aplicara un descuento del 20 por ciento en su compra" +
                                        "por ser un administrador del aeropuerto.");
                            }


                            String idPasaje = hPasajes.generarIdParaPasaje();
                            ///GENERAR UN ID ALEATORIO PARA PASAJE Y CORROBORAR QUE NO EXISTA UNO IGUAL

                            LocalDate fechaPasaje = LocalDate.now();
                            ///Se genera la fecha. No se hace falta formatear porque por defecto es YYYY-MM-DD


                            Pasaje pasaje = new Pasaje(idPasaje, usuario.getIdUsuario(), aeropuerto.getCodigoInternacional(), fechaPasaje, matriculaAvion, true);
                            usuario.setIdPasaje(idPasaje);
                            usuariosTotales.modificar(usuario, usuario.getIdUsuario());
                            hPasajes.agregar(pasaje);


                        }

                    } catch (ExcepcionAeropuertoInactivo excepcionAeropuertoInactivo) {
                        System.out.println("Error. No se va a poder realizar una compra de pasaje porque: " + excepcionAeropuertoInactivo.getMessage());
                    }


                    break;

                case 2:

                    if (usuario.getIdPasaje() != null) {
                        System.out.println("Usted ha elegido dar de baja su pasaje.");
                        System.out.println("Esta seguro de que quiere realizar esta accion?");
                        System.out.println("Ingrese 'si' para continuar: ");
                        eleccion = scanner.nextLine().toLowerCase();

                        if (eleccion.equals("si")) {
                            if (hPasajes.eliminar(usuario.getIdPasaje())) {
                                System.out.println("Pasaje eliminado del historial con exito");
                            }

                            if (avionesTotales.liberarAsiento(usuario.getIdPasaje())) {
                                System.out.println("Asiento del avion liberado con exito");
                            }
                            usuario.setIdPasaje(null);
                            usuariosTotales.modificar(usuario, usuario.getIdUsuario());

                        }
                    }else {
                        System.out.println("Usted no ha comprado un pasaje");
                    }

                    break;

                case 3:
                    System.out.println("Usted ha elegido ver su historial de vuelo.");

                    ArrayList<Pasaje> pasajesDelUsuario = hPasajes.filtrarPorUsuario(usuario.getIdUsuario());

                    if (pasajesDelUsuario != null) {
                        for (Pasaje pasaje : pasajesDelUsuario) {

                            System.out.println(pasaje.informacionPasaje());
                        }

                    }else {
                        System.out.println("Usted no ha realizado compras de pasajes");
                    }


                    break;


                default:
                    System.out.println("La opcion ingresada no es valida.");


                    break;


            }

            System.out.println("Para continuar utilizando el menu de pasaje comercial presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');


    }


}
