package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.Avion;
import PaqueteAvion.AvionComercial;
import PaqueteAvion.GestionAvion;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import PaqueteUsuario.GestionUsuarios;

import java.util.Scanner;

public class TorreDeControl {

    public static void torreDeControl(HistorialPasaje hPasajes, GestionUsuarios usuariosTotales, Aeropuerto aeropuerto, GestionRutaAerea rutasAereasTotales, GestionHangar hangaresTotales, GestionAvion avionesTotales, GestionAeropuerto aeropuertosRegistrados) {

        char continuar;
        Scanner scanner = new Scanner(System.in);

        String matriculaAvion;
        String idRutaAerea;
        RutaAerea rutaDelAvion = null;
        Avion avionBuscado;
        Aeropuerto aeropuertoDestino = null;
        Hangar hangarDestino = null;
        Hangar hangarOrigen = null;


        System.out.println("Usted ha entrado a la torre de control. Desde acá va a poder dar la orden de " +
                "volar a un avion.");


        boolean controlador = false;

        do {

            System.out.println("--------------------------------");
            System.out.println("BIENVENIDO A LA TORRE DE CONTROL");
            System.out.println("--------------------------------");

            System.out.println("Ingrese la matricula del avion al que le quiere dar la orden de vuelo: ");
            matriculaAvion = scanner.nextLine();
            ///Se ingresa la matricula del avion.

            avionBuscado = avionesTotales.buscar(matriculaAvion);
            ///Se busca al avion

            if (avionBuscado != null && aeropuerto.getIdHangaresDeAeropuerto().contains(avionBuscado.getIdHangar())) {
                ///Si se encontró un avión y este pertenece a uno de los hangares del aeropuerto...

                if (avionBuscado.isAvionActivo()) {
                    ///...se fija si esta activo.
                    if (avionBuscado.getIdRutaAsignada() != null) {
                        ///Se fija si tiene ruta asignada:
                        controlador = true;
                    } else {
                        System.out.println("El avion no posee ruta asignada.");
                    }
                    ///Si lo anterior se cumple, controlador se hace verdadero.

                    ///No se fija si la ruta asignada sale desde este aeropuerto, porque si el avión se encuentra en este
                    ///aeropuerto y tiene una ruta asignada, no existe la posibilidad de que el aeropuerto de salida
                    ///sea otro distinto que en el que se encuentra.

                } else {

                    System.out.println("El avion no se encuentra activo");
                }
            } else {
                System.out.println("Avion no encontrado");
            }


            if (controlador) {
                rutaDelAvion = rutasAereasTotales.buscar(avionBuscado.getIdRutaAsignada());
                ///no se verifica la existencia de la ruta aerea porque no se puede borrar una ruta si por lo
                ///menos hay un avion que la tenga asignada.

                aeropuertoDestino = aeropuertosRegistrados.buscar(rutaDelAvion.getAeropuertoLlegada());
                ///Se busca el aeropuerto destino. Si está como aeropuerto de salida o llegada no puede no existir
                hangarOrigen = hangaresTotales.buscar(avionBuscado.getIdHangar());
                ///Se busca el hangar de origen del avión. No puede no exisitir si por lo menos contiene un avión

                if (aeropuertoDestino.isAeropuertoActivo() && rutaDelAvion.isRutaActiva()) {
                    ///Se fija si la ruta y el aeropuerto estan activos
                    controlador = true;

                } else {
                    System.out.println("No se puede hacer el viaje porque el aeropuerto destino está inactivo o" +
                            "la ruta no se encuentra activa.");
                    controlador = false;
                }

            }

            if (controlador) {
                ///Si controlador sigue siendo verdadero

                if (aeropuertoDestino.getIdHangaresDeAeropuerto() != null) {
                    ///Se verifica si getIdHangaresDeAeropuerto() tiene datos para evitar que salte un
                    ///NullPointerException

                    for (String idHangarPosibleDestino : aeropuertoDestino.getIdHangaresDeAeropuerto()) {
                        ///entonces por cada hangar que tenga el aeropuerto
                        hangarDestino = hangaresTotales.buscar(idHangarPosibleDestino);
                        ///se va a buscar al hangar del aeropuerto

                        if (hangarDestino.isHabilitado() && hangarDestino.validarSiCapacidadHangarEsMenorAAvionesAlmacenados()) {
                            ///y si este está habilitado y tiene capaciadad para almacenar, controlador sigue siendo true
                            ///No se verifica si hangar destino es null o no, porque si existe en el registro del aeropuerto
                            ///su id, entonces no hay posibilidad de que el objeto no existe. Cuando se crea o elimina un
                            ///hangar, automáticamente se da aviso al aeropuerto.


                            controlador = true;
                            avionBuscado.setKmRecorridos(rutaDelAvion.getKmRecorrido());
                            ///Se actualiza los kilómetros recorridos

                            avionBuscado.setIdRutaAsignada(null);
                            ///Ya que el avion se dirige a destino, queda sin efecto su ruta aerea asignada.
                            if (avionBuscado instanceof AvionComercial) {
                                ((AvionComercial) avionBuscado).setIdPasajesDelAvion(null);
                                ///Ya que se realiza el vuelo se dan de baja los asientos ocupados si es un avion vomercial
                                usuariosTotales.darDeBajaPasajesUsuariosPorAvion(aeropuerto.getCodigoInternacional());
                                ///se dan de baja los pasajes de los usuarios de este vuelo. Ahora van a poder comprar otro pasaje
                                hPasajes.expirarPasaje(avionBuscado.getMatricula());
                                ///se tiene que avisar al historial que expiraron los pasajes

                            }

                            rutaDelAvion.eliminarIdAvionDeHashSet(avionBuscado.getMatricula());
                            ///se elimina de la ruta aerea el registro del avion que la hizo

                            hangarOrigen.eliminarAvionDeHangar(avionBuscado.getMatricula());
                            ///se elimina del hangar de origen la existencia del avión ya que está en el destino

                            avionBuscado.vuelo(rutaDelAvion.getKmRecorrido());
                            ///Se está volando!

                            avionBuscado.setIdHangar(hangarDestino.getIdHangar());
                            ///Se le indica al avión su nuevo hangar

                            hangarDestino.agregarAvionAHangar(avionBuscado.getMatricula());
                            ///Se le avisa al hangar que recibió un avion.

                            System.out.println("El vuelo se ha realizado");
                            break;


                        }

                        controlador = false;

                    }
                } else {
                    controlador = false;
                }


            }

            if (controlador) {
                System.out.println("El avion ha llegado a destino");
            } else {
                System.out.println("No se dio la orden de vuelo");
            }

            System.out.println("Para continuar en la torre de control presione s: ");
            continuar = scanner.nextLine().charAt(0);


        }while (continuar == 's');

    }

}
