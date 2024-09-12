package PaqueteAvion;

import PaqueteExcepciones.ExcepcionRutaEnUso;

public abstract class Avion {

    protected String matricula;  ///se puede modificar
    protected String idHangar;
    protected String idRutaAsignada;
    protected String modelo;  ///Se puede modificar
    protected int kmRecorridos; ///no se puede modificar
    protected boolean avionActivo;  ///se puede modificar, pero el avion no va a poder volar si esta desactivado ni se van a poder comprar pasajes para el avion

    ///CONSTRUCTORES-----------------------------------------------------------------------------------

    public Avion(String matricula, String modelo, int kmRecorridos, String idHangar) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.kmRecorridos = kmRecorridos;
        this.idHangar = idHangar;

        avionActivo = true;
    }

    public Avion(String matricula, String idHangar, String idRutaAsignada, String modelo, int kmRecorridos, boolean avionActivo) {
        this.matricula = matricula;
        this.idHangar = idHangar;
        this.idRutaAsignada = idRutaAsignada;
        this.modelo = modelo;
        this.kmRecorridos = kmRecorridos;
        this.avionActivo = avionActivo;
    }


    ///CONSTRUCTORES-----------------------------------------------------------------------------------

    ///GETTER Y SETTERS--------------------------------------------------------------------------------
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getIdHangar() {
        return idHangar;
    }

    public void setIdHangar(String idHangar) {
        this.idHangar = idHangar;
    }

    public String getIdRutaAsignada() {
        return idRutaAsignada;
    }

    public void setIdRutaAsignada(String idRutaAsignada) {
        this.idRutaAsignada = idRutaAsignada;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(int kmRecorridos) {
        this.kmRecorridos = this.kmRecorridos + kmRecorridos;
    }

    public boolean isAvionActivo() {
        return avionActivo;
    }

    public void setAvionActivo(boolean avionActivo) {
        this.avionActivo = avionActivo;
    }

    ///GETTER Y SETTERS--------------------------------------------------------------------------------

    ///OTROS METODOS-----------------------------------------------------------------------------------
    public abstract StringBuilder informacionAvion();

    ///OTROS METODOS-----------------------------------------------------------------------------------

    public void validarExistenciaRutaAerea() throws ExcepcionRutaEnUso{

        if (getIdRutaAsignada() != null){
            throw new ExcepcionRutaEnUso("El avion tiene una ruta asignada.");

        }
    }


    public abstract void vuelo(int kmDeRuta); ///¡488 kilómetros de id! ¡488 kilómetros de vuelta!


    public void verificarKilometrajeAvion(){

        if (getKmRecorridos() > 10000){
            System.out.println("Se procede a dar de baja al avión por exceder el limite de kilometraje" +
                    "poco realista establecido artificialmente por el programador");
                    ///Acaso poner esto ya empieza a dar indicios de mi descenso a la locura?

            setAvionActivo(false);


        }


    }


}
