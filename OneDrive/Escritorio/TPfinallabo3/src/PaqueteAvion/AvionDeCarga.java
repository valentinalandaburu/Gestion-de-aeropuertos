package PaqueteAvion;

import java.util.ArrayList;

public class AvionDeCarga extends Avion{

    private ArrayList <String> objetosPermitidos;
    private float cargaMaxima;
    private boolean cargaPeligrosa;
    private boolean gruaInterna;

    ///CONSTRUCTORES--------------------------------------------------------------------

    public AvionDeCarga(String matricula, String modelo, int kmRecorridos, String idHangar, ArrayList<String>objetosPermitidos ,float cargaMaxima, boolean cargaPeligrosa, boolean gruaInterna) {
        super(matricula, modelo, kmRecorridos, idHangar);
        this.cargaMaxima = cargaMaxima;
        this.cargaPeligrosa = cargaPeligrosa;
        this.gruaInterna = gruaInterna;
        this.objetosPermitidos = objetosPermitidos;
    }

    public AvionDeCarga(String matricula, String idHangar, String idRutaAsignada, String modelo, int kmRecorridos, boolean avionActivo, ArrayList<String> objetosPermitidos, float cargaMaxima, boolean cargaPeligrosa, boolean gruaInterna) {
        super(matricula, idHangar, idRutaAsignada, modelo, kmRecorridos, avionActivo);
        this.objetosPermitidos = objetosPermitidos;
        this.cargaMaxima = cargaMaxima;
        this.cargaPeligrosa = cargaPeligrosa;
        this.gruaInterna = gruaInterna;
    }


    ///CONSTRUCTORES--------------------------------------------------------------------

    ///GETTERS Y SETTERS----------------------------------------------------------------
    public float getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(float cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public boolean isCargaPeligrosa() {
        return cargaPeligrosa;
    }

    public void setCargaPeligrosa(boolean cargaPeligrosa) {
        this.cargaPeligrosa = cargaPeligrosa;
    }

    public boolean isGruaInterna() {
        return gruaInterna;
    }

    public void setGruaInterna(boolean gruaInterna) {
        this.gruaInterna = gruaInterna;
    }
    ///GETTERS Y SETTERS----------------------------------------------------------------

    ///METODOS PARA MENU---------------------------------------------------------------



    ///METODO PARA OBTENER INFORMACION DEL AVION DE CARGA-------------------------------
    ///SOBREESCRIBE AL METODO ABSTRACTO DEL PADRE
    public StringBuilder informacionAvion() {

        StringBuilder info = new StringBuilder();

        info.append("AvionDeCarga{" +
                "objetosPermitidos=" + objetosPermitidos +
                ", cargaMaxima=" + cargaMaxima +
                ", cargaPeligrosa=" + cargaPeligrosa +
                ", gruaInterna=" + gruaInterna +
                ", matricula='" + matricula + '\'' +
                ", idHangar='" + idHangar + '\'' +
                ", idRutaAsignada='" + idRutaAsignada + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kmRecorridos=" + kmRecorridos +
                ", avionActivo=" + avionActivo +
                "] ");

        info.append("}");
        return info;
    }

    ///SOBREESCRIBE AL METODO ABSTRACTO DEL PADRE
    ///METODO PARA OBTENER INFORMACION DEL AVION DE CARGA-------------------------------
    ///METODOS PARA MENU---------------------------------------------------------------


    @Override
    public void vuelo(int kmDeRuta) {

        System.out.println("El avion esta despegando");
        System.out.println("El avion esta volando.");
        System.out.println("El avion ha llegado a destino.");

        verificarKilometrajeAvion();


    }
}
