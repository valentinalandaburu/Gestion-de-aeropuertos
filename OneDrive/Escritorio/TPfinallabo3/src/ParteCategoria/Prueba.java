package ParteCategoria;

import PaqueteAeropuerto.Aeropuerto;
import PaqueteAvion.GestionAvion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prueba {
    public static void vjvjhvjhvjh(String[] args) {

                LocalDate fecha = LocalDate.of(2024, 6, 3);
                StringBuilder muestra = new StringBuilder();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                muestra.append(fecha.format(formatter));

                System.out.println("Fecha en formato personalizado: " + muestra);

        LocalDateTime momentoExacto = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String momentoFormateado = momentoExacto.format(formatter2);
        System.out.println("Momento exacto formateado: " + momentoFormateado);

    }
}
