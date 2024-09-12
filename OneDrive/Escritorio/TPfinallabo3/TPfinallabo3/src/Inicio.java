import PaqueteAeropuerto.Aeropuerto;
import PaqueteAeropuerto.GestionAeropuerto;
import PaqueteAvion.AvionComercial;
import PaqueteAvion.AvionDeCarga;
import PaqueteAvion.GestionAvion;
import PaqueteHangar.GestionHangar;
import PaqueteHangar.Hangar;
import PaqueteMenu.MenuIngresoAeropuerto;
import PaquetePasaje.HistorialPasaje;
import PaqueteRutaAerea.GestionRutaAerea;
import PaqueteRutaAerea.RutaAerea;
import PaqueteUsuario.GestionUsuarios;
import PaqueteUsuario.Usuario;
import PaqueteUsuario.UsuarioAdmin;

import java.util.ArrayList;

import static PaqueteMenu.MenuIngresoAeropuerto.ingresoAAeropuerto;

public class Inicio {

    public static void main(String[] args) {



        GestionRutaAerea rutasDisponibles = new GestionRutaAerea();

       /// RutaAerea rutaAerea = new RutaAerea("Dasrta", "AAA111", "BBB222", 200);
        ///rutasDisponibles.agregar(rutaAerea);


        GestionAvion avionesTotales = new GestionAvion();
        AvionComercial avionComercial1 = new AvionComercial("xcx2132112", "Boeing 21321", 0, "balx1", 50, "Flybondi", "Media", true, false, true);
        AvionComercial avionComercial2 = new AvionComercial("adsa2313123", "Alaflauta", 0, "balx1", 25, "Flybondi", "Media", true, false, false);
        AvionComercial avionComercial3 = new AvionComercial("iedioew321", "Boeing 21321", 0, "balx1", 50, "Flybondi", "Media", false, true, true);
        AvionComercial avionComercial4 = new AvionComercial("pcdscjcnso23", "Boeing 21321", 0, "balx1", 50, "Flybondi", "Media", false, true, false);
        AvionComercial avionComercial5 = new AvionComercial("bnlbdf342", "Boeing 21321", 0, "balx1", 50, "Flybondi", "Media", false, false, true);
        AvionComercial avionComercial6 = new AvionComercial("23213213", "Boeing 21321", 0, "balx1", 50, "Flybondi", "Media", false, false, false);


        ArrayList<String>objetosPermitidos = new ArrayList<>();
        objetosPermitidos.add("Joyas");
        objetosPermitidos.add("Ropa");
        objetosPermitidos.add("Consolas");
        objetosPermitidos.add("Billeteras");
        objetosPermitidos.add("Lentes");
        AvionDeCarga avionDeCarga1 = new AvionDeCarga("dsads34", "Panfult", 20, "balx1", objetosPermitidos, 10, false, true);
        AvionDeCarga avionDeCarga2 = new AvionDeCarga("ppppp", "Cansad", 0, "balx1", objetosPermitidos, 5, true, true);
        AvionDeCarga avionDeCarga3 = new AvionDeCarga("dsads34", "Panfult", 20, "balx1", objetosPermitidos, 15, false, false);

        GestionHangar hangaresTotales = new GestionHangar();
        Hangar hangar1 = new Hangar("balx1", "scjclacs", 30, true, "AAA111");


        GestionAeropuerto  aeropuertosRegistrados = new GestionAeropuerto();

        Aeropuerto aeropuerto1 = new Aeropuerto("Ezeiza", "padre dutto 19999", 40, "AAA111", true);
        Aeropuerto aeropuerto2 = new Aeropuerto("Newells", "Garoto 21312", 30, "BBB222", true);


        System.out.println(aeropuerto2.obtenerInformacion());

        aeropuertosRegistrados.agregar(aeropuerto1);
        aeropuertosRegistrados.agregar(aeropuerto2);

        GestionUsuarios usuariosTotales = new GestionUsuarios();
        Usuario usuario = new Usuario("Xa334","AAA111", "aaaa1111", "carloso", "Carlos Lopez Martinez", "39333444", "123456789", 'o');
        UsuarioAdmin usuAdmin1 = new UsuarioAdmin("das32", "BBB222", "ABCD1234","Carlos admin", "Carlos Rodriguez", "11111111111", "743564753", 'm', "34555555555");
        UsuarioAdmin usuAdmin2 = new UsuarioAdmin("BGT33", "AAA111", "ABCD1234","ADAS admin", "Bianca Lexter", "2222222222", "2312312321321", 'f', "349999999999");
        UsuarioAdmin usuAdmin3 = new UsuarioAdmin("23s32", "AAA111", "ABCD1234","LLLL admin", "Leon Gieco", "33333333333", "23131323123213", 'm', "342222222222");
        UsuarioAdmin usuAdmin4 = new UsuarioAdmin("kiod2", "AAA111", "ABCD1234","PPPP admin", "Marta Blitz", "44444444444", "312312321321312", 'm', "341111111111");

        usuariosTotales.agregar(usuario);
        usuariosTotales.agregar(usuAdmin1);
        usuariosTotales.agregar(usuAdmin2);
        usuariosTotales.agregar(usuAdmin3);
        usuariosTotales.agregar(usuAdmin4);

        HistorialPasaje hPasajes = new HistorialPasaje();

        MenuIngresoAeropuerto.ingresoAAeropuerto(avionesTotales, hangaresTotales, rutasDisponibles, aeropuertosRegistrados, usuariosTotales, hPasajes);

    }

}
