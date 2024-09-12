package PaqueteExcepciones;

public class ExcepcionCapacidadHangar extends Exception{
    ///esta excepcion va a servir para asegurar que no se pueda generar un hangar que exceda la cantidad de aviones
    ///permitido por el aeropuerto
    public ExcepcionCapacidadHangar(String message) {
        super(message);
    }
}
