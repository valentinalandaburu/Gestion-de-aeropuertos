package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteExcepciones.ExcepcionAeropuertoInactivo;
import PaqueteExcepciones.ExcepcionRutaConMismoDato;
import PaqueteExcepciones.ExcepcionRutaEnUso;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGestionRutas {
    public static void menuGestionRutas(Aeropuerto aeropuerto, GestionRutaAerea rutasDisponibles, GestionAeropuerto aeropuertos){

        int opcion;
        char continuar;
        Scanner scanner = new Scanner(System.in);

        String idRuta = "";
        String aeropuertoSalida = "";
        String aeropuertoLlegada = "";
        int kmRecorrido = 0;
        boolean rutaActivoOInactiva;

        RutaAerea rutaBuscada;

        boolean controlador = false;


        do {

            System.out.println("-------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE GESTION DE RUTA AEREA");
            System.out.println("-------------------------------------------");

            System.out.println("Para crear una nueva ruta aerea presione 1.");
            System.out.println("Para eliminar una ruta aerea presione 2.");
            System.out.println("Para modificar una ruta aerea presione 3.");
            System.out.println("Para buscar una ruta aerea presione 4.");
            System.out.println("Para listar las rutas aerea presione 5.");
            opcion = scanner.nextInt();
            scanner.nextLine();


            switch (opcion) {


                case 1:

                    System.out.println("Usted ha elegido crear una nueva ruta aerea.");

                    System.out.println("Ingrese el id que va a tener la ruta aerea: ");
                    idRuta = scanner.nextLine();
                    ///Se ingresa el id que va a tener la ruta

                    System.out.println("A continuacion se muestra la lista de aeropuertos disponibles" +
                            "para establecer como aeropuertos de llegada y salida");

                    System.out.println(aeropuertos.listadoV2());
                    ///Se listan todos los aeropuertos existentes

                    System.out.println("Ingrese el codigo IATA del aeropuerto de salida: ");
                    aeropuertoSalida = scanner.nextLine();
                    ///Se ingresa el codigo IATA del aeropuerto de salida

                    System.out.println("Ingrese el codigo IATA del aeropuerto de llegada: ");
                    aeropuertoLlegada = scanner.nextLine();
                    ///Se ingresa el codigo IATA aeropuerto de llegada

                    ///Se piden los datos de aeropuerto de salida, llegada y id porque, aparte de ser necesarios
                    ///para crear la ruta area, se necesitan para validar.


                    if (aeropuertos.buscarPorCodigo(aeropuertoSalida) != null && aeropuertos.buscarPorCodigo(aeropuertoLlegada) != null) {
                        if (!aeropuertoLlegada.equals(aeropuertoSalida)) {
                            try {

                                ///se verifica que existan los aeropuertos, que los aeropuertos de llegada y salida sean distintos

                                aeropuertos.verificacionDeAeropuertosActivosParaEstablecerRuta(aeropuertoSalida, aeropuertoLlegada);
                                ///se verifica que los aeropuertos indicados esten acitvos.

                                rutasDisponibles.validarRutaAerea(aeropuertoSalida, aeropuertoLlegada, idRuta);
                                ///ademas se verifica que la creacion no signifique repetir rutas o ids.


                                do {
                                    System.out.println("Ingrese la cantidad de km que va a tener el recorrido" +
                                            "(Ingrese un valor que sea mayor o igual 0): ");

                                    kmRecorrido = scanner.nextInt();
                                    scanner.nextLine();
                                    ///Si no se repite la ruta, entonces se pide que se ingrese de cuanto va a ser el recorrido en km.

                                    if (kmRecorrido > 0) {

                                        RutaAerea nvaRuta = new RutaAerea(idRuta, aeropuertoSalida, aeropuertoLlegada, kmRecorrido);
                                        rutasDisponibles.agregar(nvaRuta);
                                        ////Si toda la información es correcta, se crea la ruta aerea

                                    } else {

                                        System.out.println("No se puede establecer una ruta con un recorrido menor o igual a 0km");
                                    }

                                } while (kmRecorrido <= 0);
                                ///Se va a tener que ingresar un kilometraje hasta que el dato sea válido.

                            } catch (ExcepcionAeropuertoInactivo excepcionAeropuertoInactivo) {

                                System.out.println("Error. " + excepcionAeropuertoInactivo.getMessage());

                            } catch (ExcepcionRutaConMismoDato excepcionRutaConMismoDato) {

                                System.out.println("Error. " + excepcionRutaConMismoDato.getMessage());
                            }


                        } else {

                            System.out.println("El aeropuerto de llegada y salida no pueden ser los mismos");

                        }


                    } else {
                        System.out.println("Uno o ambos de los aeropuertos indicados no existe");
                    }


                    break;


                case 2:

                    System.out.println("Usted ha elegido eliminar una ruta aerea");

                    System.out.println("Ingrese el id de la ruta aerea que desea eliminar");
                    idRuta = scanner.nextLine();
                    rutaBuscada = rutasDisponibles.buscar(idRuta);
                    ///Se ingresa el id la ruta aerea que se quiere eliminar

                    if (rutaBuscada != null) {
                        ///Si encuentra la ruta

                        if (rutaBuscada.getIdAvionesConEstaRuta().isEmpty()) {

                            ///y si la ruta no tiene aviones asignados
                            rutasDisponibles.eliminar(idRuta);
                            System.out.println("Ruta eliminada.");
                            ///la elimina

                            ///no se tiene que hacer nada mas, porque el verificar que sea una ruta sin uso, no se
                            ///tendria que hacer más nada. Solamente eliminar la ruta.
                        } else {
                            System.out.println("No se puede eliminar la ruta porque esta asignada a uno o mas aviones.");
                        }
                    } else {
                        System.out.println("No se pudo encontrar la ruta");
                    }

                    break;


                case 3:

                    System.out.println("Usted ha elegido modificar una un ruta aerea");
                    System.out.println("Ingrese el id de la ruta aerea que desea modificar: ");
                    idRuta = scanner.nextLine();
                    ///Se ingresa el id de la ruta aerea que se quiere modificar.

                    RutaAerea rutaAModificar = rutasDisponibles.buscar(idRuta);
                    ///Se busca la ruta a modificar.

                    if (rutaAModificar != null) {
                        ///si se encuentra
                        if (rutaAModificar.getIdAvionesConEstaRuta().isEmpty()) {
                            ///y si no tiene aviones asignados, entonces se puede modificar

                            System.out.println("A continuacion se muestra la lista de aeropuertos disponibles" +
                                    "para establecer como aeropuertos de llegada y salida");

                            System.out.println(aeropuertos.listadoV2());
                            ///Se muestran los aeropuertos en el sistema

                            System.out.println("Ingrese el codigo IATA del aeropuerto de salida: ");
                            aeropuertoSalida = scanner.nextLine();
                            ///Se establece el aeropuerto de salida

                            System.out.println("Ingrese el codigo IATA del aeropuerto de llegada: ");
                            aeropuertoLlegada = scanner.nextLine();
                            ///Se establece el aeropuerto de llegada


                            if (aeropuertos.buscarPorCodigo(aeropuertoSalida) != null && aeropuertos.buscarPorCodigo(aeropuertoLlegada) != null) {
                                ///Si los aeropuertos de salida y llegada existen...

                                if (!aeropuertoLlegada.equals(aeropuertoSalida)) {
                                    ///y si los aeropuertos de salida y llegada no son lo mismo...

                                    try {

                                        aeropuertos.verificacionDeAeropuertosActivosParaEstablecerRuta(aeropuertoSalida, aeropuertoLlegada);
                                        ///Verifica que los aeropuertos esten activos.

                                        rutasDisponibles.validarRutaAerea(aeropuertoSalida, aeropuertoLlegada);
                                        ///verifica que no existe una ruta aerea con ese mismo trayecto

                                        rutaAModificar.setAeropuertoSalida(aeropuertoSalida);
                                        rutaAModificar.setAeropuertoLlegada(aeropuertoLlegada);
                                        ///Si no existe una ruta aerea con la modificación, se setean los valores.
                                        ///Si el usuario desea mantener los mismos valores, va a saltar la excepción y no se
                                        ///va a modificar los aeropuertos de llegada y salida

                                    } catch (ExcepcionAeropuertoInactivo excepcionAeropuertoInactivo) {

                                        System.out.println("Error. " + excepcionAeropuertoInactivo.getMessage());

                                    } catch (ExcepcionRutaConMismoDato excepcionRutaConMismoDato) {

                                        System.out.println("Error. " + excepcionRutaConMismoDato.getMessage());
                                        System.out.println("No se pudo modificar el recorrido.");

                                    }

                                    do {
                                        try {
                                            System.out.println("Ingrese de cuantos kilometros es el recorrido.");
                                            kmRecorrido = scanner.nextInt();

                                            if (kmRecorrido <= 0 || kmRecorrido > 19267) {

                                                System.out.println("El valor ingresado esta fuera de rango");
                                                controlador = false;
                                            }else {
                                                controlador = true;
                                            }
                                            ///Se le va a pedir al usuario que ingrese un kilometraje hasta que el valor esté
                                            ///dentro del rango.

                                        } catch (InputMismatchException inputMismatchException) {
                                            System.out.println("Error. Ingrese un valor valido");
                                            ///Si ingresa un valor erróneo, como un caracter o string, salta la excepción
                                            controlador = false;
                                            ///controlador se vuelve false;
                                        }
                                    } while (!controlador || kmRecorrido <= 0 || kmRecorrido > 19267);
                                    ///Y si controlador es false, o el dato está fuera de rango el usuario va a tener que
                                    //volver a ingresar un valor

                                    rutaAModificar.setKmRecorrido(kmRecorrido);
                                    ///se setean el valor de los kilometros del recorrido.

                                    do {
                                        try {
                                            System.out.println("Ingrese si la ruta va a estar activa o inactiva: ");
                                            rutaActivoOInactiva = scanner.nextBoolean();
                                            controlador = true;

                                            rutaAModificar.setRutaActiva(rutaActivoOInactiva);

                                        } catch (InputMismatchException inputMismatchException) {
                                            System.out.println("Error. Ingrese un valor booleano.");
                                            controlador = false;
                                        }

                                        scanner.nextLine();

                                    } while (!controlador);


                                    rutasDisponibles.modificar(rutaAModificar, rutaAModificar.getIdRutaAerea());
                                    ///se realiza la modificación

                                } else {

                                    System.out.println("El aeropuerto de llegada y salida no pueden ser los mismos");

                                }

                            } else {
                                System.out.println("No se encontro el aeropuerto de salida/llegada indicado.");
                            }

                        }else{
                            System.out.println("La ruta no puede ser modificada porque esta asignada a uno o mas aviones");
                        }

                    } else {

                        System.out.println("No se encontro la ruta indiciada");
                    }


                    break;


                case 4:

                    System.out.println("Usted ha elegido buscar una ruta aerea");
                    System.out.println("Si quiere buscar una ruta por su id, presione 1");
                    System.out.println("Si quiere buscar una ruta por su aeropuerto de salida y llegada, presione 2");
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {

                        System.out.println("Ingrese el id de la ruta: ");
                        idRuta = scanner.nextLine();
                        rutaBuscada = rutasDisponibles.buscar(idRuta);

                        if (rutaBuscada != null) {

                            System.out.println(rutaBuscada.listado());

                        } else {
                            System.out.println("No se encontro la ruta indicada");
                        }

                    } else if (opcion == 2) {

                        System.out.println("Ingrese el aeropuerto de salida");
                        aeropuertoSalida = scanner.nextLine();
                        System.out.println("Ingrese el aeropuerto de llegada");
                        aeropuertoLlegada = scanner.nextLine();
                        rutaBuscada = rutasDisponibles.buscar(aeropuertoSalida, aeropuertoLlegada);
                        if (rutaBuscada != null) {

                            System.out.println(rutaBuscada.listado());

                        } else {
                            System.out.println("No se encontro la ruta con los aeropuertos indicados");
                        }
                    } else {
                        System.out.println("Opcion no valida");
                    }


                    break;


                case 5:
                    System.out.println("Usted ha elegido listar las rutas aereas");

                    System.out.println("Si quiere ver un listado de todos las rutas existentes presione 1.");
                    System.out.println("Si quiere ver un listado de todos las rutas filtradas por aeropuerto" +
                            " presione 2.");

                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {

                        System.out.println(rutasDisponibles.listado());


                    } else if (opcion == 2) {

                        rutasDisponibles.listadoRutasPorAeropuerto(aeropuerto.getCodigoInternacional());
                    } else {

                        System.out.println("Opcion ingresada no valida");
                    }

                    break;


                default:
                    System.out.println("La opcion ingresada no es valida.");
                    break;


            }

            System.out.println("Para seguir utilizando el menu de gestion de rutas presione s: ");
            continuar = scanner.nextLine().charAt(0);


        }while (continuar == 's');


    }


}
