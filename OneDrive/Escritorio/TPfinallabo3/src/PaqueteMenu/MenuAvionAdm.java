package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAvion.Avion;
import PaqueteAvion.AvionComercial;
import PaqueteAvion.AvionDeCarga;
import PaqueteAvion.GestionAvion;
import PaqueteExcepciones.*;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteJsonUtiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAvionAdm {

    public static void menuAvionAdministrador(GestionHangar hangaresTotales,GestionRutaAerea rutasAereas, GestionUsuarios usuarios, HistorialPasaje hPasajes, GestionAvion aviones, Aeropuerto aeropuerto, Hangar hangarSeleccionado){

        int opcion;
        Scanner scanner = new Scanner(System.in);

        String idParaRutaAerea;

        String matriculaABuscar;


        char letra;
        char continuar;

        ///variables necesarias para la generacion de todos los tipos de aviones
        String matricula = " ";
        String modelo = " ";

        RutaAerea rutaParaAsignaroEliminar;
        int kmRecorridos;
        ///variables necesarias para la generacion de todos los tipos de aviones

        ///variables necesarias para la generacion de aviones comerciales
        String aerolinea;
        int capacidad;
        boolean avionActivo = false;
        String nombreCateogria;
        boolean asientoCuero = false;
        boolean jacuzzi = false;
        boolean wifi = false;
        ///variables necesarias para la generacion de aviones comerciales

        ///variables necesarias para la generacion de carga comerciales
        float cargaMaxima;
        boolean cargaPeligrosa = false;
        boolean gruaInterna = false;
        ArrayList<String>objetosPermitidos = new ArrayList<>();
        String objetoPermitido;
        ///variables necesarias para la generacion de carga comerciales

        ///variable necesaria para generar los do - while
        boolean controlador = false;
        ///variable necesaria para generar los do - while

        Avion avionABuscar;

        do {

            System.out.println("-----------------------------------------------");
            System.out.println("BIENVENIDO AL MENU DE AVIONES DEL ADMINISTRADOR");
            System.out.println("-----------------------------------------------");

            System.out.println("Para agregar un avion presione 1.");
            System.out.println("Para eliminar un avion presione 2.");
            System.out.println("Para modificar un avion presione 3.");
            System.out.println("Para buscar un avion presione 4.");
            System.out.println("Para listar los aviones presione 5.");
            System.out.println("Para asignar un vuelo a un avion presione 7.");
            System.out.println("Para eliminar el vuelo a un avion presione 8.");
            opcion = scanner.nextInt();
            scanner.nextInt();


            switch (opcion) {


                case 1:
                    ///SI SE CREA UN AVION NUEVO, ESTE DEBE SER 'INGRESADO' EN UN HANGAR. ESTO SE HACE AGREGANDO EL ID DEL
                    ///AVION AL ATRIBUTO HASHSET DE IDs DE AVIONES QUE TIENE HANGAR. POSTERIORMENTE ESTO SE DEBE GUARDAR EN
                    ///LA CLASE GESTION.

                    try {
                        aeropuerto.verificarAeropuertoActivo();
                        ///Se verifica que el aeropuerto esté activo

                        hangarSeleccionado.verificarHangarActivo();
                        ///Se verifica si el hangar esta activo.
                        hangarSeleccionado.validarCantidadAviones(); ///Al tener hangar una coleccion los id de los aviones registrados para este hangar, se fija en el tamaño de esa coleccion
                        System.out.println("Usted ha elegido agregar un avion.");

                        ///Se hace un arraylist con un archivo JSON el cual valida las matriculas y tira una
                        ///exception que cancela todoo;

                        System.out.println("Ingrese la matricula del avion: ");
                        matricula = scanner.nextLine();
                        ///Se ingresa una matricula...

                        validarRegistroMatricula(matricula);
                        ///..y se verifica para evitar que haya matrículas repetidas en caso de que exista o haya existido
                        ///un avion con la matrícula


                        System.out.println("Ingrese el modelo del avion");
                        modelo = scanner.nextLine();
                        ///Se ingresa el modelo


                        do {
                            System.out.println("Ingrese la cantidad el kilometraje del avion. No puede ser mayor a 100000");
                            kmRecorridos = scanner.nextInt();
                        } while (kmRecorridos < 0 || kmRecorridos > 100000);
                        ///El programa no va a dejar de pedirle al usuario que ingrese un kilometraje válido hasta que el
                        ///usuario ingrese un dato valido.


                        System.out.println("Si quiere generar un avion comercial presione 1.");
                        System.out.println("Si quiere generar un avion de carga presione 2.");
                        opcion = scanner.nextInt();
                        ///El usuario elige si quiere crear un avion comercial o uno de carga

                        if (opcion == 1) {

                            ///Si quiere crear un un avion comercial,...

                            do {
                                System.out.println("Ingrese la cantidad de asientos que tiene el avion");
                                capacidad = scanner.nextInt();
                                scanner.nextInt();
                            } while (capacidad <= 0 || capacidad > 900);

                            System.out.println("Ingrese la aerolinea del avion.");
                            aerolinea = scanner.nextLine();
                            ///...se le pide que ingrese la cantidad de asientos que va a tener y de cuál aerolínea es.
                            ///Con respecto a la capacidad, para evitar que se ponga un numero inmenso, se establece un
                            ///límite máximo.

                            System.out.println("Datos sobre la categoria del avion: ");

                            System.out.println("Ingrese el nombre que va a tener la categoria de este avion: ");
                            nombreCateogria = scanner.nextLine();
                            scanner.nextLine();

                            do {
                                try {

                                    System.out.println("Ingrese true si va a tener asiento de cuero, false si no los va a tener: ");
                                    asientoCuero = scanner.nextBoolean();

                                    controlador = true;
                                } catch (InputMismatchException e1) {
                                    System.out.println("Error, ingrese un valor booleano");
                                    controlador = false;
                                }
                            } while (!controlador);

                            do {
                                try {

                                    System.out.println("Ingrese true si va a tener jacuzzi, false si no lo va a tener");
                                    jacuzzi = scanner.nextBoolean();
                                    controlador = true;

                                } catch (InputMismatchException e2) {

                                    System.out.println("Error, ingrese un valor booleano");
                                    controlador = false;

                                }
                            } while (!controlador);

                            do {
                                try {

                                    System.out.println("Ingrese true si va a tener wifi, false si no lo va a tener");
                                    wifi = scanner.nextBoolean();
                                    controlador = true;

                                } catch (InputMismatchException ee) {

                                    System.out.println("Error, ingrese un valor booleano");
                                    controlador = false;

                                }
                            } while (!controlador);

                            ///Con respecto a la categoría, se le pide al usuario que ingrese los datos correspondientes.

                            AvionComercial aCom = new AvionComercial(matricula, modelo, kmRecorridos, hangarSeleccionado.getIdHangar(), capacidad, aerolinea, nombreCateogria, asientoCuero, jacuzzi, wifi);
                            ///Se crea el avion comercial.

                            aviones.agregar(aCom);
                            ///Agrega el avion a gestion aviones

                            hangarSeleccionado.agregarAvionAHangar(aCom.getMatricula());
                            ///Avisa al hangar que tiene un nuevo avion. La verificación de capacidad para asegurarse que la
                            ///creación de este nuevo avión no sobrepase el límite del hangar ya se hizo arriba.

                            hangaresTotales.modificar(hangarSeleccionado, hangarSeleccionado.getIdHangar());
                            ///Finalmente se guarda el cambio hecho al hangar.


                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2
                            ///FALTA GUARDAR EL HANGAR MODIFICADO EN SU CLASE GESTION AL IGUAL QUE EN EL CASE 2


                        } else if (opcion == 2) {
                            ///SI el usuario elige la opción de crear un avion de carga

                            do {
                                System.out.println("Ingrese la carga maxima (en toneladas) del avion. Valor maximo 500 toneladas");

                                cargaMaxima = scanner.nextFloat();
                                if (cargaMaxima <= 0) {

                                    controlador = false;
                                    System.out.println("La carga máxima no puede ser 0 o un valor negativo");

                                } else if (cargaMaxima > 500) {
                                    ///Para limitar la carga máxima, se establece que el avión puede llegar a transportar
                                    ///como máximo 500 toneladas.

                                    controlador = false;
                                } else {

                                    controlador = true;

                                }

                            } while (!controlador);
                            ///Se pide que se ingrese una carga máxima válida

                            do {
                                try {
                                    System.out.println("Ingrese si el avion puede llevar carga peligrosa o no");
                                    cargaPeligrosa = scanner.nextBoolean();
                                    controlador = true;
                                } catch (InputMismatchException e) {

                                    System.out.println("Error. Ingrese un booleano");
                                    controlador = false;
                                }
                            } while (!controlador);
                            ///Se pide que se ingrese si el avion lleva carga máxima.

                            do {

                                try {

                                    System.out.println("Ingrese si el avion tiene grua interna");
                                    gruaInterna = scanner.nextBoolean();

                                } catch (InputMismatchException e) {

                                    System.out.println("Error. Ingrese un booleano");
                                    controlador = false;
                                }

                            } while (!controlador);
                            ///Se pide que se ingrese si el avion tiene grua interna.

                            do {
                                System.out.println("Cargue un elemento a la lista de objetos permitidos para" +
                                        "transportar: ");
                                objetoPermitido = scanner.nextLine();

                                if (objetosPermitidos.contains(objetoPermitido)) {
                                    System.out.println("El objeto ya fue ingresado");
                                } else {
                                    objetosPermitidos.add(objetoPermitido);
                                    System.out.println("objeto agregado");
                                }

                                System.out.println("Si quiere seguir ingresando elementos presione s: ");
                                letra = scanner.nextLine().charAt(0);
                            } while (letra == 's');
                            ///Se genera una lista de objetos que se van a poder transportar en el avion


                            AvionDeCarga aCarg = new AvionDeCarga(matricula, modelo, kmRecorridos, hangarSeleccionado.getIdHangar(), objetosPermitidos, cargaMaxima, cargaPeligrosa, gruaInterna);
                            ///Se genera el avion de carga.
                            aviones.agregar(aCarg);
                            ///Se lo agrega a la clase gestora
                            hangarSeleccionado.agregarAvionAHangar(aCarg.getMatricula());
                            ///Se avisa al hangar que va a poseer un nuevo avión
                            hangaresTotales.modificar(hangarSeleccionado, hangarSeleccionado.getIdHangar());
                            ///Se guarda el cambio (ahora el hangar tiene un nuevo avión).
                        }

                        agregarRegistroMatricula(matricula);

                    } catch (ExcepcionAeropuertoInactivo | ExcepcionHangarInactivo excepcionAeropuertoInactivo) {

                        System.out.println("Error. " + excepcionAeropuertoInactivo.getMessage());

                    } catch (ExcepcionCapacidadAviones excepcionCapacidadAviones) {

                        System.out.println("Error." + excepcionCapacidadAviones.getMessage());

                    } catch (ExcepcionMatricula excepcionMatricula) {

                        System.out.println("Error. " + excepcionMatricula.getMessage());
                    }


                    break;

                case 2:

                    System.out.println("Usted ha elegido eliminar un avion.");
                    System.out.println("No se puede eliminar un avion si hay un pasaje activo que tenga un vuelo" +
                            "comprado con este avion");

                    System.out.println("Ingrese la matricula del avion a eliminar.");
                    matricula = scanner.nextLine();
                    ///Se ingresa la matricula

                    controlador = true;

                    avionABuscar = aviones.buscar(matricula);
                    ///Se busca al avion que se quiere eliminar

                    if (avionABuscar != null) {
                        if (avionABuscar.getIdHangar().equals(hangarSeleccionado.getIdHangar())) {

                            ///Si el avion se encontró y el avion encontrado tiene el mismo idHangar que el hangar
                            ///pasado por parametro (Que es el hangar con el que se entró a este menú)...

                            ///...entonces se fija si es una instancia de AvionComercial

                            try {

                                avionABuscar.validarExistenciaRutaAerea();

                                ///En caso de serlo, revisa que el avion no tenga una ruta aerea asignada. Si el avion no tiene,
                                ///entonces tampoco tiene pasajes comprados. No se puede comprar un pasaje de avion si este no
                                ///tiene una ruta aerea asignada.

                                ///Si tiene una ruta aerea asignada, primeramente deberá eliminarla para poder borrar el avion.
                                ///Si un avion tiene una ruta aerea establecida es posible que tenga asientos comprados.


                            } catch (ExcepcionRutaEnUso excepcionRutaEnUso) {
                                System.out.println("Error. " + excepcionRutaEnUso.getMessage());
                                System.out.println("Antes de borrar un avion debe eliminar su ruta aerea.");
                                controlador = false;
                                ///En caso de que haya una ruta aerea asignada para el avion, controlador se va a hacer false y
                                ///no se va a poder eliminar el avion.
                            }


                            if (controlador) {

                                ///Si control es verdadero es porque el avion no tiene ruta aerea asignada.

                                if (aviones.eliminar(matricula)) {
                                    ///Si se logra eliminar el avion con la matricula ingresada...

                                    System.out.println("Se ha borrado el avion");
                                    ///...se da aviso al usuario.
                                }

                                if (hangarSeleccionado.eliminarAvionDeHangar(matricula)) {
                                    ///Si se logra eliminar el registro del id del avion en el hangar...

                                    System.out.println("Registro del avion borrado del hangar");
                                    ///...se avisa al usuario...

                                    hangaresTotales.modificar(hangarSeleccionado, hangarSeleccionado.getIdHangar());
                                    ///...y se guarda la modificación realizada en el hangar


                                    ///No se realiza en esta parte modificaciones en la clase GestionRutaAerea por motivos que
                                    ///se explicaron más arriba: no se puede eliminar un avion si este tiene ruta aerea asignada

                                }

                                /////////////////////////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////////////////////////
                                ////ANOTACION: FIJARSE SI NO CONVIENE HACER ESTO AL QUERER SALIR DE ESTE MENU////
                                ////ANOTACION: FIJARSE SI NO CONVIENE HACER ESTO AL QUERER SALIR DE ESTE MENU////
                                /////////////////////////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////////////////////////

                                ///LO DE ARRIBA ES BAJO LA LOGICA DE PENSAR QUE SI POR CADA VEZ QUE ENTRO ACÁ, SE
                                ///HACE LA MODIFICACION, ES UN GASTADERO DE RECURSOS. ES MEJOR HACER TODOS LOS CAMBIOS
                                ///QUE SE QUIERAN HACER Y RECIEN CUANDO SE SALE DEL MENU QUE GUARDE LOS CAMBIOS

                            }
                        } else {
                            System.out.println("No se ha encontrado el avion en el hangar");
                        }

                    } else {

                        System.out.println("No se ha podido encontrar al avion");

                    }


                    break;

                case 3:


                    ///Al no poder cambiarse la matricula, los pasajes, los hangares y las rutas aereas
                    ///quedan iguales y no se tienen que modificar

                    System.out.println("Usted ha elegido modificar un avion.");

                    System.out.println("Ingrese la matricula del avion a modificar: ");
                    matriculaABuscar = scanner.nextLine();

                    Avion avionAModificar = aviones.buscar(matriculaABuscar);

                    //Se busca al avion con la matricula ingresada

                    if (avionAModificar != null) {
                        if (((avionAModificar.getIdHangar().equals(hangarSeleccionado.getIdHangar())) || avionAModificar.getKmRecorridos() > 10000)) {

                            ///Si se encuentra el avion y pertenece al hangar que se seleccionó para ingresar a este menu...

                            System.out.println("Ingrese la modificacion del modelo: ");
                            modelo = scanner.nextLine();
                            avionAModificar.setModelo(modelo);
                            ///...se setea el nuevo modelo

                            do {
                                try {
                                    System.out.println("Ingrese true si el avion va a estar activo o false para indicar si va a estar inactivo: ");
                                    avionActivo = scanner.nextBoolean();
                                    ///...se setea si va a estar activo o inactivo.


                                    controlador = true;
                                } catch (InputMismatchException exception) {
                                    System.out.println("Ingrese un valor booleano.");
                                    controlador = false;
                                }

                            } while (!controlador);


                            if (avionAModificar instanceof AvionComercial) {

                                ///Si avion a modificar es una instancia de AvionComercial
                                AvionComercial aComModificado = (AvionComercial) avionAModificar;
                                ///Se hace el casteo.

                                System.out.println("Ingrese el nuevo nombre de la aerolinea");
                                aerolinea = scanner.nextLine();
                                scanner.nextLine();
                                ///Se pide el nombre de la aerolinea

                                try {

                                    do {
                                        System.out.println("Ingrese la nueva capacidad el avion. Maximo 750 asientos: ");
                                        capacidad = scanner.nextInt();
                                        if (capacidad <= 0 || capacidad > 750) {
                                            System.out.println("La capacidad que se quiere establecer esta fuera de rango");
                                        }

                                    } while (capacidad <= 0 || capacidad > 750);

                                    aComModificado.validarNuevaCapacidadAvion(capacidad);
                                    ///Se pide modificar la capacidad máxima del avion y se verifica.


                                } catch (ExcepcionAsientosTotales excepcionAsientosTotales) {
                                    System.out.println("Error. " + excepcionAsientosTotales.getMessage());
                                }

                                aviones.modificar(aComModificado, aComModificado.getMatricula());
                                ///Se modifica el avion comercial
                            }

                            if (avionAModificar instanceof AvionDeCarga) {
                                ///Si avion a modificar es una instancia de AvionDeCarga

                                AvionDeCarga aCargModificado = (AvionDeCarga) avionAModificar;
                                ///Se hace el casteo.


                                do {
                                    System.out.println("Ingrese la carga maxima (en kg) del avion");
                                    cargaMaxima = scanner.nextFloat();

                                    ///Se ingresa la carga máxima que puede transportar el avion

                                    if (cargaMaxima <= 0) {
                                        controlador = false;
                                        System.out.println("No puede existir un avion de carga que transporte" +
                                                "0 o menos kilos");
                                    } else {
                                        controlador = true;
                                    }

                                } while (controlador == false);

                                do {
                                    try {
                                        System.out.println("Ingrese si el avion puede llevar carga peligrosa o no");
                                        cargaPeligrosa = scanner.nextBoolean();

                                        ///Se indica si puede transportar carga peligrosa.

                                        controlador = true;

                                    } catch (InputMismatchException e) {

                                        System.out.println("Error. Ingrese un booleano");
                                        controlador = false;
                                    }
                                } while (controlador == false);

                                do {

                                    try {

                                        System.out.println("Ingrese si el avion tiene grua interna");
                                        gruaInterna = scanner.nextBoolean();

                                        ///Se ingresa si tiene grua interna

                                    } catch (InputMismatchException e) {

                                        System.out.println("Error. Ingrese un booleano");
                                        controlador = false;
                                    }

                                } while (controlador == false);


                                aviones.modificar(aCargModificado, aCargModificado.getMatricula());
                                ///Una vez realizado lo anterior, se guarda el avion modificado.
                                ///Como se dijo antes, al no modificarse la matrícula, no se modifica el hangar ni
                                ///ni el pasaje
                            }

                        } else {

                            System.out.println("El avion no se encuentra en el hangar. Fijese si el avion indicado" +
                                    "pertenece a este hangar o si fue dado de baja por kilometraje.");

                        }

                    } else {

                        System.out.println("El avion indicado no se encuentra en el hangar. Esto puede deberse a que el avion" +
                                "no pertenece a este hangar o fue dado de baja por superar el limite de kilometraje maximo" +
                                "establecido");
                    }


                    break;

                case 4:

                    System.out.println("Usted ha elegido buscar un avion.");
                    System.out.println("Ingrese la matricula del avion");
                    matricula = scanner.nextLine();
                    ///Se ingresa la matricula

                    Avion avion = aviones.buscar(matricula);
                    ///se busca al avion utilizando la matricula

                    if (avion != null && avion.getIdHangar().equals(hangarSeleccionado.getIdHangar())) {
                        ///Si avion es distinto de null (encontró el avion con esa matricula), y si el id del hangar
                        ///que tiene el objeto para indicar en qué hangar esta estacionado es igual al id del hangar
                        ///pasado por parámetro...


                        System.out.println(avion.informacionAvion());
                        ///Se muestra la informacion del avion.

                        ///En Java existe el enlazado automático. Por más que estemos utilizando una clase padre,
                        ///al ser esta abstracta y tener sobreescrito ese método, logra detectar si el avion es
                        ///un avion comercial o un avion comercial y muestra la informacion correspondiente


                    } else {
                        System.out.println("No existe un avion con esa matricula.");
                    }

                    break;

                case 5:

                    System.out.println("Usted ha elegido listar los aviones.");

                    System.out.println(aviones.listado(hangarSeleccionado.getIdHangar()));

                    ///Como aviones (instancia de la clase GestionAvion) tiene todos los aviones independientemente
                    ///de donde se encuentren, se utiliza el id del Hangar para filtrar

                    break;

                case 6:

                    ///EN ESTA PARTE VA A VERSE MODIFICADO:
                    // EL AVION PORQUE SE LE AÑADE LA RUTA AEREA.
                    // LA RUTA AEREA PORQUE A SU HASHSET DE IDs DE AVIONES QUE HACEN ESTA RUTA SE VA A SUMAR UNO MAS
                    // TODOS ELLOS SE VAN A VER MODIFICADOS
                    // SOBRE PASAJE NO SE MODIFICA NADA PORQUE APARTE DE QUE NO SE CAMBIA LA MATRICULA DEL AVION, SI
                    // EL AVION NO POSEE UNA RUTA AEREA ENTONCES NO SE PUEDE COMPRAR UN PASAJE PARA DICHO AVION.

                    System.out.println("Usted ha elegido asignar una ruta aerea a un avion.");
                    System.out.println("Ingrese la matricula del avion a asignar ruta aerea: ");
                    matricula = scanner.nextLine();
                    ///Se ingresa la matricula del avion al cual se desea asignar una ruta aerea.

                    avionABuscar = aviones.buscar(matricula);
                    ///se busca el avion con la matricula indicada.

                    if ((avionABuscar != null && avionABuscar.getIdHangar().equals(hangarSeleccionado.getIdHangar())) || avionABuscar.getKmRecorridos() > 10000) {

                        ///Si el avion se encuentra( != null ) y el avion pertenece al hangar indicado pasado por parámetro en el menu

                        if (avionABuscar.getIdRutaAsignada() == null) {
                            ///Si el avion no tiene asignado una ruta....

                            System.out.println("A continuacion se muestran las rutas aereas que parten desde este aeropuerto: ");

                            System.out.println(rutasAereas.filtradoPorNombreAeropuerto(aeropuerto.getCodigoInternacional()));
                            ///...se muestran las rutas aereas filtradas por el aeropuerto.

                            System.out.println("Ingrese el id de la ruta que quiera establecer para este avion: ");
                            idParaRutaAerea = scanner.nextLine();
                            ///Se ingresa un id para buscar la ruta aerea.

                            rutaParaAsignaroEliminar = rutasAereas.buscar(idParaRutaAerea);
                            ///Se busca la ruta...

                            if (rutaParaAsignaroEliminar != null && rutaParaAsignaroEliminar.getAeropuertoSalida().equals(aeropuerto.getCodigoInternacional())) {

                                ///...y si la ruta es encontrada y su aeropuerto de salida establecido sale desde el aeropuerto pasado
                                /// por parámetro en el menú...

                                rutaParaAsignaroEliminar.agregarIdAvionesConEstaRuta(avionABuscar.getMatricula());
                                ///...entonces se suma el id del avion indicado a la coleccion de ids de la ruta aerea que indica
                                ///los aviones que tienen asignados esta ruta.

                                avionABuscar.setIdRutaAsignada(rutaParaAsignaroEliminar.getIdRutaAerea());
                                ///Ahora se le asigna al avion la ruta area.

                                aviones.modificar(avionABuscar, avionABuscar.getMatricula());
                                rutasAereas.modificar(rutaParaAsignaroEliminar, rutaParaAsignaroEliminar.getIdRutaAerea());
                                ///y finalmente se guardan las modificaciones en las clases gestoras.


                            } else {
                                System.out.println("La ruta indicada no se encuentra.");
                            }


                        } else {

                            System.out.println("El avion ya tiene una ruta asignada.");

                        }


                    } else {
                        System.out.println("El avion no se encuentra en el hangar. Esto puede ser debido a que el avion" +
                                "no pertenece a este hangar o fue dado de baja por superar el limite de kilometraje maximo" +
                                "establecido");
                    }


                    break;

                case 7:
                    System.out.println("Usted ha elegido eliminar la ruta aerea a un avion.");

                    ///EN ESTA PARTE VA A VERSE MODIFICADO:
                    // EL AVION PORQUE SE LE QUITA LA RUTA AEREA.
                    // LA RUTA AEREA PORQUE A SU HASHSET DE IDs DE AVIONES QUE HACEN ESTA RUTA SE VA A ELIMINAR UNO


                    // SOBRE PASAJE NO SE MODIFICA NADA PORQUE NO SE CAMBIA LA MATRICULA Y NO SE PUEDE ELIMINAR LA
                    // RUTA AEREA SI YA HAY UN PASAJE COMPRADO PARA EL AVION.

                    System.out.println("Ingrese la matricula del avion al que le quiere eliminar su ruta aerea" +
                            "establecida: ");
                    matricula = scanner.nextLine();
                    ///Se ingresa la matricula del avion al cual se desea eliminar la ruta aerea.

                    avionABuscar = aviones.buscar(matricula);
                    ///se busca el avion con la matricula indicada.

                    if (avionABuscar != null && avionABuscar.getIdHangar().equals(hangarSeleccionado.getIdHangar())) {

                        ///Si el avion se encuentra( != null ) y el avion pertenece al hangar indicado pasado por parámetro en el menu...

                        if (avionABuscar.getIdRutaAsignada() == null) {
                            ///...Si el avion no tiene asignado una ruta....
                            System.out.println("El avion ya se encuentra sin una ruta aerea establecida");

                        } else {

                            ///...Si el avion tiene ruta asignada...

                            if (hPasajes.pasajeNoExpiradoContieneAvion(matricula)) {

                                ///...se fija que no hayan pasajes comprados para el avion indicado, ya que esto
                                ///indica que hay usuarios que van a hacer esa ruta aerea.

                                rutaParaAsignaroEliminar = rutasAereas.buscar(avionABuscar.getIdRutaAsignada());
                                ///Se busca la ruta aerea...

                                if (rutaParaAsignaroEliminar.eliminarIdAvionDeHashSet(avionABuscar.getMatricula())) {
                                    ///...y si se elimina de su hashset de idAviones el registro del avion al que se le quiere
                                    ///eliminar su ruta aerea asignada.

                                    avionABuscar.setIdRutaAsignada(null);
                                    ///Se borra el idRuta que indicaba al avion cual era su ruta, por lo que ahora ya no tiene.

                                    aviones.modificar(avionABuscar, avionABuscar.getMatricula());
                                    ///finalmente se guardan los cambios del avion en su clase gestora...

                                    rutasAereas.modificar(rutaParaAsignaroEliminar, rutaParaAsignaroEliminar.getIdRutaAerea());
                                    ///...y ocurre lo mismo con la ruta aerea, la cual ya no tiene el registro del avion.

                                } else {
                                    System.out.println("No se pudo eliminar al avion su ruta area asignada");
                                }
                            }


                        }
                    } else {

                        System.out.println("El avion que se intenta modificar no se encuentra en el hangar o ha sido dado de baja" +
                                "logica por exceder el limite de kilometraje maximo");

                    }


                    break;

                default:

                    System.out.println("Opcion no valida.");

                    break;
            }

            System.out.println("Si quiere seguir utilizando el menu de avion para administrador presione s: ");
            continuar = scanner.nextLine().charAt(0);

        }while (continuar == 's');


    }


    ///Esta es la funcion de agregar matricula al json array cuando esta validada
    public static void agregarRegistroMatricula(String matricula) throws ExcepcionMatricula {

        String registro;
        registro = JsonUtiles.leer("RegistroMatricula.json");
        try {

            if (registro != null) {

                JSONArray lista = new JSONArray(registro);
                lista.put(matricula);
                JsonUtiles.grabar(lista, "RegistroMatricula.json");

            }else{
                throw new ExcepcionMatricula("No se han podido descargar las matriculas");


            }


        }catch (JSONException jsonException){

            throw new ExcepcionMatricula("No se han podido descargar/agregar la matriculas");
        }

    }

    ///Esta es la funcion de verificar si hay una matricula determinada en un archivo
    public static void validarRegistroMatricula(String matricula) throws ExcepcionMatricula {

        String registro;
        registro = JsonUtiles.leer("RegistroMatricula.json");

        try {

            if (registro == null) {

                throw new ExcepcionMatricula("No se han podido descargar las matriculas");

            } else {

                JSONArray arregloJSon = new JSONArray(registro);

                for (int i = 0; i < arregloJSon.length(); i++){
                    String tMatricula = arregloJSon.getString(i);

                    if (tMatricula.equals(matricula)){

                        throw new ExcepcionMatricula("Ya existe la matricula que se quiere ingresar");


                    }
                }
            }
        }catch (JSONException jsonException){

            throw new ExcepcionMatricula("No se han podido descargar las matriculas");

        }

    }


}
