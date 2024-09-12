package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.ExcepcionAeropuertoInactivo;
import PaqueteExcepciones.ExcepcionCapacidadAeropuerto;
import PaqueteExcepciones.ExcepcionCapacidadHangar;
import PaqueteExcepciones.ExcepcionHangarMismosDatos;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHangarAdmin {
    public static void menuHangarDelAdmin(GestionAeropuerto aeropuertosTotales, GestionAvion aviones, GestionRutaAerea rutasDisponibles, HistorialPasaje pasajes, GestionUsuarios usuarios, Aeropuerto aeropuerto, GestionHangar hangares){

        int opcion;
        char continuar;

        String idHangar = "";
        String nombreHangar = "";
        int capacidadAvionesdelHangar = 0;
        boolean habilitado = true;

        Hangar hangarABuscaroEliminar;


        ///VARIABLE HECHA PARA HACER VALIDACION DE LA VARIALBE BOOLEANA "habilitado"




        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("---------------------------------------------");
            System.out.println("BIENVENIDO MENU DE HANGARES DEL ADMINISTRADOR");
            System.out.println("---------------------------------------------");

            System.out.println("Si quiere agregar un hangar presione 1");
            System.out.println("Si quiere eliminar un hangar presione 2");
            System.out.println("Si quiere modificar un hangar presione 3");
            System.out.println("Si quiere buscar un hangar presione 4");
            System.out.println("Si quiere listar un hangar presione 5");
            System.out.println("Si quiere acceder al menu de avion presione 6");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:

                    try {

                        aeropuerto.verificarAeropuertoActivo();

                        do {
                            try {
                                System.out.println("Usted ha elegido agregar un nuevo hangar");
                                System.out.println("Ingrese el id del hangar: ");
                                idHangar = scanner.nextLine();
                                ///Se ingresa el id que va a tener el nuevo hangar

                                System.out.println("Ingrese el nombre del hangar: ");
                                nombreHangar = scanner.nextLine();
                                ///Se ingresa el nombre que va a tener el nuevo hangar

                                hangares.verificarSiHayHangarConMismosDatos(idHangar, nombreHangar);
                                ///verifica los datos

                                flag = true;

                            } catch (ExcepcionHangarMismosDatos excepcionHangarMismosDatos) {
                                flag = false;
                                System.out.println("Ha ocurrido un error porque " + excepcionHangarMismosDatos.getMessage());
                            }
                        } while (flag == false);

                        do {

                            try {

                                System.out.println("Ingrese si el hangar va a estar o no habilitado: ");
                                habilitado = scanner.nextBoolean();
                                ///Se ingresa si va a estar habilitado con true o con false.

                                flag = true;
                                ///En caso de que no se produzca una excepción por ingresar mal el valor, flag se hace
                                ///true y se sale del bucle

                            } catch (InputMismatchException inputMismatchException) {

                                System.out.println("Error. Ingrese un valor booleano");

                                flag = false;
                            }

                        } while (flag == false);

                        ///Esta validaciòn de capacidad de hangar no va a tener un do while porque se puede llegar
                        ///a generar un bucle infinito. Si ya no hay capacidad en el aeropuerto para generar otro hangar,
                        ///el usuario tendria que poner como capacidad 0 en el nvo hangar. La validacion impide
                        ///que se pueda generar un hangar con capacidad 0. Es por esto que flag nunca se podria hacer
                        ///true y no se podria salir del bucle.

                        scanner.nextLine();
                        try {

                            do {
                                System.out.println("Ingrese la capacidad del hangar: ");
                                capacidadAvionesdelHangar = scanner.nextInt();
                                ///Se ingresa la capacidad que va a tener el hangar

                                if (capacidadAvionesdelHangar <= 0) {
                                    System.out.println("No puede haber un hangar con una capacidad menor o igual a o");
                                }

                                ///Se va a volver a pedir que el usuario ingrese una capacidad hasta que ingrese un dato que sea
                                ///valido

                            } while (capacidadAvionesdelHangar <= 0);

                            Hangar hangar = new Hangar(idHangar, nombreHangar, capacidadAvionesdelHangar, habilitado, aeropuerto.getCodigoInternacional());
                            ///Se crea el hangar

                            aeropuerto.validacionDeCapacidad(hangar, hangares);
                            ///Finalmente se valida la capacidad del hangar y se guardan los datos.

                        } catch (ExcepcionCapacidadAeropuerto excepcionCapacidadAeropuerto) {
                            System.out.println("Error porque " + excepcionCapacidadAeropuerto.getMessage());
                        }
                    } catch (ExcepcionAeropuertoInactivo excepcionAeropuertoInactivo) {

                        System.out.println("Error. " + excepcionAeropuertoInactivo);

                    }

                    break;


                case 2:

                    System.out.println("Usted ha elegido eliminar un hangar");
                    System.out.println("Tenga en cuenta que no se puede eliminar un hangar si hay por lo menos" +
                            "un avion dentro del hangar.");

                    System.out.println("Ingrese el id del hangar a eliminar ");
                    idHangar = scanner.nextLine();

                    hangarABuscaroEliminar = hangares.buscar(idHangar);
                    ///Se ingresa el id del hangar que se quiere eliminar.


                    if (hangarABuscaroEliminar != null) {
                        ///Si se encuentra el hangar, ...

                        if (hangarABuscaroEliminar.getIdAvionesEnHangar().isEmpty() & hangarABuscaroEliminar.getIdAeropuerto().equals(aeropuerto.getCodigoInternacional())) {
                            ///...el hangar no tiene registro de que tenga aviones asignados y ademas el hangar encontrado
                            // pertenece al aeropuerto pasado por parametro (compara con ids)...

                            hangares.eliminar(idHangar);
                            ///...se elimina el hangar de la clase gestoria...
                            aeropuerto.removerIdHangarPertenencia(idHangar);
                            ///...se quita del aeropuerto el registro de existencia del hangar eliminado...
                            aeropuertosTotales.modificar(aeropuerto, aeropuerto.getCodigoInternacional());
                            ///...y finalmente se guarda la modificación
                        }


                    } else {
                        System.out.println("No existe hangar con ese id.");
                    }

                    break;


                case 3:


                    System.out.println("Usted ha elegido modificar un hangar");

                    System.out.println("Ingrese el id del hangar a modificar ");
                    idHangar = scanner.nextLine();
                    ///Se ingresa el id del hangar a modificar

                    Hangar hangarAModificar = hangares.buscar(idHangar);
                    ///Se busca el hangar

                    if (hangarAModificar != null) {
                        ///Si se encuentra al hangar...

                        do {

                            System.out.println("Ingrese el nuevo nombre del hangar");
                            nombreHangar = scanner.nextLine();
                            scanner.nextLine();
                            ///...se pide de ingresar el que va a ser su nuevo nombre.

                            try {

                                hangares.verificarSiHayHangarConMismosDatos(nombreHangar);
                                flag = true;
                                ///Se verifica que no haya hangares con el mismo nombre

                            } catch (ExcepcionHangarMismosDatos excepcionHangarMismosDatos) {

                                System.out.println("Error. " + excepcionHangarMismosDatos.getMessage());
                                flag = false;
                                ///Se va a tener que ingresar un nombre hasta que se establezca uno que no existe.
                            }

                        } while (flag == false);

                        do {

                            try {

                                System.out.println("Ingrese si el hangar va a estar o no habilitado: ");
                                habilitado = scanner.nextBoolean();
                                flag = true;

                                ///Se pide que se ingrese si va ahora va a estar habilitado o deshabilitado.

                            } catch (InputMismatchException e) {
                                flag = false;
                                ///En caso de que el dato ingresado no sea válido, se le vuelve a pedir al usuario que ingrese
                                ///el dato correspondiente


                            }

                        } while (flag == false);


                        ///En el try que sigue se hace un do while porque se supone que si se logrò crear un hangar, hay
                        ///cierto margen para poder modificar su capacidad. En el peor de los casos, el cual seria que el
                        ///hangar tenga capacidad para guardar un solo avion, lo unico que podria llegar a pasar es que el
                        ///usuario se vea obligado a dejar la capacidad original.
                        do {
                            try {

                                System.out.println("Ingrese la nueva capacidad del hangar");
                                capacidadAvionesdelHangar = scanner.nextInt();
                                ///Se ingresa la nueva capacidad

                                hangarAModificar.setNombreHangar(nombreHangar);
                                hangarAModificar.setHabilitado(habilitado);
                                ///Se setea el nombre y si el hangar está habilitado

                                hangarAModificar.validarSiCapacidadHangarEsMenorAAvionesAlmacenados(capacidadAvionesdelHangar);
                                ///Antes de hacer el setear el valor de la nueva capacidad se fija que la nueva capacidad no
                                ///sea menor a la cantidad de aviones almacenados


                                hangarAModificar.setCapacidadAviones(capacidadAvionesdelHangar);
                                ///Se setean todos los datos


                                aeropuerto.validacionDeCapacidad(hangarAModificar, hangares);
                                ///El aeropuerto valida la capacidad, y en caso de respetar la capacidad máxima establecida,
                                ///se guarda la modificacion.

                                flag = true;

                            } catch (ExcepcionCapacidadHangar excepcionCapacidadHangar) {

                                ///Si la nueva capacidad es menor a la cantidad de aviones almacenados, se lanza una excepcion
                                ///y se va a mostrar el mensaje de abajo
                                System.out.println("Error. " + excepcionCapacidadHangar.getMessage());
                                flag = false;

                            } catch (ExcepcionCapacidadAeropuerto excepcionCapacidadAeropuerto) {

                                ///Si la capacidad establecida para el hangar no respeta el límite máximo establecido por el
                                ///aeropuerto, se lanza una excepción y se va a mostrar el mensaje de abajo

                                System.out.println("Error. " + excepcionCapacidadAeropuerto.getMessage());

                            }
                        } while (flag == false);

                    } else {

                        System.out.println("No existe un hangar con el id ingresado");

                    }

                    break;

                case 4:

                    System.out.println("Usted ha elegido buscar un hangar");
                    System.out.println("Ingrese el id del hangar a eliminar ");
                    idHangar = scanner.nextLine();

                    Hangar hangarBuscado = hangares.buscar(idHangar);

                    if (hangarBuscado != null) {
                        if (hangarBuscado.getIdAeropuerto().equals(aeropuerto.getCodigoInternacional())) {
                            System.out.println(hangarBuscado.obtenerInformacion());
                        } else {
                            System.out.println("El hangar no pertenece a este aeropuerto");
                        }
                    } else {
                        System.out.println("El hangar no existe");
                    }

                    break;
                case 5:

                    System.out.println("Usted ha elegido listar los hangares");
                    System.out.println(hangares.listado(aeropuerto.getCodigoInternacional()));

                    break;

                case 6:

                    System.out.println("Usted ha elegido ingresar al menu de aviones");
                    System.out.println("Ingrese el id del hangar al cual quiere acceder a sus aviones almacenados");
                    idHangar = scanner.nextLine();

                    Hangar hBuscado = hangares.buscar(idHangar);

                    if (hBuscado != null) {
                        if (hBuscado.getIdAeropuerto().equals(aeropuerto.getCodigoInternacional())) {

                            MenuAvionAdm.menuAvionAdministrador(hangares, rutasDisponibles, usuarios, pasajes, aviones, aeropuerto, hBuscado);
                        } else {
                            System.out.println("Error. El hangar indicado no se encontro dentro del aeropuerto");
                        }
                    } else {

                        System.out.println("No existe ese hangar");
                    }


                    break;
                default:

                    System.out.println("Opcion no valida");

                    break;


            }

            System.out.println("Para continuar con el menu hangar del administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');

    }
}