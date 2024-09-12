package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteExcepciones.ExcepcionCapacidadAeropuerto;
import PaqueteExcepciones.ExcepcionCapacidadHangar;
import PaqueteExcepciones.ExcepcionDatosAeropuerto;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;
import PaquetePasaje.HistorialPasaje;
import PaquetePasaje.Pasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGestionAeropuertos {
    public static void menuGestionAeropuertos(GestionRutaAerea rutasTotales, GestionUsuarios usuariosTotales, HistorialPasaje hPasajes, GestionAeropuerto aeropuertosRegistrados, GestionHangar hangaresTotales){



        Scanner scanner = new Scanner(System.in);
        int opcion;
        char continuar;

        String nombreAeropuerto = "";
        String codigoIATA = "";
        String direccion = "";
        int capacidadTotalAviones;
        boolean aeropuertoActivo = false;  ///es lo que va a indicar si se puede comprar pasajes o no

        boolean controlador = false;

        String nombreOcodigo;

        do {

            System.out.println("-------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE GESTION DE AEROPUERTO");
            System.out.println("--------------------------------------------");

            System.out.println("Si quiere agregar un aerupuerto presione 1.");
            System.out.println("Si quiere eliminar un aerupuerto presione 2.");
            System.out.println("Si quiere modificar un aerupuerto presione 3.");
            System.out.println("Si quiere buscar un aerupuerto presione 4.");
            System.out.println("Si quiere listar los aerupuertos presione 5.");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {


                case 1:

                    System.out.println("Usted ha elegido crear un nuevo aeropuerto.");

                    try {

                        System.out.println("Ingrese el nombre que va a tener el aeropuerto: ");
                        nombreAeropuerto = scanner.nextLine();

                        System.out.println("Ingrese la direccion que va a tener el aeropuerto: ");
                        direccion = scanner.nextLine();

                        do {

                            System.out.println("Ingrese el codigo IATA que va a tener el aeropuerto: ");
                            codigoIATA = scanner.nextLine();

                            if (aeropuertosRegistrados.esCodigo(codigoIATA)) {
                                controlador = true;
                            }

                        } while (!controlador);


                        aeropuertosRegistrados.validarDatosAeropuerto(nombreAeropuerto, direccion, codigoIATA);

                        do {

                            System.out.println("Ingrese la capacidad total de aviones que puede tener el aeropuerto: ");
                            capacidadTotalAviones = scanner.nextInt();
                            controlador = true;


                            if (capacidadTotalAviones <= 0) {
                                System.out.println("La capacidad del aeropuerto no puede ser 0 ni ser un numero negativo");
                                controlador = false;
                            } else {
                                controlador = true;

                            }

                        } while (!controlador);

                        do {
                            try {

                                System.out.println("Ingrese si el aeropuerto va a estar activo: ");
                                aeropuertoActivo = scanner.nextBoolean();
                                controlador = true;

                            } catch (InputMismatchException e) {

                                System.out.println("El valor ingresado no es booleano.");
                                controlador = false;
                            }

                            scanner.nextLine();

                        } while (!controlador);

                        Aeropuerto nvoAerpuerto = new Aeropuerto(nombreAeropuerto, direccion, capacidadTotalAviones, codigoIATA, aeropuertoActivo);
                        aeropuertosRegistrados.agregar(nvoAerpuerto);


                    } catch (ExcepcionDatosAeropuerto excepcionDatosAeropuerto) {

                        System.out.println(excepcionDatosAeropuerto.getMessage());
                    }


                    break;


                case 2:

                    boolean flag = false;

                    System.out.println("Usted ha elegido eliminar un aeropuerto.");
                    System.out.println("Ingrese el codigo IATA del aeropuerto a eliminar: ");
                    codigoIATA = scanner.nextLine();  ///Se indica el codigo del aeropuerto

                    Aeropuerto aeropuertoABuscar = aeropuertosRegistrados.buscar(codigoIATA); ///Busca el aeropuerto


                    if (aeropuertoABuscar != null) {   ///Si existe el aeropuerto...

                        if (!aeropuertoABuscar.getIdHangaresDeAeropuerto().isEmpty()) {
                            ///Se fija si tiene hangares que le pertenezcan
                            ///Si el hashset de hangares que tiene el aeropuerto no está vacio...

                            flag = true; ///...se activa el flag para evitar que el aeropuerto sea borrado.

                        }

                        if (!aeropuertoABuscar.getUsuariosDeAeropuerto().isEmpty()) {
                            ///se fija que no existan usuarios en este aeropuerto.
                            ///Si la el hashset de usuarios del aeropuerto no está vacio...
                            flag = true; ///...se activa el flag para evitar que el aeropuerto sea borrado.

                        }

                        if (rutasTotales.existeDeAeropuertoEnRutas(aeropuertoABuscar.getCodigoInternacional())) {

                            ///Si el aeropuerto aparece en una ruta aerea creada ya sea como aeropuerto de salida o llegada...

                            System.out.println("Entra a existeDeAeropuertoEnRutas()");
                            flag = true; /// ...se activa el flag para evitar que el aeropuerto sea borrado.

                        }

                        ///No se revisan si hay pasajes no expirados porque si no hay usuarios en el aeropuerto,
                        ///entonces no puede haber pasajes comprados. Tampoco puede haber pasajes sin expirar con
                        ///destino a este aeropuerto si el aeropuerto no aparece en ninguna ruta aerea.

                    } else {

                        System.out.println("No se encontro el aeropuerto.");
                    }

                    if (flag) {

                        System.out.println("No se puede eliminar el aeropuerto porque tiene hangares, usuarios registrados, o rutas como salida/llegada");


                    } else {

                        ///Si no se cumple ninguna de las condiciones anteriores...
                        if (aeropuertoABuscar != null) {
                            if (aeropuertosRegistrados.eliminar(aeropuertoABuscar.getCodigoInternacional())) {
                                System.out.println("Aeropuerto eliminado");
                            } else {
                                System.out.println("No se pudo eliminar el aeropuerto");
                            }
                        }
                        ///se elimina el aeropuerto

                    }


                    break;


                case 3:
                    System.out.println("Usted ha elegido modificar un aeropuerto.");
                    System.out.println("Para modificar un aeropuerto debe utilizar el codigo IATA.");
                    System.out.println("Recuerde que formato es 'AAA111'. Tres letras mayusculas y tres numeros");
                    System.out.println("Ingrese el codigo internacional del aeropuerto que quiere modificar: ");
                    codigoIATA = scanner.nextLine();

                    if (aeropuertosRegistrados.esCodigo(codigoIATA)) {
                        ///Si el codigo ingresado es una codigo en formato IATA...

                        aeropuertoABuscar = aeropuertosRegistrados.buscarPorCodigo(codigoIATA);
                        ///...busca el aeropuerto.
                        if (aeropuertoABuscar != null) {

                            ///Si se encuentra el aeropuerto...

                            try {


                                System.out.println("Ingrese el nuevo nombre del aeropuerto: ");
                                nombreAeropuerto = scanner.nextLine();
                                ///Se pide el nuevo nombre

                                System.out.println("Ingrese la nueva direccion del aeropuerto");
                                direccion = scanner.nextLine();
                                ///Se pide la nueva direccion

                                aeropuertosRegistrados.validarDatosAeropuerto(nombreAeropuerto, direccion);
                                ///Se validan la direccion y nombre del aeropuerto


                                System.out.println("Ingrese la nueva capacidad del aeropuerto: ");
                                capacidadTotalAviones = scanner.nextInt();
                                ///Se ingresa la nueva capacidad.

                                aeropuertoABuscar.validacionDeCapacidad(capacidadTotalAviones);
                                ///Se valida la capacidad utilizando a la clase Avion porque es ella la que tiene la informacion
                                ///de la capacidad total de los hangares de un aeropuerto.

                                do {
                                    try {


                                        System.out.println("Ingrese si el aeropuerto va a estar activo (en caso de que este inactivo," +
                                                "no se podran comprar pasajes en el). True para activo, false para inactivo: ");
                                        aeropuertoActivo = scanner.nextBoolean();
                                        ///Se ingresa si el aeropuerto va a estar activo o inactivo.

                                        controlador = true;

                                    } catch (InputMismatchException inputMismatchException) {

                                        System.out.println("No ingreso un valor booleano");
                                        controlador = false;

                                    }
                                } while (!controlador);

                                aeropuertoABuscar.setAeropuertoActivo(aeropuertoActivo);
                                aeropuertoABuscar.setNombreAeropuerto(nombreAeropuerto);
                                aeropuertoABuscar.setDireccion(direccion);
                                aeropuertoABuscar.setCapacidadTotalAviones(capacidadTotalAviones);
                                ///Se setean los valores

                                aeropuertosRegistrados.modificar(aeropuertoABuscar, codigoIATA);
                                ///Se guarda la modificacion del aeropuerto

                                ///Al no modificarse el codigo internacional, no hay que hacer modificaciones en los
                                ///usuarios de este aeropuerto ni en los hangares


                            } catch (ExcepcionDatosAeropuerto excepcionDatosAeropuerto) {
                                System.out.println("Error. " + excepcionDatosAeropuerto.getMessage());

                            } catch (ExcepcionCapacidadAeropuerto excepcionCapacidadAeropuerto) {
                                System.out.println("Error. " + excepcionCapacidadAeropuerto.getMessage());
                            }


                        }

                    } else {
                        System.out.println("No se ingreso un codigo IATA. in");
                    }

                    break;


                case 4:

                    System.out.println("Usted ha elegido buscar un aeropuerto.");
                    System.out.println("Ingrese el nombre del aeropuerto o su codigo IATA");
                    nombreOcodigo = scanner.nextLine();

                    Aeropuerto aBuscado = aeropuertosRegistrados.buscar(nombreOcodigo);

                    if (aBuscado != null) {
                        System.out.println("Aeropuerto encontrado");
                        System.out.println(aBuscado.obtenerInformacion());
                    } else {
                        System.out.println("El aeropuerto no se encontro");
                    }

                    break;


                case 5:
                    System.out.println("Usted ha elegido listar los aeropuertos.");
                    System.out.println(aeropuertosRegistrados.listado());

                    break;


                default:
                    System.out.println("La opcion no ingresada no es valida.");
                    break;

            }

            System.out.println("Si quiere seguir utilizando el menu de gestion de aeropuertos presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');


    }


}
