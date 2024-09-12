package PaqueteMenu;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.GestionAvion;
import PaqueteHangar.GestionHangar;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteUsuario.GestionUsuarios;

import java.util.Scanner;

public final class MenuIngresoAeropuerto {

    public static void ingresoAAeropuerto(GestionAvion avionesTotales, GestionHangar hangaresTotales, GestionRutaAerea rutasDisponibles, GestionAeropuerto aeropuertosRegistrados, GestionUsuarios usuariosTotales, HistorialPasaje hPasajes){



        ////FUNCIONA DE ACÁ
        Scanner scanner = new Scanner(System.in);
        String opcion;

        System.out.println("¿A que aeropuerto quiere ingresar?");
        System.out.println(aeropuertosRegistrados.listadoV2());
        System.out.println("Tenga en cuenta que solo puede ingresar a su usuario desde el Aeropuerto que lo creo: ");
        System.out.println("Ingrese el nombre o codigo internacional (IATA) del aeropuerto: ");
        opcion = scanner.nextLine();

        Aeropuerto aeropuertoBuscado = aeropuertosRegistrados.buscar(opcion);

        if (aeropuertoBuscado != null) {

            Login.menuLogin(avionesTotales, hangaresTotales, rutasDisponibles, aeropuertoBuscado, aeropuertosRegistrados, usuariosTotales, hPasajes);

        }else {
            System.out.println("Aeropuerto no encontrado. Intente nuevamente");
        }
        ///HASTA ACÁ (OSEA FUNCIONA TODOOOO)






    }

}
